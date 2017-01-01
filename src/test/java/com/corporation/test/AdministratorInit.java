package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.corporation.mapper.ManagerMapper;
import com.corporation.po.Manager;

/**
 * 
 * @ClassName: AdministratorInit
 * @Description: 初始化超级管理员
 * @author: JJChen
 * @date: 2016年12月10日 下午9:38:45
 */
public class AdministratorInit
{
	@Test
	public void test() throws Exception
	{
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ManagerMapper managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper") ; 
		
		//添加超级管理员
		Manager manager = new Manager() ; 
		manager.setName("administrator");
		manager.setPassword(DigestUtils.md5DigestAsHex("administrator".getBytes()));
		managerMapper.insert(manager) ; 
		

	}
}
