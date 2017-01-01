package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.corporation.mapper.UserMapper;
import com.corporation.po.User;

/**
 * 
 * @ClassName: TestIntegration
 * @Description: 测试集成
 * @author: JJChen
 * @date: 2016年11月24日 下午5:17:24
 */
public class TestIntegration
{
	@Test
	public void test() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		
	}
}
