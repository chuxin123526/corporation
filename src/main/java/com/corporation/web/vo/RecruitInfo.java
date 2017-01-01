package com.corporation.web.vo;

import java.util.Date;

public class RecruitInfo
{
	private long id ; 
	private String imageUrl ;
	private String title ; 
	private String content ;
	private String corporationName ;
	private String corporationID ; 
	private Date beginTime ; 
	private Date endTime ;
	private int amount ; 
	private int cost ;
	private Date publishTime ; 
	
	public Date getPublishTime()
	{
		return publishTime;
	}
	public void setPublishTime(Date publishTime)
	{
		this.publishTime = publishTime;
	}
	public Date getBeginTime()
	{
		return beginTime;
	}
	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	public int getCost()
	{
		return cost;
	}
	public void setCost(int cost)
	{
		this.cost = cost;
	}
	public String getCorporationID()
	{
		return corporationID;
	}
	public void setCorporationID(String corporationID)
	{
		this.corporationID = corporationID;
	}
	public String getCorporationName()
	{
		return corporationName;
	}
	public void setCorporationName(String corporationName)
	{
		this.corporationName = corporationName;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getImageUrl()
	{
		return imageUrl;
	}
	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
	
}
