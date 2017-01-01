package com.corporation.mapper;

import java.util.List;

import com.corporation.po.RecruitInfo;
import com.corporation.web.vo.RecruitInfoListCondition;

public interface RecruitInfoMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(RecruitInfo record);

    int insertSelective(RecruitInfo record);

    RecruitInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RecruitInfo record);

    int updateByPrimaryKeyWithBLOBs(RecruitInfo record);

    int updateByPrimaryKey(RecruitInfo record);
    
    /**
     * 
     * @Title: list
     * @Description: 获取招募令列表
     * @param recruitListCondition
     * @return
     * @return: List<RecruitInfo>
     */
    public List<com.corporation.web.vo.RecruitInfo> list(RecruitInfoListCondition recruitListCondition) ;
    
    /**
     * 
     * @Title: selectCount
     * @Description: 查找总记录
     * @return
     * @return: int
     */
    public int selectCount() ; 
    
    /**
     * 
     * @Title: recruitInfo
     * @Description: 根据招募令ID查找社团
     * @param corporationID
     * @return
     * @return: com.corporation.web.vo.RecruitInfo
     */
    public com.corporation.web.vo.RecruitInfo recruitInfo(long recruitInfoID) ;

}