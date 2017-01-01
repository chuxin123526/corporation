package com.corporation.po;

/**
 * 
 * @ClassName: Manager_Role
 * @Description: 管理员_角色的多对多关联
 * @author: JJChen
 * @date: 2016年11月24日 下午3:55:19
 */
public class Manager_Role
{
	private Long id; //ID

	private Long managerID; //管理员ID

	private Long roleID; //角色ID

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getManagerID()
	{
		return managerID;
	}

	public void setManagerID(Long managerID)
	{
		this.managerID = managerID;
	}

	public Long getRoleID()
	{
		return roleID;
	}

	public void setRoleID(Long roleID)
	{
		this.roleID = roleID;
	}


}