package com.corporation.mapper;

import java.util.List;
import java.util.Map;

import com.corporation.po.MemberPosition;

public interface MemberPositionMapper
{
    int deleteByPrimaryKey(Long id);

    int insert(MemberPosition record);

    int insertSelective(MemberPosition record);

    MemberPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberPosition record);

    int updateByPrimaryKey(MemberPosition record);
    
    /**
     * 
     * @Title: memberPositionList
     * @Description: 获取成员职位列表
     * @return
     * @return: List<MemberPosition>
     */
    public List<MemberPosition> memberPositionList(long corporationID) ; 
    
   
}