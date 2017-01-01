package com.corporation.web.vo;


public class MyApplyCreateCorporationListCondition
{
	private long userID ;
	private int requestPage;
	private int pageCount = 12;
	private int count;
	
	public long getUserID()
	{
		return userID;
	}
	public void setUserID(long userID)
	{
		this.userID = userID;
	}
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