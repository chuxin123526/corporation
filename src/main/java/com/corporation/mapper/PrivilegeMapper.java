package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Privilege;

public interface PrivilegeMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    /**
     * 
     * @Title: topPrivilegeList
     * @Description: 获取所有顶级权限
     * @return
     * @return: List<Privilge>
     */
    public List<Privilege> topPrivilegeList() ; 
    
    /**
     * 
     * @Title: selectPrivilegeListByTopPrivilegeID
     * @Description: 根据顶级权限ID获取子权限
     * @param topPrivilegeID
     * @return
     * @return: List<Privilege>
     */
    public List<Privilege> selectPrivilegeListByTopPrivilegeID(long topPrivilegeID) ; 
}