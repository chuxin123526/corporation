package com.corporation.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.corporation.po.User;
import com.corporation.test.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestTransactional
{
	@Resource(name = "com.corporation.test.service.impl.userServiceImpl")
	private UserService userService ; 

	@Test
	public void test() throws Exception
	{
		userService.insert(); 
	}
}
