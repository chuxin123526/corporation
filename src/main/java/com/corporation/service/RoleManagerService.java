package com.corporation.service;

import java.util.List;

import com.corporation.po.Privilege;
import com.corporation.po.Role;
import com.corporation.po.Role_Privilege;

public interface RoleManagerService
{
	/**
	 * 
	 * @Title: list
	 * @Description: 角色列表
	 * @return
	 * @throws Exception
	 * @return: List<Role>
	 */
	public List<Role> list() throws Exception ;
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除角色
	 * @param roleID
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean delete(long roleID) throws Exception ; 
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加角色
	 * @param role
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean add(Role role) throws Exception ; 
	
	/**
	 * 
	 * @Title: topPrivilegeList
	 * @Description: 获取顶级权限
	 * @return
	 * @return: List<Privilege>
	 */
	public List<Privilege> topPrivilegeList() throws Exception; 
	
	/**
	 * 
	 * @Title: selectRole_PrivilegeListByRoleID
	 * @Description: 根据角色ID查找角色与权限的关系
	 * @param roleID
	 * @return
	 * @throws Exception
	 * @return: List<Role_Privilege>
	 */
	public List<Role_Privilege> selectRole_PrivilegeListByRoleID(long roleID) throws Exception ;
	
	/**
	 * 
	 * @Title: selectByPrivilegeKey
	 * @Description: 根据角色ID获取角色
	 * @param roleID
	 * @return
	 * @throws Excepetion
	 * @return: Role
	 */
	public Role selectByPrivilegeKey(long roleID) throws Exception  ; 
	
	/**
	 * 
	 * @Title: allocatePrivilege
	 * @Description: 分配权限
	 * @param privilegeIDS
	 * @param roleID
	 * @return
	 * @return: boolean
	 */
	public boolean allocatePrivilege(long[] privilegeIDS , long roleID) throws Exception; 
	
	/**
	 * 
	 * @Title: selectPrivilegeByPrimaryKey
	 * @Description: 根据权限ID查找权限
	 * @param privilegeID
	 * @return
	 * @throws Exception
	 * @return: Privilege
	 */
	public Privilege selectPrivilegeByPrimaryKey(long privilegeID) throws Exception ; 
}
