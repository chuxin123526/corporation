package com.corporation.mapper;

import java.util.List;

import com.corporation.po.CorporationCreateApply;
import com.corporation.web.vo.CorporationCreateApplyListCondition;
import com.corporation.web.vo.MyApplyCreateCorporationListCondition;

public interface CorporationCreateApplyMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(CorporationCreateApply record);

    int insertSelective(CorporationCreateApply record);

    CorporationCreateApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CorporationCreateApply record);

    int updateByPrimaryKey(CorporationCreateApply record);
    
    /**
     * 
     * @Title: myApplyCreateCorporationList
     * @Description: 根据分页条件查找我申请创建的社团列表
     * @param myApplyCreateCorporationListCondition
     * @return
     * @return: List<com.corporation.web.vo.CorporationCreateApply>
     */
    public List<com.corporation.web.vo.CorporationCreateApply> myApplyCreateCorporationList(MyApplyCreateCorporationListCondition myApplyCreateCorporationListCondition) ;  ;
    
    /**
     * 
     * @Title: selectCount
     * @Description: 根据用户ID查询用户申请创建的社团总记录
     * @param userID
     * @return
     * @return: int
     */
    public int selectCount(long userID) ;
    
    /**
     * 
     * @Title: corporationCreateApplyList
     * @Description: 根据分页条件查找申请创建社团列表
     * @param corporationCreateApplyListCondition
     * @return
     * @return: List<com.corporation.web.vo.CorporationCreateApply>
     */
    public List<com.corporation.web.vo.CorporationCreateApply> corporationCreateApplyList(CorporationCreateApplyListCondition corporationCreateApplyListCondition) ; 
    
    /**
     * 
     * @Title: count
     * @Description: 社团创建申请总条目
     * @return
     * @return: int
     */
    public int count() ; 
    
    /**
     * 
     * @Title: selectCorporationCreateApplyByID
     * @Description: 根据社团创建申请ID查找申请信息
     * @param corporationCreateApplyID
     * @return
     * @return: com.corporation.web.vo.CorporationCreateApply
     */
    public com.corporation.web.vo.CorporationCreateApply selectCorporationCreateApplyByID(long corporationCreateApplyID) ;
    
}