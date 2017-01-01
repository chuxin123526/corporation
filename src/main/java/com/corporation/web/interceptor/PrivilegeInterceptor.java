package com.corporation.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corporation.mapper.Manager_RoleMapper;
import com.corporation.mapper.PrivilegeMapper;
import com.corporation.mapper.Role_PrivilegeMapper;
import com.corporation.po.Manager;
import com.corporation.po.Manager_Role;
import com.corporation.po.Privilege;
import com.corporation.po.Role_Privilege;


/**
 * 
 * @ClassName: PrivilegeInterceptor
 * @Description: 后台管理权限拦截器
 * @author: JJChen
 * @date: 2016年12月10日 下午4:05:21
 */
public class PrivilegeInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()) ; 
		//获取mapper
		Manager_RoleMapper manager_RoleMapper = (Manager_RoleMapper)applicationContext.getBean("manager_roleMapper") ; 
		Role_PrivilegeMapper role_PrivilegeMapper = (Role_PrivilegeMapper)applicationContext.getBean("role_privilegeMapper") ; 
		PrivilegeMapper privilegeMapper = (PrivilegeMapper)applicationContext.getBean("privilegeMapper") ; 
		
		//获取当前登录管理员
		Manager manager = (Manager)request.getSession().getAttribute("manager") ;
		
		//判断是否为超级管理员
		if(manager.getName().equals("administrator"))
		{
			//administrator为超级管理员
			return true ; 
		}
		
		//获取当前管理员所拥有的角色
		List<Manager_Role> manager_RoleList = manager_RoleMapper.selectByManagerID(manager.getId()) ; 
		//遍历角色
		for(Manager_Role manager_role : manager_RoleList)
		{
			//根据角色ID获取权限列表
			List<Role_Privilege> role_privilegeList = role_PrivilegeMapper.selectByRoleID(manager_role.getRoleID()) ; 
			
			//遍历权限
			for(Role_Privilege role_privilege : role_privilegeList)
			{
				//获取权限
				Privilege privilege = privilegeMapper.selectByPrimaryKey(role_privilege.getPrivilegeID()) ;
				//获取uri
				String uri = request.getRequestURI() ; 
				List<Privilege> childrenPrivilegeList = privilegeMapper.selectPrivilegeListByTopPrivilegeID(privilege.getId()) ; 
				//遍历子权限
				for(Privilege privilege2 : childrenPrivilegeList)
				{
					//比较访问的URI与权限URI
					if(uri.equals(privilege2.getUrl()))
					{
						//当前登录管理员拥有该权限
						return true ; 
					}
				}
				
				
			}
		}
		
		//当前登录管理员没有改权限
		request.setAttribute("errorMessage", "您没有该权限");
		request.getRequestDispatcher("../manager/forwardErrorPage").forward(request, response);
		
		return false ; 
	}
}
