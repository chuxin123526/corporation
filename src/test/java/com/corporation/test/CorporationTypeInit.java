package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.corporation.mapper.CorporationTypeMapper;
import com.corporation.po.CorporationType;

public class CorporationTypeInit
{
	@Test
	public void testInsert() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		
		CorporationTypeMapper corporationTypeMapper = (CorporationTypeMapper)applicationContext.getBean("corporationTypeMapper") ;
		
		CorporationType corporationType = new CorporationType() ; 
		corporationType.setName("体育");
		corporationTypeMapper.insert(corporationType) ; 
		
		CorporationType corporationType1 = new CorporationType() ; 
		corporationType1.setName("休闲");
		corporationTypeMapper.insert(corporationType1) ; 
		

		CorporationType corporationType2 = new CorporationType() ; 
		corporationType2.setName("艺术特长");
		corporationTypeMapper.insert(corporationType2) ; 
		
		CorporationType corporationType3 = new CorporationType() ; 
		corporationType3.setName("公益服务");
		corporationTypeMapper.insert(corporationType3) ; 
		
		CorporationType corporationType4 = new CorporationType() ; 
		corporationType4.setName("文学");
		corporationTypeMapper.insert(corporationType4) ; 
	}
}
