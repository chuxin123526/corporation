package com.corporation.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corporation.po.User;

/**
 * 
 * @ClassName: LoginInterceptor
 * @Description: 用户登录拦截器
 * @author: JJChen
 * @date: 2016年12月4日 下午2:12:25
 */
public class LoginInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		User user = (User)request.getSession().getAttribute("user") ;
		if(user != null)
		{
			//用户已经登录
			return true ; 
		}
		else
		{
			//用户未登录
			request.setAttribute("errorMessage", "请先登录");
			request.getRequestDispatcher("../user/forwardErrorPage").forward(request, response);
			
			return false ; 
		}
	}
}
