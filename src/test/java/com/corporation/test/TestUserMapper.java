package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.corporation.mapper.UserMapper;
import com.corporation.po.User;

/**
 * 
 * @ClassName: TestUserMapper
 * @Description: 测试用户Mapper
 * @author: JJChen
 * @date: 2016年11月25日 上午8:39:32
 */
public class TestUserMapper
{
	/**
	 * 
	 * @Title: testUserInsert
	 * @Description: 测试增加一条用户记录
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testUserInsert() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		UserMapper userMapper = (UserMapper)applicationContext.getBean("userMapper") ; 
		User user = new User() ; 
		user.setUserName("JJChen");
		user.setPassword(DigestUtils.md5DigestAsHex(new String("test").getBytes()));
		userMapper.insertSelective(user) ;
	}
	
	/**
	 * 
	 * @Title: testSelectIDByUserName
	 * @Description: 测试根据用户名查找用户ID
	 * @return: void
	 */
	@Test
	public void testSelectIDByUserName()
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		UserMapper userMapper = (UserMapper)applicationContext.getBean("userMapper") ; 
		User user = new User() ; 
		user.setUserName("Somebody");
		user = userMapper.selectIDbyUserName(user) ; 
		System.out.println(user);
	}
	
	@Test
	public void testSelectByPrimaryKey() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		UserMapper userMapper = (UserMapper)applicationContext.getBean("userMapper") ; 
		User user = new User() ; 
		user.setId((long)26);
		user = userMapper.selectByPrimaryKey(user.getId()) ;
	}

}
