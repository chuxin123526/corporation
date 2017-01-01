package com.corporation.service;

import java.util.List;

import com.corporation.po.Corporation;
import com.corporation.po.CorporationType;
import com.corporation.po.RecruitInfo;
import com.corporation.web.vo.RecruitInfoListCondition;

public interface CorporationService
{
	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查找社团
	 * @param id
	 * @return
	 * @throws Exception
	 * @return: Corporation
	 */
	public com.corporation.web.vo.Corporation selectByPrimaryKey(long id) throws Exception ;
	
	/**
	 * 
	 * @Title: publishRecruit
	 * @Description: 发布招募令
	 * @param recruitInfo
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean publishRecruit(RecruitInfo recruitInfo) throws Exception ;
	
	
	/**
	 * 
	 * @Title: udpateCorporationByPrimaryKeySelective
	 * @Description: 选择性修改社团信息
	 * @param corporation
	 * @return
	 * @return: boolean
	 */
	public boolean udpateCorporationByPrimaryKeySelective(Corporation corporation); 
	
	/**
	 * 
	 * @Title: logoutCorporation
	 * @Description: 注销社团
	 * @param corporationID
	 * @return
	 * @return: boolean
	 */
	public boolean logoutCorporation(long corporationID) throws Exception;
	
	/**
	 * 
	 * @Title: click
	 * @Description: 社团点击量加1
	 * @param corporationID
	 * @return: void
	 */
	public void click(long corporationID) throws Exception;
	
	/**
	 * 
	 * @Title: list
	 * @Description: 社团排行列表
	 * @return
	 * @throws Exception
	 * @return: List<Corporation>
	 */
	public List<Corporation> list() throws Exception ;
	
	/**
	 * 
	 * @Title: corporationTypeList
	 * @Description: 获取所有社团类型
	 * @return
	 * @throws Exception
	 * @return: List<CorporationType>
	 */
	public List<CorporationType> corporationTypeList() throws Exception ; 
	
	/**
	 * 
	 * @Title: recruitInfoList
	 * @Description: 获取招募令列表
	 * @param recruitListConditions
	 * @return
	 * @throws Exception
	 * @return: List<RecruitInfo>
	 */
	public List<com.corporation.web.vo.RecruitInfo> recruitInfoList(RecruitInfoListCondition recruitListConditions) throws Exception ;
	
	/**
	 * 
	 * @Title: recruitInfo
	 * @Description: 根据招募令ID查找详细招募信息
	 * @param recruitInfoID
	 * @return
	 * @return: com.corporation.web.vo.RecruitInfo
	 */
	public com.corporation.web.vo.RecruitInfo recruitInfo(long recruitInfoID) throws Exception; 
	
	/**
	 * 
	 * @Title: top4Corporation
	 * @Description: 查找排名前4的社团
	 * @return
	 * @throws Exception
	 * @return: List<Corporation>
	 */
	public List<Corporation> top4Corporation() throws Exception; 
	
	/**
	 * 
	 * @Title: selectNameByPrimaryKey
	 * @Description: 根据社团ID查找社团名字
	 * @param corporationID
	 * @return
	 * @throws Exception
	 * @return: Corporation
	 */
	public Corporation selectNameByPrimaryKey(long corporationID) throws Exception ; 
	
	/**
	 * 
	 * @Title: count
	 * @Description: 获取社团数量
	 * @return
	 * @return: int
	 */
	public int count()  throws Exception ;
	
	/**
	 * 
	 * @Title: recruitInfoCount
	 * @Description: 获取招募令总记录
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int recruitInfoCount() throws Exception ;
}
