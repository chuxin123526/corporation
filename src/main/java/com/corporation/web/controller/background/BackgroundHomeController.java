package com.corporation.web.controller.background;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.service.CorporationService;
import com.corporation.service.UserService;

@Controller
@RequestMapping(path = "/backgroundHome")
public class BackgroundHomeController
{
	@Resource
	private CorporationService corporationServiceImpl ; 
	
	@Resource
	private UserService userServiceImpl ;
	
	@RequestMapping(path = "/index")
	public ModelAndView index() 
	{
		ModelAndView modelAndView = new ModelAndView("background/home/index") ; 
		
		try
		{
			//获取招募令总记录
			int recruitInfoCount = this.corporationServiceImpl.recruitInfoCount() ; 
			
			//获取社团总记录
			int corporationCount = this.corporationServiceImpl.count() ;
			
			//获取用户总数量
			int userCount  = this.userServiceImpl.count() ;
			
			//页面数据回显
			modelAndView.addObject("recruitInfoCount", recruitInfoCount) ; 
			modelAndView.addObject("corporationCount" , corporationCount) ; 
			modelAndView.addObject("userCount", userCount) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "参数异常") ; 
			modelAndView.setViewName("background/common/error") ;
		}
	
		
		return modelAndView ; 
	}
}
