package com.corporation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.PrivilegeMapper;
import com.corporation.mapper.RoleMapper;
import com.corporation.mapper.Role_PrivilegeMapper;
import com.corporation.po.Privilege;
import com.corporation.po.Role;
import com.corporation.po.Role_Privilege;
import com.corporation.service.RoleManagerService;

@Service
@Transactional
public class RoleManagerServiceImpl implements RoleManagerService
{
	@Resource
	private RoleMapper roleMapper ;
	
	@Resource
	private PrivilegeMapper privilegeMapper ; 
	
	@Resource
	private Role_PrivilegeMapper role_privilegeMapper;

	@Override
	public List<Role> list() throws Exception
	{
		return this.roleMapper.list() ;
	}

	@Override
	public boolean delete(long roleID) throws Exception
	{
		if(this.roleMapper.deleteByPrimaryKey(roleID)!=0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean add(Role role) throws Exception
	{
		if(this.roleMapper.insert(role)!=0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public List<Privilege> topPrivilegeList() throws Exception
	{
		return this.privilegeMapper.topPrivilegeList() ; 
	}

	@Override
	public List<Role_Privilege> selectRole_PrivilegeListByRoleID(long roleID) throws Exception
	{
		return this.role_privilegeMapper.selectByRoleID(roleID) ;
	}

	@Override
	public Role selectByPrivilegeKey(long roleID) throws Exception
	{
		return roleMapper.selectByPrimaryKey(roleID) ; 
	}

	@Override
	public boolean allocatePrivilege(long[] privilegeIDS, long roleID) throws Exception
	{
		try
		{
			//将该角色原权限删除
			this.role_privilegeMapper.deleteByRoleID(roleID) ; 
			
			//添加权限
			for(long privilegeID : privilegeIDS)
			{
				Role_Privilege role_Privilege = new Role_Privilege() ; 
				role_Privilege.setRoleID(roleID);
				role_Privilege.setPrivilegeID(privilegeID);
				this.role_privilegeMapper.insert(role_Privilege) ; 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception() ;
		}
		
		return true ;
	}

	@Override
	public Privilege selectPrivilegeByPrimaryKey(long privilegeID) throws Exception
	{
		return this.privilegeMapper.selectByPrimaryKey(privilegeID) ; 
	}
	
}
