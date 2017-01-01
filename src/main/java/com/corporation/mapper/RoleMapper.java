package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Role;

public interface RoleMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 
     * @Title: list
     * @Description: 获取角色列表
     * @return
     * @return: List<Role>
     */
    public List<Role> list() ; 
}