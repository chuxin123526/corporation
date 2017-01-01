package com.corporation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.CorporationMapper;
import com.corporation.po.Corporation;
import com.corporation.service.SearchService;
import com.corporation.web.vo.SearchCondition;

@Service
@Transactional
public class SearchServiceImpl implements SearchService
{
	@Resource
	private CorporationMapper corporationMapper ;

	@Override
	public List<Corporation> search(SearchCondition searchCondition) throws Exception
	{
		//设置关键字模糊搜索
		if(searchCondition.getKeyword() != null && !searchCondition.getKeyword().equals(""))
		{
			String keyword = searchCondition.getKeyword() ; 
			keyword = "%" + keyword + "%" ; 
			searchCondition.setKeyword(keyword);
		}
		//获取总记录
		int count = this.corporationMapper.selectCount(searchCondition) ;
		//设置总页数
		if(count == 0)
		{
			searchCondition.setCount(1);
		}
		else
			if(count%searchCondition.getPageCount() == 0)
			{
				searchCondition.setCount(count/searchCondition.getPageCount());
			}
			else
			{
				searchCondition.setCount(count/searchCondition.getPageCount()+1);
			}
		
		return this.corporationMapper.search(searchCondition) ;
	}

	
}
