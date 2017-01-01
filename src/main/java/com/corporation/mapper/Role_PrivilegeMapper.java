package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Role_Privilege;

public interface Role_PrivilegeMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Role_Privilege record);

    int insertSelective(Role_Privilege record);

    Role_Privilege selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role_Privilege record);

    int updateByPrimaryKey(Role_Privilege record);
    
    /**
     * 
     * @Title: selectByRoleID
     * @Description: 根据角色ID查找角色与权限的对应关系
     * @param roleID
     * @return
     * @return: List<Role_Privilege>
     */
    public List<Role_Privilege> selectByRoleID(long roleID) ; 
    
    public int deleteByRoleID(long roleID) ;
}