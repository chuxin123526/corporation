package com.corporation.web.vo;

import java.util.Date;

public class CorporationJoinApply {

	//以下为我申请加入的社团列表页面结果展示
    private Long id;

    private String status; //

    private Date applyTime;
    
    private String corporationName ;
    
    //以下为处理加入社团申请页面结果展示 , 和审核加入社团申请
    private String userName ;
    private String realName ;
    private String college ; 
    private String major ; 
    private String period ;
    private String phoneNumber ; 
    private String email ; 
    private String sex ;
    private String reason ; 
    private long userID ; 
    
    public long getUserID()
	{
		return userID;
	}

	public void setUserID(long userID)
	{
		this.userID = userID;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
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

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
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

	public String getCorporationName()
	{
		return corporationName;
	}

	public void setCorporationName(String corporationName)
	{
		this.corporationName = corporationName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


 
 

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}