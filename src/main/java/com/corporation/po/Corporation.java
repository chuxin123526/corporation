package com.corporation.po;

import java.util.Date;

public class Corporation implements Comparable<Corporation>
{
	private Long id;

	private String name;

	private Integer ranking;

	private String address;

	private String email;

	private String phoneNumber;

	private Long presidentID;

	private Date registerTime;

	private String imageUrl;

	private Long click;

	private Long typeID;

	private String introduction;

	private int memberAmount;

	
	public int getMemberAmount()
	{
		return memberAmount;
	}

	public void setMemberAmount(int memberAmount)
	{
		this.memberAmount = memberAmount;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public Integer getRanking()
	{
		return ranking;
	}

	public void setRanking(Integer ranking)
	{
		this.ranking = ranking;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address == null ? null : address.trim();
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email == null ? null : email.trim();
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public Long getPresidentID()
	{
		return presidentID;
	}

	public void setPresidentID(Long presidentID)
	{
		this.presidentID = presidentID;
	}

	public Date getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(Date registerTime)
	{
		this.registerTime = registerTime;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	}

	public Long getClick()
	{
		return click;
	}

	public void setClick(Long click)
	{
		this.click = click;
	}

	public Long getTypeID()
	{
		return typeID;
	}

	public void setTypeID(Long typeID)
	{
		this.typeID = typeID;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction == null ? null : introduction.trim();
	}

	@Override
	public int compareTo(Corporation o)
	{
		return o.getClick().compareTo(this.click) ;
	}
}