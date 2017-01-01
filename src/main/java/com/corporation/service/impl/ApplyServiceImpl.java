package com.corporation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.CorporationCreateApplyMapper;
import com.corporation.mapper.CorporationJoinApplyMapper;
import com.corporation.mapper.CorporationMapper;
import com.corporation.mapper.User_CorporationMapper;
import com.corporation.po.Corporation;
import com.corporation.po.CorporationCreateApply;
import com.corporation.po.CorporationJoinApply;
import com.corporation.po.User_Corporation;
import com.corporation.service.ApplyService;
import com.corporation.web.vo.CorporationCreateApplyListCondition;
import com.corporation.web.vo.CorporationJoinApplyListConditin;
import com.corporation.web.vo.MyApplyCreateCorporationListCondition;
import com.corporation.web.vo.MyApplyJoinCorporationListCondition;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService
{
	@Resource
	private CorporationJoinApplyMapper corporationJoinApplyMapper ;
	
	@Resource
	private CorporationCreateApplyMapper corporationCreateApplyMapper ;
	
	@Resource
	private User_CorporationMapper user_corporationMapper ;
	
	@Resource
	private CorporationMapper corporationMapper ; 
	
	@Override
	public boolean applyJoinCorporation(CorporationJoinApply corporationJoinApply) throws Exception
	{
		if(this.corporationJoinApplyMapper.insertSelective(corporationJoinApply)!=0)
		{
			//保存申请记录成功
			return true ;
		}
		else
		{
			//保存申请记录失败
			return false ;
		}
	}

	@Override
	public boolean applyCreateCorporation(CorporationCreateApply corporationCreateApply) throws Exception
	{
		if(this.corporationCreateApplyMapper.insertSelective(corporationCreateApply) != 0)
		{
			//保存申请成功
			return true ;
		}
		else
		{
			//保存申请失败
			return false ;
		}
	}

	@Override
	public List<com.corporation.web.vo.CorporationJoinApply> myApplyJoinCorporationList(
			MyApplyJoinCorporationListCondition myApplyJoinCorporationListCondition) throws Exception
	{
		
		//设置分页信息
		int count = this.corporationJoinApplyMapper.selectCount(myApplyJoinCorporationListCondition.getUserID()) ; 
		if(count == 0)
		{
			myApplyJoinCorporationListCondition.setCount(1);
		}
		else
		{
			if(count%myApplyJoinCorporationListCondition.getPageCount() == 0)
			{
				myApplyJoinCorporationListCondition.setCount(count/myApplyJoinCorporationListCondition.getPageCount());
			}
			else
			{
				myApplyJoinCorporationListCondition.setCount(count/myApplyJoinCorporationListCondition.getPageCount() + 1);
			}
		}
		
		//根据分页条件查找
		List<com.corporation.web.vo.CorporationJoinApply> corporationJoinApplyList = this.corporationJoinApplyMapper.myApplyJoinCorporationList(myApplyJoinCorporationListCondition) ; 
		
		return corporationJoinApplyList ;
	}

	@Override
	public List<com.corporation.web.vo.CorporationCreateApply> myApplyCreateCorporationList(
			MyApplyCreateCorporationListCondition myApplyCreateCorporationListCondition) throws Exception
	{
		//设置分页信息
		int count = this.corporationCreateApplyMapper.selectCount(myApplyCreateCorporationListCondition.getUserID()) ; 
		if(count == 0)
		{
			myApplyCreateCorporationListCondition.setCount(1);
		}
		else
		{
			if(count%myApplyCreateCorporationListCondition.getPageCount() == 0)
			{
				myApplyCreateCorporationListCondition.setCount(count/myApplyCreateCorporationListCondition.getPageCount()) ; 
			}
			else
			{
				myApplyCreateCorporationListCondition.setCount(count/myApplyCreateCorporationListCondition.getPageCount() + 1) ; 
			}
		}
		
		//查找我申请创建的社团列表
		List<com.corporation.web.vo.CorporationCreateApply> list = this.corporationCreateApplyMapper.myApplyCreateCorporationList(myApplyCreateCorporationListCondition) ; 
		
		return list ;
	}

	@Override
	public List<com.corporation.web.vo.CorporationJoinApply> corporationJoinApplyList(
			CorporationJoinApplyListConditin corporationJoinApplyListConditin) throws Exception
	{
		//设置分页信息
		int count = this.corporationJoinApplyMapper.selectCountByCorporationID(corporationJoinApplyListConditin.getCorporationID()) ; 
		if(count == 0)
		{
			corporationJoinApplyListConditin.setCount(1);
		}
		else
		{
			if(count%corporationJoinApplyListConditin.getPageCount() == 0)
			{
				corporationJoinApplyListConditin.setCount(count/corporationJoinApplyListConditin.getPageCount()) ; 
			}
			else
			{
				corporationJoinApplyListConditin.setCount(count/corporationJoinApplyListConditin.getPageCount() + 1) ; 
			}
		}
		
		//查找我申请创建的社团列表
		List<com.corporation.web.vo.CorporationJoinApply> list = this.corporationJoinApplyMapper.corporationJoinApplyList(corporationJoinApplyListConditin) ; 
				
		return list ;
	}

	@Override
	public com.corporation.web.vo.CorporationJoinApply selectCorporationJoinApplyID(long corporationJoinApplyID) throws Exception
	{
		return this.corporationJoinApplyMapper.selectByCorporationJoinApplyID(corporationJoinApplyID) ;
	}

	@Override
	public boolean reviewJoinCorporationApply(CorporationJoinApply corporationJoinApply , long corporationID , long userID) throws Exception
	{
		if(corporationJoinApply.getStatus().equals("已通过"))
		{
			//向该社团增加成员
			User_Corporation user_Corporation = new User_Corporation() ; 
			user_Corporation.setUserID(userID);
			user_Corporation.setCorporationID(corporationID);
			if(this.user_corporationMapper.insert(user_Corporation) ==0) 
			{
				//插入记录失败
				throw new Exception("插入记录失败") ;
			}
			
		}
		
		//更新申请信息状态
		if(this.corporationJoinApplyMapper.updateByPrimaryKeySelective(corporationJoinApply) != 0)
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean judgeUserWhetherBelongCorporation(User_Corporation user_corporation) throws Exception
	{
		if(this.user_corporationMapper.selectByUserIDAndCorporationID(user_corporation) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public List<com.corporation.web.vo.CorporationCreateApply> corporationCreateApplyList(
			CorporationCreateApplyListCondition corporationCreateApplyListCondition) throws Exception
	{
		//设置分页条件
		int count = this.corporationCreateApplyMapper.count() ; 
		if(count == 0)
		{
			corporationCreateApplyListCondition.setCount(1);
		}
		else
		{
			if(count%corporationCreateApplyListCondition.getPageCount() == 0)
			{
				corporationCreateApplyListCondition.setCount(count/corporationCreateApplyListCondition.getPageCount());
			}
			else
			{
				corporationCreateApplyListCondition.setCount(count/corporationCreateApplyListCondition.getPageCount()+1);
			}
		}
		
		return this.corporationCreateApplyMapper.corporationCreateApplyList(corporationCreateApplyListCondition) ; 
	}

	@Override
	public com.corporation.web.vo.CorporationCreateApply selectCorporationCreateApplyByID(long corporationCreateApplyID)
			throws Exception
	{
		return this.corporationCreateApplyMapper.selectCorporationCreateApplyByID(corporationCreateApplyID) ; 
	}

	@Override
	public boolean reviewCorporationCreateApply(long corporationCreateApplyID , String handle) throws Exception 
	{
		
		
		//获取申请信息
		CorporationCreateApply corporationCreateApply = this.corporationCreateApplyMapper.selectByPrimaryKey(corporationCreateApplyID) ; 
		if(handle.equals("通过"))
		{
			//修改申请状态
			corporationCreateApply.setStatus("已通过");
			this.corporationCreateApplyMapper.updateByPrimaryKeySelective(corporationCreateApply) ;
			//封装
			Corporation corporation = new Corporation() ; 
			corporation.setAddress(corporationCreateApply.getAddress());
			corporation.setClick((long)0);
			corporation.setEmail(corporationCreateApply.getEmail());
			corporation.setIntroduction(corporationCreateApply.getIntroduction());
			corporation.setName(corporationCreateApply.getName());
			corporation.setPhoneNumber(corporationCreateApply.getPhoneNumber());
			corporation.setPresidentID(corporationCreateApply.getUserID());
			corporation.setTypeID(corporationCreateApply.getCorporationTypeID());
			//创建社团
			if(this.corporationMapper.insert(corporation)!=0)
			{
				return true ;
			}
			else
			{
				return false ;
			}
		}
		else
		{
			//修改申请状态
			corporationCreateApply.setStatus("未通过");
			if(this.corporationCreateApplyMapper.updateByPrimaryKeySelective(corporationCreateApply)!=0)
			{
				return true ; 
			}
			else
			{
				return false ;
			}
			
		}
		
		
		
	}
}
