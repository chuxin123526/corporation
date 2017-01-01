package com.corporation.web.controller.front;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.corporation.po.Corporation;
import com.corporation.po.CorporationType;
import com.corporation.po.User;
import com.corporation.service.CorporationService;
import com.corporation.service.UserService;
import com.corporation.util.EmailUtil;
import com.google.code.kaptcha.Producer;


/**
 * 
 * @ClassName: UserController
 * @Description: 处理用户模块请求
 * @author: JJChen
 * @date: 2016年11月22日 上午10:04:56
 */
@Controller
@RequestMapping(path = "/user")
public class UserController 
{
	@Resource
	private UserService userService ;
	
	@Resource 
    private Producer captchaProducer ;
	
	@Resource
	private CorporationService corporationServiceImpl ; 
	
	@Resource
	private EmailUtil emailUtil ;
	
	/**
	 * 
	 * @Title: loginUI
	 * @Description: 登录页面
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping("/loginUI")
	public ModelAndView loginUI()
	{
		ModelAndView modelAndView = new ModelAndView("/front/user/loginUI") ;
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: registerUI
	 * @Description: 注册页面
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/registerUI")
	public ModelAndView registerUI()
	{
		ModelAndView modelAndView = new ModelAndView("/front/user/registerUI") ; 
		
		return modelAndView ; 
	}
	
	/**
	 * 
	 * @Title: register
	 * @Description: 用户注册
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/register")
	public ModelAndView register(@RequestParam("userName") String userName , @RequestParam("password") String password , 
									@RequestParam("email") String email , HttpServletRequest request
			)
	{
		ModelAndView modelAndView = new ModelAndView("common/success") ; 
		
		//发送激活邮件
		String title = "社团网申系统激活邮箱" ;
		String url = "http://"+request.getServerName()+":"+
				request.getServerPort()+request.getContextPath() +"/manager/user/activateEmail?email="+email ;
		String content = "点击链接激活邮箱  <a href = '"+url+"' >" + url + "</a>" ;
		try
		{
			this.emailUtil.send(email, title, content) ;
			//保存用户
			User user = new User() ; 
			user.setUserName(userName);
			user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			user.setEmail(email);
			user.setStatus("0");
			if(this.userService.register(user) == false)
			{
				//影响条数为0，保存失败
				request.setAttribute("errorMessage", "保存失败");
				modelAndView.setViewName("common/error") ;
				return modelAndView;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "系统异常");
			modelAndView.setViewName("common/error") ;
			return modelAndView;
		}
		
		modelAndView.addObject("successMessage", "已发送邮件到"+email+",请激活") ;
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: activateEmail
	 * @Description: 激活邮箱
	 * @param email 邮箱
	 * @param 
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/activateEmail")
	public ModelAndView activateEmail(@RequestParam("email") String email , HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("common/success") ; 
		
		// 检查用户是否存在
		User user = new User() ; 
		user.setEmail(email);
		try
		{
			user = this.userService.selectIDByEmail(user) ;
			if(user == null)
			{
				//该邮箱未注册
				request.setAttribute("errorMessage", "邮箱未注册");
				modelAndView.setViewName("common/error");
				return modelAndView ;
			}
			else
			{
				//激活邮箱
				user.setStatus("1");
				if(this.userService.activateEmail(user) == false)
				{
					//激活失败
					request.setAttribute("errorMessage", "激活失败");
					modelAndView.setViewName("common/error");
					return modelAndView ;
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "系统异常");
			modelAndView.setViewName("common/error");
			return modelAndView ;
		}
		
		modelAndView.addObject("successMessage", "激活成功") ; 
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: checkUserNameIfAlreadyRegister
	 * @Description: 检查用户名是否已经被注册
	 * @param userName
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/checkUserNameAvailable")
	@ResponseBody
	public String checkUserNameAvailable(@RequestParam("userName") String userName)
	{
		
		boolean flag = false ;
		User user = new User() ; 
		user.setUserName(userName);
		try
		{
			flag = this.userService.checkUserNameAvailable(user) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new Boolean(flag).toString() ;
	}
	
	/**
	 * 
	 * @Title: checkEmailAvailable
	 * @Description: 检查邮箱是否可用
	 * @param 
	 * 		email 邮箱
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/checkEmailAvailable")
	@ResponseBody
	public String checkEmailAvailable(@RequestParam("email") String email)
	{
		
		boolean flag = false ;
		User user = new User() ; 
		user.setEmail(email);
		try
		{
			flag = this.userService.checkEmailAvailable(user) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new Boolean(flag).toString() ;
	}
	
	/**
	 * 
	 * @Title: checkByUserNameAndPassword
	 * @Description: 校验用户名和密码是否正确
	 * @param userName
	 * @param password
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/checkByUserNameAndPassword")
	@ResponseBody
	public String checkByUserNameAndPassword(@RequestParam("userName") String userName , @RequestParam("password") String password)
	{
		JSONObject result = new JSONObject() ; 
		try
		{
			User user = new User() ; 
			user.setUserName(userName);
			user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			if(this.userService.checkUserByNameAndPassword(user))
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
			//系统异常
			result.put("responseCode", "2") ; 
			e.printStackTrace();
		}
		
		return result.toString() ;
	}
	
	/**
	 * 
	 * @Title: login
	 * @Description: 验证后的登录
	 * @param userName
	 * @param password
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/login")
	public String login(@RequestParam("userName") String userName , @RequestParam("password") String password , HttpServletRequest request)
	{
		User user = new User() ; 
		user.setUserName(userName);
		//获取用户ID
		try
		{
			User user1 = this.userService.selectIDbyUserName(user)  ; 
			if(user1 != null)
			{
				//查找到用户ID
				//设置用户ID
				user.setId(user1.getId());
				
				//查找用户
				//设置用户
				user = this.userService.selectUserByPrimaryKey(user) ;
			}
			else
			{
				//未查找到用户ID
				request.setAttribute("errorMessage", "该用户不存在");
				return "forward:../user/forwardErrorPage" ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "系统异常");
			return "forward:../user/forwardErrorPage" ;
		}
		
		request.getSession().setAttribute("user", user);
		
		//重定向到首页
		return "redirect:../home/index" ;
	}
	
	/**
	 * 
	 * @Title: personalInfoComplete
	 * @Description: 个人信息完善页面
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/personalInfoCompleteUI")
	public ModelAndView personalInfoCompleteUI(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("/front/user/personalInfoCompleteUI") ; 
		
		try
		{
			//获取用户信息
			User user = new User() ; 
			user.setId(((User)request.getSession().getAttribute("user")).getId()) ; 
			user = this.userService.selectUserByPrimaryKey(user) ; 
			modelAndView.addObject("user", user) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "该用户不存在");
			modelAndView.setViewName("common/error");
			return modelAndView ;
		}
		
		return modelAndView ; 
	}
	
	@RequestMapping(path = "/forwardErrorPage")
	public ModelAndView forwardErrorPage() 
	{
		return new ModelAndView("common/error") ;
	}
	
	/**
	 * 
	 * @Title: personalInfoComplete
	 * @Description: 个人信息完善
	 * @param realName
	 * @param sex
	 * @param phoneNumber
	 * @param college
	 * @param major
	 * @param period
	 * @param introduction
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/personalInfoComplete")
	public String personalInfoComplete(@RequestParam("realName") String realName , 
				@RequestParam("sex") String sex ,
				@RequestParam("phoneNumber") String phoneNumber ,
				@RequestParam("college") String college ,
				@RequestParam("major") String major ,
				@RequestParam("period") String period ,
				@RequestParam("introduction") String introduction , 
				HttpServletRequest request)
	{
		User user = new User() ; 
		try
		{
			user.setId(((User)request.getSession().getAttribute("user")).getId());
			user.setRealName(realName);
			user.setPhoneNumber(phoneNumber);
			user.setSex(sex);
			user.setCollege(college);
			user.setMajor(major);
			user.setPeriod(period);
			user.setPeriod(period);
			user.setIntroduction(introduction.trim());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:forwardErrorPage" ; 
		}
			
		try
		{			
			if(this.userService.updateByPrimaryKeySelective(user) == false)
			{
				request.setAttribute("errorMessage", "修改失败");
				return "forward:forwardErrorPage" ;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "修改失败");
			return "forward:forwardErrorPage" ;
			
		}
		
		return "redirect:personalInfoCompleteUI" ;
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
		ModelAndView modelAndView = new ModelAndView("front/user/updatePasswordUI") ; 

		return modelAndView ; 
	}
	
	/**
	 * 
	 * @Title: updatePassword
	 * @Description: 修改密码
	 * @param originPassword
	 * @param newPassword
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/updatePassword")
	@ResponseBody
	public String updatePassword(@RequestParam("originPassword") String originPassword , 
			@RequestParam("newPassword") String newPassword , HttpServletRequest request)
	{
		JSONObject result = new JSONObject() ; 
		long userID = ((User)request.getSession().getAttribute("user")).getId() ;
		originPassword = DigestUtils.md5DigestAsHex(originPassword.getBytes()) ;
		String databaseOrignPassword = ((User)request.getSession().getAttribute("user")).getPassword() ; 
		if(!originPassword.equals(databaseOrignPassword))
		{
			//用户输入原密码与数据库原密码不相同
			result.put("responseCode", "0") ;
		}
		else
		{
			//用户输入原密码与数据库原密码相同，修改用户密码
			User user = new User() ; 
			user.setId(userID);
			user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
			result.put("responseCode", "1") ;
			
			//保存
			try
			{
				this.userService.updateByPrimaryKeySelective(user) ;
			} catch (Exception e)
			{
				e.printStackTrace();
				//异常,返回码:2
				result.put("responseCode", "2") ;
			}
		}
		
		return result.toString() ;
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
		request.getSession().setAttribute("user", null);
		
		return "redirect:loginUI" ;
	}
	
	/**
	 * 
	 * @Title: myCorporation
	 * @Description: 我的社团
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/myCorporation")
	public ModelAndView myCorporation(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("front/user/myCorporation") ;
		
		try
		{
			//获取我的社团
			User user = (User)request.getSession().getAttribute("user") ;
			List<Corporation> corporationList = this.userService.myCorporation(user) ;
			
			//获取社团类型
			List<CorporationType> corporationTypeList = this.corporationServiceImpl.corporationTypeList() ;
			
			
			//页面数据回显
			modelAndView.addObject("corporationList", corporationList);
			modelAndView.addObject("corporationTypeList", corporationTypeList) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "系统异常") ; 
			return modelAndView ;
		}
		
		
		return modelAndView ; 
	}
	
	/**
	 * 
	 * @Title: myJoinCorporation
	 * @Description: 我加入的社团
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/myJoinCorporation")
	public ModelAndView myJoinCorporation(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("front/user/myCorporation") ;
		
		//获取我加入的社团
		try
		{
			User user = (User)request.getSession().getAttribute("user") ;
			List<Corporation> corporationList = this.userService.myJoinCorporation(user) ;
			//设置
			request.setAttribute("corporationList", corporationList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "系统异常") ; 
			return modelAndView ;
		}
				
		return modelAndView ;
	}
	
	@RequestMapping(path = "/forgetPasswordUI")
	public ModelAndView forgetPassowrdUI()
	{
		ModelAndView modelAndView = new ModelAndView("front/user/forgetPaswordUI")  ;
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: forgetPassword
	 * @Description: 忘记密码
	 * @param email
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/forgetPassword")
	public ModelAndView forgetPassword(@RequestParam("email") String email , HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("common/success") ; 
		
		//验证邮箱是否有效
		User user = new User() ; 
		user.setEmail(email);
		try
		{
			if(this.userService.selectIDByEmail(user) != null) 
			{
				//邮箱在用户列表
				//发送重置密码邮件
				String title = "社团网申系统重置密码邮件" ;
				String url = "http://"+request.getServerName()+":"+
						request.getServerPort()+request.getContextPath() +"/manager/user/resetPasswordUI?email="+email ;
				String content = "点击链接重置密码  <a href = '"+url+"' >" + url + "</a>" ;
				
				//设置校验码
				//TODO
				
				//保存校验码
				//TODO
				
				try
				{
					this.emailUtil.send(email, title, content) ;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					modelAndView.setViewName("common/error");
					modelAndView.addObject("errorMessage", "邮件发送失败") ;
					return modelAndView ;
				}
			}
			else
			{
				//邮箱不在用户列表
				modelAndView.setViewName("common/error");
				modelAndView.addObject("errorMessage", "该邮箱未注册") ;
				return modelAndView ;
			}
		}
		catch(Exception e)
		{
			//系统异常
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "系统异常") ;
			return modelAndView ;
		}
		
		modelAndView.addObject("successMessage", "已发送密码重置邮件到"+email) ;
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: modelAndView
	 * @Description: 重置密码界面
	 * @param email
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/resetPasswordUI")
	public ModelAndView resetPasswordUI(@RequestParam("email") String email)
	{
		ModelAndView modelAndView = new ModelAndView("front/user/resetPasswordUI") ;
		
		try
		{
			User user = new User() ; 
			user.setEmail(email);
			if(this.userService.selectIDByEmail(user) != null)
			{
				//邮箱在用户列表
				modelAndView.addObject("email", email) ;
			}
			else
			{
				//邮箱不在用户列表
				modelAndView.setViewName("common/error");
				modelAndView.addObject("errorMessage", "邮箱未注册") ;
				return modelAndView ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/errror");
			modelAndView.addObject("errorMessage", "系统异常") ;
			return modelAndView ;
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: resetPassword
	 * @Description: 重置密码
	 * @param email
	 * @param newPassword
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/resetPassword")
	public ModelAndView resetPassword(@RequestParam("email") String email , 
								@RequestParam("newPassword") String newPassword)
	{
		ModelAndView modelAndView = new ModelAndView("common/success") ; 

		// 重置密码
		try
		{
			User user = new User() ; 
			user.setEmail(email);
			user = this.userService.selectIDByEmail(user) ;
			if(user == null)
			{
				//用户不存在
				modelAndView.setViewName("common/error");
				modelAndView.addObject("errorMessage" , "用户不存在") ;
				return modelAndView ;
			}
			else
			{
				user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
				this.userService.updateByPrimaryKeySelective(user) ; 
				modelAndView.addObject("successMessage", "重置密码成功") ;
				return modelAndView ;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage" , "系统异常") ;

		}

		return modelAndView ;
	}
	
	
}
