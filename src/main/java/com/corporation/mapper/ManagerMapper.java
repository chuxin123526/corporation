package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Manager;

public interface ManagerMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    
    /**
     * 
     * @Title: list
     * @Description: 获取管理员列表 
     * @return
     * @return: List<Manager>
     */
    public List<Manager> list() ; 
    
    /**
     * 
     * @Title: selectByManagerNameAndPassword
     * @Description: 根据用户名密码查找用户
     * @param manager
     * @return
     * @return: Manager
     */
    public Manager selectByManagerNameAndPassword(Manager manager) ; 
    
    /**
     * 
     * @Title: checkManagerNameAvailable
     * @Description: 检查用户名是否可用
     * @param managerName
     * @return
     * @return: int
     */
    public int checkManagerNameAvailable(String managerName) ; 
}