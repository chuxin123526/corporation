package com.corporation.mapper;

import com.corporation.po.User_Corporation;

public interface User_CorporationMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(User_Corporation record);

    int insertSelective(User_Corporation record);

    User_Corporation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User_Corporation record);

    int updateByPrimaryKey(User_Corporation record);
    
    /**
     * 
     * @Title: deleteByUserIDAndCorporationID
     * @Description: 根据社团ID和成员ID将成员移出社团
     * @param user_corporation
     * @return
     * @return: int
     */
    public int deleteByUserIDAndCorporationID(User_Corporation user_corporation) ; 
    
    /**
     * 
     * @Title: selectByUserIDAndCorporationID
     * @Description: 根据用户ID和社团ID查找用户与该社团的关系
     * @param user_corporation
     * @return
     * @return: int
     */
    public int selectByUserIDAndCorporationID(User_Corporation user_corporation) ;
}