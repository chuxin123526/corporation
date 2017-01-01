package com.corporation.mapper;

import java.util.List;
import java.util.Map;

import com.corporation.po.CorporationJoinApply;
import com.corporation.web.vo.CorporationJoinApplyListConditin;
import com.corporation.web.vo.MyApplyJoinCorporationListCondition;

public interface CorporationJoinApplyMapper
{
	int deleteByPrimaryKey(Long id);

	int insert(CorporationJoinApply record);

	int insertSelective(CorporationJoinApply record);

	CorporationJoinApply selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CorporationJoinApply record);

	int updateByPrimaryKey(CorporationJoinApply record);
	
	/**
	 * 
	 * @Title: myApplyJoinCorporation
	 * @Description:根据userID和分页条件 获取我申请加入的社团列表
	 * @param userID
	 * @return
	 * @return: List<CorporationJoinApply>
	 */
	public List<com.corporation.web.vo.CorporationJoinApply> myApplyJoinCorporationList(MyApplyJoinCorporationListCondition myApplyJoinCorporationListCondition) ; 
	
	/**
	 * 
	 * @Title: selectCount
	 * @Description: 根据userID获取申请加入社团总记录
	 * @return
	 * @return: int
	 */
	public int selectCount(long userID) ; 
	
	/**
	 * 
	 * @Title: corporationJoinApplyList
	 * @Description: 根据社团ID和分页条件获取社团申请加入列表
	 * @param corporationID
	 * @return
	 * @return: List<CorporationJoinApply>
	 */
	public List<com.corporation.web.vo.CorporationJoinApply> corporationJoinApplyList(CorporationJoinApplyListConditin corporationJoinApplyListConditin) ;
	
	/**
	 * 
	 * @Title: selectCountByCorporationID
	 * @Description: 根据社团ID获取申请加入社团总记录
	 * @param corporationID
	 * @return
	 * @return: int
	 */
	public int selectCountByCorporationID(long corporationID) ;
	
	/**
	 * 
	 * @Title: selectByCorporationJoinApplyID
	 * @Description: 根据社团加入申请ID获取用户申请信息
	 * @param corporationJoinApplyID
	 * @return
	 * @return: com.corporation.web.vo.CorporationJoinApply
	 */
	public com.corporation.web.vo.CorporationJoinApply selectByCorporationJoinApplyID(long corporationJoinApplyID) ;
}