package com.corporation.service;

import java.util.List;

import com.corporation.po.Corporation;
import com.corporation.web.vo.SearchCondition;

public interface SearchService
{
	/**
	 * 
	 * @Title: search
	 * @Description: 根据关键字搜索社团
	 * @param keyword
	 * @return
	 * @return: List<Corporation>
	 */
	public List<Corporation> search(SearchCondition searchCondition) throws Exception ; 
}
