package com.corporation.service;

import java.util.List;

import com.corporation.po.Corporation;
import com.corporation.po.User;

/**
 * 
 * @ClassName: UserService
 * @Description: 用户服务
 * @author: JJChen
 * @date: 2016年11月26日 上午11:33:23
 */
public interface UserService
{
	/**
	 * 
	 * @Title: checkUserNameIfAlreadyRegister
	 * @Description: 用户名是否可用
	 * @return
	 * @return: boolean
	 */
	public boolean checkUserNameAvailable(User user) throws Exception; 
	
	/**
	 * 
	 * @Title: register
	 * @Description: 用户注册
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean register(User user) throws Exception ;
	
	/**
	 * 
	 * @Title: selectIDByUserEmail
	 * @Description: 根据邮箱获取用户ID
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: User
	 */
	public User selectIDByEmail(User user) throws Exception ; 
	
	/**
	 * 
	 * @Title: checkEmailAvailable
	 * @Description: 检查邮箱是否可用
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public boolean checkEmailAvailable(User user) throws Exception ;
	
	/**
	 * 
	 * @Title: activateEmail
	 * @Description: 根据用户ID激活邮箱
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean activateEmail(User user) throws Exception ; 
	
	/**
	 * 
	 * @Title: checkUserByNameAndPassword
	 * @Description: 根据用户名和密码验证用户
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean checkUserByNameAndPassword(User user) throws Exception ;  
	
	/**
	 * 
	 * @Title: selectIDbyUserName
	 * @Description: 根据用户名查找用户ID
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: User
	 */
	public User selectIDbyUserName(User user) throws Exception ;
	
	/**
	 * 
	 * @Title: selectUserByPrimaryKey
	 * @Description: 根据用户ID查找用户
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: User
	 */
	public User selectUserByPrimaryKey(User user) throws Exception ; 
	
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 可选择地更新用户
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean updateByPrimaryKeySelective(User user) throws Exception ; 
	
	/**
	 * 
	 * @Title: myCorporation
	 * @Description: 查找我的社团
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: List<Corporation>
	 */
	public List<Corporation> myCorporation(User user) throws Exception ; 
	
	/**
	 * 
	 * @Title: myJoinCorporation
	 * @Description: 查找我加入的社团
	 * @param user
	 * @return
	 * @throws Exception
	 * @return: List<Corporation>
	 */
	public List<Corporation> myJoinCorporation(User user) throws Exception ;
	
	/**
	 * 
	 * @Title: count
	 * @Description: 获取用户数量
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int count() throws Exception ; 
	
	
	
}
