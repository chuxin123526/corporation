package com.corporation.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.UserMapper;
import com.corporation.po.User;
import com.corporation.test.service.UserService;

@Service("com.corporation.test.service.impl.userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService
{
	@Resource
	private UserMapper userMapper ;

	@Override
	public void insert() 
	{
		User user = new User() ; 
		user.setUserName("JJChen");
		this.userMapper.insertSelective(user) ;
		int i = 10/0 ;
	}
}
