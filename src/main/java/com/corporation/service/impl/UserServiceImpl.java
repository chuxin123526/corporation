package com.corporation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.CorporationMapper;
import com.corporation.mapper.UserMapper;
import com.corporation.po.Corporation;
import com.corporation.po.User;
import com.corporation.service.UserService;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户服务实现
 * @author: JJChen
 * @date: 2016年11月26日 上午11:36:35
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Resource
	private UserMapper userMapper ; 
	
	@Resource
	private CorporationMapper corporationMapper ;

	@Override
	public boolean checkUserNameAvailable(User user) throws Exception
	{
		boolean flag = false ; 
		//检查用户名是否可用
		if(userMapper.checkUserNameAvailable(user) == 0)
		{
			flag = true ; 
		}
		
		return flag ; 
	}

	@Override
	public boolean register(User user) throws Exception
	{
		if(this.userMapper.insertSelective(user) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public User selectIDByEmail(User user) throws Exception
	{
		return this.userMapper.selectIDByEmail(user) ;
	}

	@Override
	public boolean checkEmailAvailable(User user) throws Exception
	{
		if(this.userMapper.checkEmailAvailable(user) == 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean activateEmail(User user) throws Exception
	{
		if(this.userMapper.updateByPrimaryKeySelective(user) == 1)
		{
			//激活成功
			return true ; 
		}
		else
		{
			//激活失败
			return false ;
		}
	}

	@Override
	public boolean checkUserByNameAndPassword(User user) throws Exception
	{
		if(this.userMapper.checkUserByNameAndPassword(user) == 1)
		{
			//用户名和密码正确
			return true ; 
		}
		else
		{
			//用户名或密码错误
			return false ;
		}
	}

	@Override
	public User selectIDbyUserName(User user) throws Exception
	{
		return this.userMapper.selectIDbyUserName(user) ;
	}

	@Override
	public User selectUserByPrimaryKey(User user) throws Exception
	{
		return this.userMapper.selectByPrimaryKey(user.getId()) ;
	}

	@Override
	public boolean updateByPrimaryKeySelective(User user) throws Exception
	{
		System.out.println(user.getStatus());
		System.out.println("--------------");
		if(this.userMapper.updateByPrimaryKeySelective(user) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public List<Corporation> myCorporation(User user) throws Exception
	{
		return this.corporationMapper.selectCorporationListByUserID(user.getId()) ;
	}

	@Override
	public List<Corporation> myJoinCorporation(User user) throws Exception
	{
		return this.corporationMapper.selectMyJoinCorporationListByUserID(user.getId()) ;
	}

	@Override
	public int count() throws Exception
	{
		return this.userMapper.count() ;
	}
	
}
