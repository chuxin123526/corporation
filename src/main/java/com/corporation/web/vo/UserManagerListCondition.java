package com.corporation.web.vo;

public class UserManagerListCondition
{
	private int requestPage ;
	private int pageCount = 12;
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
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	
	
}
