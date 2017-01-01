package com.corporation.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corporation.po.Manager;
import com.corporation.po.User;

public class ManagerLoginInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		Manager manager = (Manager)request.getSession().getAttribute("manager") ;
		if(manager != null)
		{
			//管理员已经登录
			return true ; 
		}
		else
		{
			//未登录
			request.setAttribute("errorMessage", "请先登录");
			request.getRequestDispatcher("../manager/forwardErrorPage").forward(request, response);
			
			return false ; 
		}
	}
}
