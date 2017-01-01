package com.corporation.web.controller.background;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.User;
import com.corporation.service.UserManagerService;
import com.corporation.web.vo.UserManagerListCondition;

/**
 * 
 * @ClassName: UserManagerController
 * @Description: 用户管理请求
 * @author: JJChen
 * @date: 2016年12月5日 上午9:32:59
 */
@Controller
@RequestMapping(path = "/userManager")
public class UserManagerController
{
	@Resource
	private UserManagerService userManagerServiceImpl ; 
	
	/**
	 * 
	 * @Title: list
	 * @Description: 用户列表
	 * @param requestPage
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/list")
	public ModelAndView list(@RequestParam(value = "requestPage" , required = false) String requestPage)
	{
			ModelAndView modelAndView = new ModelAndView("background/userManager/list") ; 
			
			try
			{
				//设置分页条件
				UserManagerListCondition userManagerListCondition = new UserManagerListCondition() ; 
				if(requestPage == null  || requestPage.equals("") || requestPage.equals("0"))
				{
					userManagerListCondition.setRequestPage(1);
				}
				else
				{
					userManagerListCondition.setRequestPage(Integer.parseInt(requestPage));
				}
				
				//查询
				List<User> userList =  this.userManagerServiceImpl.list(userManagerListCondition) ; 
				
				//页面回显
				modelAndView.addObject("userList", userList) ; 
				modelAndView.addObject("requestPage", userManagerListCondition.getRequestPage()) ;
				modelAndView.addObject("count", userManagerListCondition.getCount()) ;
			}
			catch(Exception e)
			{
				modelAndView.addObject("errorMessage", "参数错误") ;
				modelAndView.setViewName("background/common/error") ;
				e.printStackTrace();
			}
			
			
			return modelAndView ;
	}
	
	@RequestMapping(path = "/delete")
	public String delete(@RequestParam("requestPage") String requestPage , 
							@RequestParam(value = "userID") String userID , 
							HttpServletRequest request)
	{
		try
		{
			if(this.userManagerServiceImpl.delete(Long.parseLong(userID)) == false)
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
		
		return "redirect:list?requestPage="+requestPage ;
	}
}
