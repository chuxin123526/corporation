package com.corporation.mapper;

import java.util.Map;

import com.corporation.po.User_MemberPosition;

public interface User_MemberPositionMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(User_MemberPosition record);

    int insertSelective(User_MemberPosition record);

    User_MemberPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User_MemberPosition record);

    int updateByPrimaryKey(User_MemberPosition record);
    
    /**
     * 
     * @Title: deleteByUserID
     * @Description: 根据成员ID删除成员与职位关系
     * @param userID
     * @return
     * @return: int
     */
    public int deleteByUserID(long userID) ; 
    
    /**
     * 
     * @Title: selectCount
     * @Description: 根据成员ID和社团ID查找成员职位关系
     * @param user_memberPosition
     * @return
     * @return: User_MemberPosition
     */
    public User_MemberPosition selectByUserIDAndCorporationID(Map<String, Long> hashmap) ; 
    
    /**
     * 
     * @Title: selectMemberPositionNameByCorporationIDAndMemberID
     * @Description: 根据成员ID和社团ID查找成员职位名称
     * @return
     * @return: String
     */
    public String selectMemberPositionNameByCorporationIDAndMemberID(Map<String , Long> map) ; 
    
}