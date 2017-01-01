package com.corporation.service;

import java.util.List;

import com.corporation.po.Manager;
import com.corporation.po.Manager_Role;

public interface ManagerService
{
	/**
	 * 
	 * @Title: list
	 * @Description: 获取管理员列表
	 * @return
	 * @return: List<Manager>
	 */
	public List<Manager> list() throws Exception;
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除管理员
	 * @param managerID
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean delete(long managerID) throws Exception; 
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加管理员
	 * @param manager
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean add(Manager manager) throws Exception ;
	
	/**
	 * 
	 * @Title: checkManagerNameAvailable
	 * @Description: 检查用户名是否可用
	 * @param managerName
	 * @return
	 * @return: boolean
	 */
	public boolean checkManagerNameAvailable(String managerName) ; 
	
	/**
	 * 
	 * @Title: checkByManagerNameAndPassword
	 * @Description: 检查用户名和密码是否正确
	 * @param manager
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean checkByManagerNameAndPassword(Manager manager) throws Exception ; 
	
	/**
	 * 
	 * @Title: selectByManagerNameAndPassword
	 * @Description: 根据管理员用户名和密码获取管理员信息
	 * @param manager
	 * @return
	 * @throws Exception
	 * @return: Manager
	 */
	public Manager selectByManagerNameAndPassword(Manager manager) throws Exception ;
	
	/**
	 * 
	 * @Title: updatePassword
	 * @Description: 修改密码
	 * @param manager
	 * @return
	 * @return: boolean
	 */
	public boolean updatePassword(Manager manager) ;
	
	/**
	 * 
	 * @Title: selectManagerRoleByManagerID
	 * @Description: 根据管理员ID查找该管理员与角色的关系
	 * @param managerID
	 * @return
	 * @return: List<Manager_Role>
	 */
	public List<Manager_Role> selectManagerRoleByManagerID(long managerID) throws Exception; 
	
	/**
	 * 
	 * @Title: selectByManagerID
	 * @Description: 根据管理员ID获取管理员信息
	 * @param managerID
	 * @return
	 * @return: Manager
	 */
	public Manager selectByManagerID(long managerID) throws Exception; 
	
	/**
	 * 
	 * @Title: allocateRole
	 * @Description: 给管理员分配角色
	 * @param roleIDS
	 * @param managerID
	 * @return
	 * @return: boolean
	 */
	public boolean allocateRole(long[] roleIDS , long managerID) throws Exception; 
}
