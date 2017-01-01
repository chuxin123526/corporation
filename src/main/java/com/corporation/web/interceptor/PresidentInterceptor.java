package com.corporation.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corporation.mapper.CorporationMapper;
import com.corporation.po.User;
import com.corporation.service.CorporationService;
import com.corporation.web.vo.Corporation;

/**
 * 
 * @ClassName: PresidentInterceptor
 * @Description: 会长操作拦截器
 * @author: JJChen
 * @date: 2016年12月4日 下午2:40:29
 */
public class PresidentInterceptor extends HandlerInterceptorAdapter
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{

		try
		{
			// get corporationMapper
			ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(request.getServletContext());
			CorporationMapper corporationMapper =(CorporationMapper) applicationContext.getBean("corporationMapper") ;
			// 获取当前用户
			User user = (User) request.getSession().getAttribute("user");
			// 获取社团ID
			long corporationID = Long.parseLong(request.getParameter("corporationID"));

			// 获取社团信息
			Corporation corporation = corporationMapper.get(corporationID) ; 

			// 判断当前用户是否为协会会长
			if (corporation.getPresidentID() == user.getId())
			{
				// 当前用户是协会会长
				return true;
			} else
			{
				// 当前用户不是该协会会长
				request.setAttribute("errorMessage", "很抱歉，您不是该协会的会长");
				request.getRequestDispatcher("../user/forwardErrorPage").forward(request, response);
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数异常");
			request.getRequestDispatcher("../user/forwardErrorPage").forward(request, response);
			return false;
		}
	}
}
