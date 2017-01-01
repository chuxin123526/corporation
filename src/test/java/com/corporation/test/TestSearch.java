package com.corporation.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.corporation.mapper.CorporationMapper;
import com.corporation.po.Corporation;
import com.corporation.web.vo.SearchCondition;

public class TestSearch
{
	@Test
	public void search() throws Exception
	{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		CorporationMapper corporationMapper =(CorporationMapper) applicationContext.getBean("corporationMapper") ; 
		SearchCondition searchCondition = new SearchCondition() ; 
		searchCondition.setKeyword("%协会%");
		searchCondition.setTypeID((long)1);
		searchCondition.setRequestPage(2);
		List<Corporation> corporationList = corporationMapper.search(searchCondition) ;
		System.out.println(corporationList.size());
		for(Corporation corporation : corporationList)
		{
			System.out.println(corporation.getName());
		}
	}
}
