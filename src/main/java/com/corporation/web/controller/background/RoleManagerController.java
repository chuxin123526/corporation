package com.corporation.web.controller.background;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Privilege;
import com.corporation.po.Role;
import com.corporation.po.Role_Privilege;
import com.corporation.service.RoleManagerService;

@Controller
@RequestMapping(path = "/roleManager")
public class RoleManagerController
{
	@Resource
	private RoleManagerService roleManagerServiceImpl ; 
	
	@RequestMapping(path = "/list")
	public ModelAndView list() 
	{
		ModelAndView modelAndView = new ModelAndView("background/roleManager/list") ;
		
		try
		{
			//获取角色列表
			List<Role> roleList = this.roleManagerServiceImpl.list() ; 
			modelAndView.addObject("roleList", roleList) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("background/common/error") ;
			modelAndView.addObject("errorMessage", "参数错误") ; 
		}
		
		return modelAndView ; 
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除角色
	 * @param roleID
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/delete")
	public String delete(@RequestParam("roleID") String roleID , HttpServletRequest request)
	{
		try
		{
			if(this.roleManagerServiceImpl.delete(Long.parseLong(roleID))==false)
			{
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
	 * @Description: 添加角色界面
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/addUI")
	public ModelAndView addUI()
	{
		ModelAndView modelAndView = new ModelAndView("background/roleManager/addUI") ; 
		
		return modelAndView ; 
	}
	
	@RequestMapping(path = "/add")
	public String add(@RequestParam("roleName") String roleName , 
						HttpServletRequest request) 
	{
		try
		{
			//添加角色
			Role role = new Role() ; 
			role.setName(roleName);
			//保存
			if(this.roleManagerServiceImpl.add(role) == false)
			{
				//保存失败
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
	
	
	/**
	 * 
	 * @Title: allocatePrivilegeUI
	 * @Description: 分配权限界面
	 * @param roleID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/allocatePrivilegeUI")
	public ModelAndView allocatePrivilegeUI(@RequestParam("roleID") String roleID)
	{
		ModelAndView modelAndView = new ModelAndView("background/roleManager/allocatePrivilegeUI") ; 
		
		try
		{
			//获取该角色与权限的对应关系
			List<Role_Privilege> role_privilegeList = this.roleManagerServiceImpl.selectRole_PrivilegeListByRoleID(Long.parseLong(roleID)) ; 
			//获取系统顶级权限
			List<Privilege> topPrivilegeList = this.roleManagerServiceImpl.topPrivilegeList() ; 
			//获取角色
			Role role = this.roleManagerServiceImpl.selectByPrivilegeKey(Long.parseLong(roleID)) ; 
			
			
			//页面回显数据
			modelAndView.addObject("role_privilegeList" , role_privilegeList) ; 
			modelAndView.addObject("topPrivilegeList" , topPrivilegeList) ; 
			modelAndView.addObject("role", role) ; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "参数错误") ; 
			modelAndView.setViewName("background/common/error");
		}
		
		return modelAndView; 
	}
	
	/**
	 * 
	 * @Title: allocatePrivilege
	 * @Description: 分配权限
	 * @param privilegeIDS
	 * @param roleID
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/allocatePrivilege")
	public String allocatePrivilege(@RequestParam(value = "privilegeIDS" , required = false) String[] privilegeIDS , 
									@RequestParam("roleID") String roleID , 
									HttpServletRequest request)
	{
		try
		{
			long[] privilegeIDSS = null ; 	
			//字符串数组转long数组
			if(privilegeIDS != null)
			{
				privilegeIDSS = new long[privilegeIDS.length] ;
				for(int i = 0 ; i < privilegeIDS.length ; i++)
				{
					privilegeIDSS[i] = Long.parseLong(privilegeIDS[i]) ; 
				}
				
			}
			else
			{
				privilegeIDSS = new long[0] ; 
			}
			this.roleManagerServiceImpl.allocatePrivilege(privilegeIDSS, Long.parseLong(roleID)) ; 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../manager/forwardErrorPage" ; 
		}
		return "redirect:list" ; 
	}
	
	
	
	
}
