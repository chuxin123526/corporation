package com.corporation.po;

/**
 * 
 * @ClassName: CorporationType
 * @Description: 社团类型实体
 * @author: JJChen
 * @date: 2016年11月24日 下午3:54:41
 */
public class CorporationType
{
	private Long id; //ID

	private String name; // 类型名称

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