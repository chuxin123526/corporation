package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.corporation.mapper.UserMapper;
import com.corporation.po.User;

public class UserInit
{
	@Test
	public void testInsert() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		
		UserMapper userMapper = (UserMapper)applicationContext.getBean("userMapper") ; 
		User user = new User() ; 
		user.setUserName("testUser1");
		user.setPassword(DigestUtils.md5DigestAsHex("somebody".getBytes()));
		user.setStatus("1");
		userMapper.insert(user) ; 
	}
}
