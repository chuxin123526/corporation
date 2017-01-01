package com.corporation.web.vo;

/**
 * 
 * @ClassName: SearchCondition
 * @Description: 搜索条件
 * @author: JJChen
 * @date: 2016年11月29日 下午2:48:58
 */
public class SearchCondition
{
	private String keyword ; //关键字 
	private Long typeID ; //类型ID
	private int requestPage ; //请求页
	private int pageCount = 12 ; //每页显示的条目
	private int count ;

	public int getRequestPage()
	{
		return requestPage;
	}
	public void setRequestPage(int requestPage)
	{
		this.requestPage = requestPage;
	}
	public int getPageCount()
	{
		return pageCount;
	}
	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}
	public String getKeyword()
	{
		return keyword;
	}
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}
	public Long getTypeID()
	{
		return typeID;
	}
	public void setTypeID(Long typeID)
	{
		this.typeID = typeID;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	

}
