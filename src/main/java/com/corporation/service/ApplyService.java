package com.corporation.service;

import java.util.List;

import com.corporation.po.CorporationCreateApply;
import com.corporation.po.CorporationJoinApply;
import com.corporation.po.User_Corporation;
import com.corporation.web.vo.CorporationCreateApplyListCondition;
import com.corporation.web.vo.CorporationJoinApplyListConditin;
import com.corporation.web.vo.MyApplyCreateCorporationListCondition;
import com.corporation.web.vo.MyApplyJoinCorporationListCondition;

public interface ApplyService
{
	/**
	 * 
	 * @Title: applyJoinCorporation
	 * @Description: 申请加入社团
	 * @param corporationJoinApply
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean applyJoinCorporation(CorporationJoinApply corporationJoinApply) throws Exception ;
	
	/**
	 * 
	 * @Title: applyCreateCorporation
	 * @Description: 申请创建社团
	 * @param corporationCreateApply
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean applyCreateCorporation(CorporationCreateApply corporationCreateApply) throws Exception ;
	
	/**
	 * 
	 * @Title: myApplyJoinCorporationList
	 * @Description: 根据分页条件获取我申请加入的社团列表
	 * @param userID
	 * @return
	 * @throws Exception
	 * @return: List<CorporationJoinApply>
	 */
	public List<com.corporation.web.vo.CorporationJoinApply> myApplyJoinCorporationList(MyApplyJoinCorporationListCondition myApplyJoinCorporationListCondition ) throws Exception ;
	
	/**
	 * 
	 * @Title: myApplyCreateCorporationList
	 * @Description: 根据分页条件获取我申请创建的社团
	 * @param myApplyCreateCorporationListCondition
	 * @return
	 * @throws Exception
	 * @return: List<com.corporation.web.vo.CorporationCreateApply>
	 */
	public List<com.corporation.web.vo.CorporationCreateApply> myApplyCreateCorporationList(MyApplyCreateCorporationListCondition myApplyCreateCorporationListCondition) throws Exception ;
	
	/**
	 * 
	 * @Title: corporationJoinApplyList
	 * @Description: 根据社团ID和分页信息获取社团加入申请列表
	 * @param corporationJoinApplyListConditin
	 * @return
	 * @return: List<com.corporation.web.vo.CorporationJoinApply>
	 */
	public List<com.corporation.web.vo.CorporationJoinApply> corporationJoinApplyList(CorporationJoinApplyListConditin corporationJoinApplyListConditin) throws Exception; 
	
	/**
	 * 
	 * @Title: selectCorporationJoinApplyID
	 * @Description: 根据加入申请ID获取申请信息
	 * @param corporationJoinApplyID
	 * @return
	 * @return: com.corporation.web.vo.CorporationJoinApply
	 */
	public com.corporation.web.vo.CorporationJoinApply selectCorporationJoinApplyID(long corporationJoinApplyID) throws Exception; 
	
	/**
	 * 
	 * @Title: reviewJoinCorporationApply
	 * @Description: 审核加入社团申请,如通过，向该该社团增加成员，如未通过，修改申请状态
	 * @param corporationJoinApply
	 * @return
	 * @return: boolean
	 */
	public boolean reviewJoinCorporationApply(CorporationJoinApply corporationJoinApply , long corporationID , long userID) throws Exception; 
	
	/**
	 * 
	 * @Title: judgeUserWhetherBelongCorporation
	 * @Description: 根据用户ID和社团ID判断该用户是否属于该社团
	 * @param user_corporation
	 * @return
	 * @return: boolean
	 */
	public boolean judgeUserWhetherBelongCorporation(User_Corporation user_corporation) throws Exception;
	
	/**
	 * 
	 * @Title: corporationCreateApplyList
	 * @Description: 根据分页信息获取社团创建申请列表
	 * @param corporationCreateApplyListCondition
	 * @return
	 * @throws Exception
	 * @return: List<com.corporation.web.vo.CorporationCreateApply>
	 */
	public List<com.corporation.web.vo.CorporationCreateApply> corporationCreateApplyList(CorporationCreateApplyListCondition corporationCreateApplyListCondition) throws Exception ;
	
	/**
	 * 
	 * @Title: selectCorporationCreateApplyByID
	 * @Description: 根据社团创建申请ID获取申请信息
	 * @param corporationCreateApplyID
	 * @return
	 * @throws Exception
	 * @return: com.corporation.web.vo.CorporationCreateApply
	 */
	public com.corporation.web.vo.CorporationCreateApply selectCorporationCreateApplyByID(long corporationCreateApplyID) throws Exception ;
	
	/**
	 * 
	 * @Title: reviewCorporationCreateApply
	 * @Description: 处理社团创建申请
	 * @param corporationCreateApplyID
	 * @return
	 * @return: boolean
	 */
	public boolean reviewCorporationCreateApply(long corporationCreateApplyID , String handle) throws Exception  ; 
}
