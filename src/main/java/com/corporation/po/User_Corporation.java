package com.corporation.po;

/**
 * 
 * @ClassName: User_Corporation
 * @Description: 用户_社团的多对多关联
 * @author: JJChen
 * @date: 2016年11月24日 下午4:01:36
 */
public class User_Corporation
{
	private Long id; //ID

	private Long userID; //用户ID

	private Long corporationID; //社团ID

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUserID()
	{
		return userID;
	}

	public void setUserID(Long userID)
	{
		this.userID = userID;
	}

	public Long getCorporationID()
	{
		return corporationID;
	}

	public void setCorporationID(Long corporationID)
	{
		this.corporationID = corporationID;
	}

}