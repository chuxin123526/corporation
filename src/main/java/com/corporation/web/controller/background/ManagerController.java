package com.corporation.web.controller.background;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Manager;
import com.corporation.po.Manager_Role;
import com.corporation.po.Privilege;
import com.corporation.po.Role;
import com.corporation.po.Role_Privilege;
import com.corporation.po.User;
import com.corporation.service.ManagerService;
import com.corporation.service.RoleManagerService;

/**
 * 
 * @ClassName: ManagerController
 * @Description: 处理管理员模块的请求
 * @author: JJChen
 * @date: 2016年12月5日 下午3:19:27
 */
@Controller
@RequestMapping(path = "/manager")
public class ManagerController
{
	@Resource
	private ManagerService managerServiceImpl ;
	
	@Resource
	private RoleManagerService roleManagerServiceImpl ; 
	
	
	
	
	/**
	 * 
	 * @Title: list
	 * @Description: 管理员列表
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/list")
	public ModelAndView list() 
	{
		ModelAndView modelAndView = new ModelAndView("background/manager/list") ; 
		
		//获取管理员列表
		try
		{
			List<Manager> managerList = this.managerServiceImpl.list() ;
			modelAndView.addObject("managerList", managerList) ;
		}
		catch(Exception e)
		{
			modelAndView.addObject("errorMessage", "参数错误") ; 
			modelAndView.setViewName("background/common/error");
			e.printStackTrace();
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除管理员
	 * @param managerID
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/delete")
	public String delete(@RequestParam("managerID") String managerID , HttpServletRequest request) 
	{
		try
		{
			if(this.managerServiceImpl.delete(Long.parseLong(managerID))==false)
			{
				//删除失败
				request.setAttribute("errorMessage", "删除失败");
				return "forward:../manager/forwardErrorPage" ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../manager/forwardErrorPage" ;
		}
		
		return "redirect:list" ; 
	}
	
	
	/**
	 * 
	 * @Title: addUI
	 * @Description: 添加管理员界面
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/addUI")
	public ModelAndView addUI()
	{
		ModelAndView modelAndView = new ModelAndView("background/manager/addUI") ; 
		
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: checkManagerNameAvailable
	 * @Description: 检查管理员名字是否可用,即管理员的名字不能重复
	 * @param ManagerName
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/checkManagerNameAvailable")
	@ResponseBody
	public String checkManagerNameAvailable(@RequestParam("managerName") String managerName)  
	{
		if(this.managerServiceImpl.checkManagerNameAvailable(managerName))
		{
			//用户名可用
			return "true" ; 
		}
		else
		{
			//用户名不可用
			return "false" ;
		}
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加管理员
	 * @param managerName
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/add")
	public String add(@RequestParam("managerName") String managerName , HttpServletRequest request)
	{
		try
		{
			Manager manager = new Manager() ; 
			manager.setName(managerName);
			manager.setPassword(DigestUtils.md5DigestAsHex(managerName.getBytes()));
			//添加
			if((this.managerServiceImpl.add(manager) == false))
			{
				//添加失败
				request.setAttribute("errorMessage", "添加失败");
				return "forward:../manager/forwardErrorPage" ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../manager/forwardErrorPage" ;
		}
		
		return "redirect:list" ;
	}
	
	@RequestMapping(path = "/loginUI")
	public ModelAndView loginUI()
	{
		ModelAndView modelAndView = new ModelAndView("background/manager/loginUI") ; 
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: checkByManagerNameAndPassword
	 * @Description: 检查用户名和密码是否正确
	 * @param managerName
	 * @param password
	 * @return
	 * @return: String
	 */
	@RequestMapping("/checkByManagerNameAndPassword")
	@ResponseBody
	public String checkByManagerNameAndPassword(@RequestParam("managerName") String managerName , @RequestParam("password") String password)
	{
		JSONObject result = new JSONObject() ; 
		
		try
		{
			Manager manager = new Manager() ; 
			manager.setName(managerName);
			manager.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			if(this.managerServiceImpl.checkByManagerNameAndPassword(manager))
			{
				//用户名和密码正确
				result.put("responseCode", "1") ;
			}
			else
			{
				//用户名或密码错误
				result.put("responseCode", "0") ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.put("responseCode", "2") ;
		}
		
		return result.toString() ;
	}
	
	/**
	 * 
	 * @Title: login
	 * @Description: 验证后的登录
	 * @param ManagerName
	 * @param password
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/login")
	public String login(@RequestParam("managerName") String ManagerName , 
						@RequestParam("password") String password , 
						HttpServletRequest request)
	{
		try
		{
			
			Manager m = new Manager() ; 
			m.setName(ManagerName);
			m.setPassword(DigestUtils.md5DigestAsHex(password.getBytes())) ;
			Manager manager = this.managerServiceImpl.selectByManagerNameAndPassword(m) ; 
			request.getSession().setAttribute("manager", manager);
			
			Map<String, String> topPrivilegeMap = new HashMap<>() ; 
			if(manager.getName().equals("administrator"))
			{
				//当前登录管理员为超级管理员
				List<Privilege> topPrivilegeList = this.roleManagerServiceImpl.topPrivilegeList() ; 
				
				for(Privilege privilege : topPrivilegeList)
				{
					topPrivilegeMap.put(privilege.getName(), privilege.getUrl()) ;
				}
			}
			else
			{
				//获取该管理员角色关系列表
				List<Manager_Role> manager_roleList = this.managerServiceImpl.selectManagerRoleByManagerID(manager.getId()) ; 
				//遍历
				for(Manager_Role manager_role : manager_roleList)
				{
					//获取角色权限关系列表
					List<Role_Privilege> role_privilegeList = this.roleManagerServiceImpl.selectRole_PrivilegeListByRoleID(manager_role.getRoleID()) ; 
					//遍历
					for(Role_Privilege role_privilege : role_privilegeList)
					{
						//获取权限
						Privilege privilege = this.roleManagerServiceImpl.selectPrivilegeByPrimaryKey(role_privilege.getPrivilegeID()) ; 

						topPrivilegeMap.put(privilege.getName(), privilege.getUrl()) ; 
					}
				}
			}
			
			request.getSession().setAttribute("topPrivilegeMap", topPrivilegeMap);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../manager/forwardErrorPage" ;
		}
		
		return "redirect:../backgroundHome/index" ;
	}
	
	/**
	 * 
	 * @Title: exit
	 * @Description: 退出
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/exit")
	public String exit(HttpServletRequest request)
	{
		request.getSession().setAttribute("manager", null);
		return "redirect:../manager/loginUI" ;
	}
	
	/**
	 * 
	 * @Title: updatePasswordUI
	 * @Description: 修改密码界面
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/updatePasswordUI")
	public ModelAndView updatePasswordUI()
	{
		ModelAndView modelAndView = new ModelAndView("background/manager/updatePasswordUI") ; 
		
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: updatePassword
	 * @Description: 修改密码
	 * @param password
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/updatePassword")
	public String updatePassword(@RequestParam("password") String password , HttpServletRequest request)
	{
		try
		{
			Manager manager = new Manager() ; 
			Manager sessionManager = (Manager)request.getSession().getAttribute("manager") ;
			manager.setId(sessionManager.getId());
			manager.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			//修改密码
			if(this.managerServiceImpl.updatePassword(manager) == false)
			{
				//影响条目为0，修改失败
				request.setAttribute("errorMessage", "修改失败");
				return "forward:../manager/forwardErrorPage" ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "forward:../manager/forwardErrorPage" ;
		}
		
		return "redirect:../backgroundHome/index" ;
	}
	
	/**
	 * 
	 * @Title: allocateRoleUI
	 * @Description: 分配角色界面
	 * @param managerID 管理员ID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/allocateRoleUI")
	public ModelAndView allocateRoleUI(@RequestParam("managerID") String managerID ) 
	{
		ModelAndView modelAndView = new ModelAndView("background/manager/allocateRoleUI") ; 
		
		try
		{
			//获取该管理员原来拥有的与角色的关系
			List<Manager_Role> manager_roleList = this.managerServiceImpl.selectManagerRoleByManagerID(Long.parseLong(managerID)) ; 
			//获取所有角色
			List<Role> roleList = this.roleManagerServiceImpl.list() ; 
			//获取管理员
			Manager manager = this.managerServiceImpl.selectByManagerID(Long.parseLong(managerID)) ; 
			
			//页面数据回显
			modelAndView.addObject("manager_roleList", manager_roleList) ; 
			modelAndView.addObject("roleList", roleList) ; 
			modelAndView.addObject("manager", manager) ;  
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "参数错误") ; 
			modelAndView.setViewName("background/common/error");
		}
		
		return modelAndView ; 
	}
	
	
	/**
	 * 
	 * @Title: allocateRole
	 * @Description: 分配角色
	 * @param roleIDS
	 * @param managerID
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/allocateRole")
	public String allocateRole(@RequestParam(value = "roleIDS" , required = false) String[] roleIDS,
								@RequestParam("managerID") String managerID , 
								HttpServletRequest request)
	{
		try
		{
			//String数组 转 long数组
			long[] roleIDSS = null ; 
			if(roleIDS != null)
			{
				roleIDSS = new long[roleIDS.length] ; 
				for(int i = 0 ; i < roleIDS.length ; i++)
				{
					roleIDSS[i] = Long.parseLong(roleIDS[i]) ;   
				}
			}
			else
			{
				roleIDSS = new long[0] ; 
			}
			
			//分配角色
			this.managerServiceImpl.allocateRole(roleIDSS, Long.parseLong(managerID)) ; 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../manager/forwardErrorPage" ; 
		}
		return "redirect:list" ; 
	}
	
	@RequestMapping(path = "/forwardErrorPage")
	public ModelAndView forwardErrorPage()
	{
		return new ModelAndView("background/common/error") ; 
	}
	
	
	
}
