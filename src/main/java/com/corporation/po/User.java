package com.corporation.po;

import java.util.Date;

/**
 * 
 * @ClassName: User
 * @Description: 用户实体
 * @author: JJChen
 * @date: 2016年11月24日 下午4:03:16
 */
public class User
{
	private Long id; //ID

	private String userName; //用户名

	private String password; //密码

	private Date registerTime; //注册时间

	private String sex; //性别

	private String realName; //真实姓名

	private String phoneNumber; //电话号码

	private String email; //邮箱

	private String college; //学院

	private String major; //专业

	private String period; //年级
	
	private String status ; //用户状态 ，0 未激活，1 已激活，2 禁用
	
	private String introduction ;
	
	private String memberPositionName ; //成员职位名称，社团成员列表页面显示
	
	public String getMemberPositionName()
	{
		return memberPositionName;
	}

	public void setMemberPositionName(String memberPositionName)
	{
		this.memberPositionName = memberPositionName;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(Date registerTime)
	{
		this.registerTime = registerTime;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major = major;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
	}

}