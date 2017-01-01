package com.corporation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.CorporationMapper;
import com.corporation.mapper.MemberPositionMapper;
import com.corporation.mapper.User_CorporationMapper;
import com.corporation.mapper.User_MemberPositionMapper;
import com.corporation.po.MemberPosition;
import com.corporation.po.User;
import com.corporation.po.User_Corporation;
import com.corporation.po.User_MemberPosition;
import com.corporation.service.MemberManagerService;
import com.corporation.web.vo.MemberListCondition;

@Service
@Transactional
public class MemberManagerServiceImpl implements MemberManagerService
{
	@Resource
	private CorporationMapper corporationMapper ; 
	
	@Resource
	private MemberPositionMapper memberPositionMapper ;
	
	@Resource
	private User_MemberPositionMapper user_memberPositionMapper ; 
	
	@Resource
	private User_CorporationMapper user_corporationMapper ; 

	@Override
	public List<User> list(MemberListCondition memberListCondition) throws Exception
	{
		//设置总页数
		int count = this.corporationMapper.selectMemberCount(memberListCondition.getCorporationID()) ; 
		if(count == 0)
		{
			memberListCondition.setCount(1);
		}
		else
			if(count%memberListCondition.getPageCount() == 0)
			{
				memberListCondition.setCount(count/memberListCondition.getPageCount());
			}
			else
			{
				memberListCondition.setCount(count/memberListCondition.getPageCount()+1);
			}
		
		if(memberListCondition.getRequestPage() > memberListCondition.getCount())
		{
			memberListCondition.setRequestPage(memberListCondition.getCount());
		}
		
		List<User> memberList = this.corporationMapper.memberList(memberListCondition) ; 
		
		for(User user : memberList)
		{
			//查找成员职位
			Map<String, Long> map = new HashMap<>() ; 
			map.put("corporationID", memberListCondition.getCorporationID()) ; 
			map.put("memberID" , user.getId()) ; 
			String memberPositionName = this.user_memberPositionMapper.selectMemberPositionNameByCorporationIDAndMemberID(map) ; 
			//设置成员职位
			user.setMemberPositionName(memberPositionName);
		}
		
		return memberList ;
	}

	@Override
	public List<MemberPosition> memberPositionList(long corporationID) throws Exception
	{
		return this.memberPositionMapper.memberPositionList(corporationID) ;
	}

	@Override
	public boolean addMemberPosition(MemberPosition memberPosition) throws Exception
	{
		if(this.memberPositionMapper.insert(memberPosition) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean deleteMemberPosition(long memberPositionID) throws Exception
	{
		if(this.memberPositionMapper.deleteByPrimaryKey(memberPositionID) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean setMemberPosition(User_MemberPosition user_memberPosition , long corporationID) throws Exception
	{
		//查找该社团是否为该成员设置过职位
		//设置成员职位，如果之前有设置，则修改，如果没有设置过，则添加
		Map<String, Long> hashmap = new HashMap<>() ; 
		hashmap.put("userID", user_memberPosition.getUserID()) ; 
		hashmap.put("corporationID", corporationID) ;
		User_MemberPosition user_MemberPosition2 = this.user_memberPositionMapper.selectByUserIDAndCorporationID(hashmap) ;
		if(user_MemberPosition2 != null)
		{
			//已经为该成员设置职位,更新职位信息
			user_MemberPosition2.setMemberPositionID(user_memberPosition.getMemberPositionID());
			this.user_memberPositionMapper.updateByPrimaryKeySelective(user_MemberPosition2) ; 
			return true ;
		}
		else
		{
			if(this.user_memberPositionMapper.insert(user_memberPosition) != 0)
			{
				return true ; 
			}
			else
			{
				return false ;
			}
		}
		
		
	}

	@Override
	public boolean kick(User_Corporation user_corporation) throws Exception
	{
		//移出成员
		int flag = this.user_corporationMapper.deleteByUserIDAndCorporationID(user_corporation) ; 
		//解除职位关系
		this.user_memberPositionMapper.deleteByUserID(user_corporation.getUserID()) ; 
		
		if(flag != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
		
	}

	@Override
	public User_MemberPosition selectByUserIDAndCorporationID(Map<String, Long> hashmap)
	{
		return this.user_memberPositionMapper.selectByUserIDAndCorporationID(hashmap) ;
	}

	@Override
	public int deleteUser_MemberPositionByPrimaryKey(long id) throws Exception
	{
		return this.user_memberPositionMapper.deleteByPrimaryKey(id) ;
	}

}
