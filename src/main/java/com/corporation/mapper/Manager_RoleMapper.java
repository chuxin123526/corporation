package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Manager_Role;

public interface Manager_RoleMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Manager_Role record);

    int insertSelective(Manager_Role record);

    Manager_Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Manager_Role record);

    int updateByPrimaryKey(Manager_Role record);
    
    /**
     * 
     * @Title: selectByManagerID
     * @Description: 根据管理员ID查找该管理员与角色的关系
     * @param managerID
     * @return
     * @return: List<Manager_Role>
     */
    public List<Manager_Role> selectByManagerID(long managerID) ; 
    
    /**
     * 
     * @Title: deleteByManagerID
     * @Description: 根据管理员ID删除与角色的关系
     * @param managerID
     * @return
     * @return: int
     */
    public int deleteByManagerID(long managerID) ; 
}