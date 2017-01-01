package com.corporation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.ManagerMapper;
import com.corporation.mapper.Manager_RoleMapper;
import com.corporation.po.Manager;
import com.corporation.po.Manager_Role;
import com.corporation.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService
{
	@Resource
	private ManagerMapper managerMapper ; 
	
	@Resource
	private Manager_RoleMapper manager_roleMapper ; 

	@Override
	public List<Manager> list() throws Exception
	{
		return this.managerMapper.list() ;
	}

	@Override
	public boolean delete(long managerID) throws Exception
	{
		if(this.managerMapper.deleteByPrimaryKey(managerID) != 0)
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean add(Manager manager) throws Exception
	{
		if(this.managerMapper.insert(manager) != 0)
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean checkByManagerNameAndPassword(Manager manager) throws Exception
	{
		Manager manager1 = this.managerMapper.selectByManagerNameAndPassword(manager) ;
		
		if(manager1 == null)
		{
			//未查找到管理员
			return false ; 
		}
		else
		{
			return true ;
		}
	}

	@Override
	public Manager selectByManagerNameAndPassword(Manager manager) throws Exception
	{
		return this.managerMapper.selectByManagerNameAndPassword(manager) ;
	}

	@Override
	public boolean updatePassword(Manager manager)
	{
		if(this.managerMapper.updateByPrimaryKeySelective(manager) != 0)
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}

	@Override
	public List<Manager_Role> selectManagerRoleByManagerID(long managerID) throws Exception 
	{
		return this.manager_roleMapper.selectByManagerID(managerID) ; 
	}

	@Override
	public Manager selectByManagerID(long managerID) throws Exception
	{
		return this.managerMapper.selectByPrimaryKey(managerID) ; 
	}

	@Override
	public boolean allocateRole(long[] roleIDS, long managerID) throws Exception 
	{
		try
		{
			//删除该管理员之前的角色
			this.manager_roleMapper.deleteByManagerID(managerID) ; 
			//给管理员分配角色
			for(long roleID : roleIDS)
			{
				Manager_Role manager_Role = new Manager_Role() ; 
				manager_Role.setManagerID(managerID); 
				manager_Role.setRoleID(roleID);
				this.manager_roleMapper.insert(manager_Role) ; 
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
	public boolean checkManagerNameAvailable(String managerName)
	{
		if(this.managerMapper.checkManagerNameAvailable(managerName) == 0)
		{
			//该管理员名字未出现在数据库,用户名可用
			return true ; 
		}
		else
		{
			//用户名可不用
			return false ; 
		}
	}

}
