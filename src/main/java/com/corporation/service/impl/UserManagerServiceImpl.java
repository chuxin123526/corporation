package com.corporation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.UserMapper;
import com.corporation.po.User;
import com.corporation.service.UserManagerService;
import com.corporation.web.vo.UserManagerListCondition;

@Service
@Transactional
public class UserManagerServiceImpl implements UserManagerService
{
	@Resource
	private UserMapper userMapper ; 

	@Override
	public List<User> list(UserManagerListCondition userManagerListCondition) throws Exception
	{
		//设置总页码
		int count = this.userMapper.count() ; 
		if(count == 0)
		{
			userManagerListCondition.setCount(1);
		}
		else
		{
			if(count%userManagerListCondition.getPageCount() == 0)
			{
				userManagerListCondition.setCount(count/userManagerListCondition.getPageCount());
			}
			else
			{
				userManagerListCondition.setCount(count/userManagerListCondition.getPageCount()+1);
			}
		}
		
		//查询用户列表
		List<User> list = this.userMapper.list(userManagerListCondition) ; 
		
		return list ;
	}

	@Override
	public boolean delete(long userID) throws Exception
	{
		if(this.userMapper.deleteByPrimaryKey(userID)!=0)
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}

}
