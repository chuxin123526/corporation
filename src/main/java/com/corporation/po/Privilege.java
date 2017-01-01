package com.corporation.po;

/**
 * 
 * @ClassName: Privilege
 * @Description: 权限实体
 * @author: JJChen
 * @date: 2016年11月24日 下午3:57:26
 */
public class Privilege
{
	private Long id; //ID

	private String name; //权限名称

	private String url; //权限地址

	private Long parentID; //父级权限ID

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

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url == null ? null : url.trim();
	}

	public Long getParentID()
	{
		return parentID;
	}

	public void setParentID(Long parentID)
	{
		this.parentID = parentID;
	}


}