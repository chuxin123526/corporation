package com.corporation.mapper;

import java.util.List;

import com.corporation.po.Corporation;
import com.corporation.po.CorporationType;
import com.corporation.po.User;
import com.corporation.web.vo.MemberListCondition;
import com.corporation.web.vo.SearchCondition;

public interface CorporationMapper
{
    int deleteByPrimaryKey(Long id);

    int insert(Corporation record);

    int insertSelective(Corporation record);

    Corporation selectByPrimaryKey(Long id);
    
    /**
     * 
     * @Title: get
     * @Description: 根据社团ID获取社团信息
     * @param corporationID
     * @return
     * @return: com.corporation.web.vo.Corporation
     */
    public com.corporation.web.vo.Corporation get(long corporationID) ;
    
    /**
     * 
     * @Title: selectCorporationClick
     * @Description: 获取每个社团的点击量
     * @return
     * @return: List<Corporation>
     */
    public List<Corporation>  selectCorporationClickList() ; 

    int updateByPrimaryKeySelective(Corporation record);

    int updateByPrimaryKey(Corporation record);
    
    /**
     * 
     * @Title: selectCorporationListByUserID
     * @Description: 查找我的社团
     * @param userID
     * @return
     * @return: List<Corporation>
     */
    public List<Corporation> selectCorporationListByUserID(long userID) ;
    
    /**
     * 
     * @Title: selectCorporationMyJoinCorporationListByUserID
     * @Description: 查找我加入的社团
     * @param userID
     * @return
     * @return: List<Corporation>
     */
    public List<Corporation> selectMyJoinCorporationListByUserID(long userID) ;
    
    /**
     * 
     * @Title: click
     * @Description: 社团点击量加1
     * @param corporationID
     * @return
     * @return: int
     */
    public int click(Long corporationID) ;
    
    /**
     * 
     * @Title: list
     * @Description: 社团排行列表
     * @return
     * @return: List<Corporation>
     */
    public List<Corporation> list() ; 
    
    /**
     * 
     * @Title: search
     * @Description: 根据关键字搜索社团
     * @param keyword
     * @return
     * @return: List<Corporation>
     */
    public List<Corporation> search(SearchCondition searchCondition) ;
    
    /**
     * 
     * @Title: corporationTypeList
     * @Description: 获取所有社团类型
     * @return
     * @return: List<CorporationType>
     */
    public List<CorporationType> corporationTypeList() ; 
    
    /**
     * 
     * @Title: selectCount
     * @Description: 查找总记录条目
     * @return
     * @return: int
     */
    public int selectCount(SearchCondition searchCondition) ; 
    
    /**
     * 
     * @Title: top4Corporation
     * @Description: 查找排名前4的社团
     * @return
     * @return: List<Corporation>
     */
    public List<Corporation> top4Corporation() ; 
    
    /**
     * 
     * @Title: memberAmount
     * @Description: 统计成员人数
     * @param corporationID
     * @return
     * @return: int
     */
    public int memberAmount(long corporationID) ;
    
    /**
     * 
     * @Title: memberList
     * @Description: 获取成员列表
     * @param memberManagerListCondition
     * @return
     * @return: List<User>
     */
    public List<User> memberList(MemberListCondition memberManagerListCondition) ;
    
    /**
     * 
     * @Title: selectMemberCount
     * @Description: 根据社团ID获取成员总记录
     * @param corporationID
     * @return
     * @return: int
     */
    public int selectMemberCount(long corporationID) ;
    
    /**
     * 
     * @Title: selectNameByPrimaryKey
     * @Description: 根据社团ID查找社团名字
     * @param corporationID
     * @return
     * @return: Corporation
     */
    public Corporation selectNameByPrimaryKey(long corporationID) ; 
    
    /**
     * 
     * @Title: count
     * @Description: 获取社团数量
     * @return
     * @return: int
     */
    public int count() ; 
    
    
}