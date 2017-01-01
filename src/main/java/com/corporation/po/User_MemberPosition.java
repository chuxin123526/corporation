package com.corporation.po;

/**
 * 
 * @ClassName: User_MemberPosition
 * @Description: 用户_成员职位的多对多关联
 * @author: JJChen
 * @date: 2016年11月24日 下午4:02:35
 */
public class User_MemberPosition
{
	private Long id; //ID

	private Long userID; //用户ID

	private Long memberPositionID; //职位ID

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

	public Long getMemberPositionID()
	{
		return memberPositionID;
	}

	public void setMemberPositionID(Long memberPositionID)
	{
		this.memberPositionID = memberPositionID;
	}


}