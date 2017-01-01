package com.corporation.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.mapper.CorporationMapper;
import com.corporation.mapper.RecruitInfoMapper;
import com.corporation.po.Corporation;
import com.corporation.po.CorporationType;
import com.corporation.po.RecruitInfo;
import com.corporation.service.CorporationService;
import com.corporation.web.vo.RecruitInfoListCondition;

@Service
@Transactional
public class CorporationServiceImpl implements CorporationService
{
	@Resource
	private CorporationMapper corporationMapper ;

	@Resource
	private RecruitInfoMapper recruitInfoMapper ; 
	
	@Override
	public com.corporation.web.vo.Corporation selectByPrimaryKey(long id) throws Exception
	{
		//根据ID查找社团
		com.corporation.web.vo.Corporation corporation = this.corporationMapper.get(id) ;
		//统计成员人数
		corporation.setMemberAmount(this.corporationMapper.memberAmount(id));
		
		return corporation ;
	}

	@Override
	public boolean publishRecruit(RecruitInfo recruitInfo) throws Exception
	{
		if(this.recruitInfoMapper.insertSelective(recruitInfo) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean udpateCorporationByPrimaryKeySelective(Corporation corporation)
	{
		if(this.corporationMapper.updateByPrimaryKeySelective(corporation) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public boolean logoutCorporation(long corporationID) throws Exception
	{
		if(this.corporationMapper.deleteByPrimaryKey(corporationID) != 0)
		{
			return true ; 
		}
		else
		{
			return false ;
		}
	}

	@Override
	public void click(long corporationID) throws Exception
	{
		this.corporationMapper.click(corporationID) ;
		
		//获取每个社团的点击量
		List<Corporation> clickList = this.corporationMapper.selectCorporationClickList() ;
		//排序
		Collections.sort(clickList); 
		//更新每个社团的排名
		for(int i = 0 ; i < clickList.size() ; i++)
		{
			clickList.get(i).setRanking(i+1);
			this.corporationMapper.updateByPrimaryKeySelective(clickList.get(i)) ;
		}
		
		
	}

	@Override
	public List<Corporation> list() throws Exception
	{
		return this.corporationMapper.list() ;
	}

	@Override
	public List<CorporationType> corporationTypeList() throws Exception
	{
		return this.corporationMapper.corporationTypeList() ;
	}

	@Override
	public List<com.corporation.web.vo.RecruitInfo> recruitInfoList(RecruitInfoListCondition recruitInfoListCondition) throws Exception
	{
		int count = this.recruitInfoMapper.selectCount() ;
		//设置总页数
		if(count%12 == 0)
		{
			recruitInfoListCondition.setCount(count/12);
		}
		else
		{
			recruitInfoListCondition.setCount(count/12+1);
		}
		
		return this.recruitInfoMapper.list(recruitInfoListCondition) ;
	}

	@Override
	public com.corporation.web.vo.RecruitInfo recruitInfo(long recruitInfoID) throws Exception
	{
		return this.recruitInfoMapper.recruitInfo(recruitInfoID) ;
	}

	@Override
	public List top4Corporation() throws Exception
	{
		return this.corporationMapper.top4Corporation() ;
	}

	@Override
	public Corporation selectNameByPrimaryKey(long corporationID) throws Exception
	{
		return this.corporationMapper.selectNameByPrimaryKey(corporationID) ; 
	}

	@Override
	public int count() throws Exception
	{
		return this.corporationMapper.count() ; 
	}

	@Override
	public int recruitInfoCount() throws Exception
	{
		return this.recruitInfoMapper.selectCount() ;
	}

}
