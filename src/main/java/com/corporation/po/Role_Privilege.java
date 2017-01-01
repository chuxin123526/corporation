package com.corporation.po;

/**
 * 
 * @ClassName: Role_Privilege
 * @Description: 角色_权限的多对多关联
 * @author: JJChen
 * @date: 2016年11月24日 下午4:00:04
 */
public class Role_Privilege
{
	private Long id; //ID

	private Long roleID; //角色ID

	private Long privilegeID; //权限ID

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getRoleID()
	{
		return roleID;
	}

	public void setRoleID(Long roleID)
	{
		this.roleID = roleID;
	}

	public Long getPrivilegeID()
	{
		return privilegeID;
	}

	public void setPrivilegeID(Long privilegeID)
	{
		this.privilegeID = privilegeID;
	}
	
	
}