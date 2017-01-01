package com.corporation.po;

/**
 * 
 * @ClassName: Role
 * @Description: 角色实体
 * @author: JJChen
 * @date: 2016年11月24日 下午4:00:58
 */
public class Role
{
	private Long id; //ID

	private String name; //角色名称

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
}