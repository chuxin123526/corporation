package com.corporation.service;

import java.util.List;

import com.corporation.po.User;
import com.corporation.web.vo.UserManagerListCondition;

public interface UserManagerService
{
	/**
	 * 
	 * @Title: list
	 * @Description: 根据分页条件或用户列表
	 * @param userManagerListCondition
	 * @return
	 * @throws Exception
	 * @return: List<User>
	 */
	public List<User> list(UserManagerListCondition userManagerListCondition) throws Exception ;
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除用户
	 * @param userID
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean delete(long userID) throws Exception ; 
}
