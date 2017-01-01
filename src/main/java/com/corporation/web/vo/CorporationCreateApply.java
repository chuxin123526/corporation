package com.corporation.web.vo;

import java.util.Date;

public class CorporationCreateApply {
	
	//以下为审核社团创建申请页面结果展示
	private String introduction ; 
	
	//以下为申请创建社团列表页面结果展示
	private String userName ;
	
	
	
	//以下为我申请创建的社团列表页面结果展示
    private Long id;
    
    private Date applyTime;

    private String address;

    private String email;

    private String phoneNumber;

    private String status;

    private String name;
    
    private String corporationTypeName ;
    
    
    public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCorporationTypeName()
	{
		return corporationTypeName;
	}

	public void setCorporationTypeName(String corporationTypeName)
	{
		this.corporationTypeName = corporationTypeName;
	}

	public Long getId() {
        return id;
    }

 


    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}