package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Corporation;
import com.corporation.po.User;
import com.corporation.web.vo.UserManagerListCondition;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 
     * @Title: checkUserNameAvailable
     * @Description: 该用户名是否可用
     * @param user
     * @return
     * @return: int
     */
    public int checkUserNameAvailable(User user) ; 
    
    /**
     * 
     * @Title: selectIDByUserEmail
     * @Description: 根据邮箱获取用户ID
     * @param user
     * @return
     * @return: User
     */
    public User selectIDByEmail(User user) ;
    
    /**
     * 
     * @Title: checkEmailAvailable
     * @Description: 检查邮箱是否可用
     * @param user
     * @return
     * @return: int
     */
    public int checkEmailAvailable(User user) ;
    
    /**
     * 
     * @Title: checkUserNameAndPassword
     * @Description: 根据用户名和密码查找记录条数
     * @param user
     * @return
     * @return: int
     */
    public int checkUserByNameAndPassword(User user) ; 
    
    /**
     * 
     * @Title: selectIDbyUserName
     * @Description: 根据用户名查找用户ID
     * @param user
     * @return
     * @return: User
     */
    public User selectIDbyUserName(User user) ; 
    
    /**
     * 
     * @Title: count
     * @Description: 获取用户数量
     * @return
     * @return: int
     */
    public int count() ; 
    
    /**
     * 
     * @Title: list
     * @Description: 根据分页条件获取用户列表
     * @param userManagerListCondition
     * @return
     * @return: List<User>
     */
    public List<User> list(UserManagerListCondition userManagerListCondition) ; 

}