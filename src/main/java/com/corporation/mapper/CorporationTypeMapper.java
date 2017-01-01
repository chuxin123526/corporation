package com.corporation.mapper;

import com.corporation.po.CorporationType;

public interface CorporationTypeMapper 
{
    int deleteByPrimaryKey(Long id);

    int insert(CorporationType record);

    int insertSelective(CorporationType record);

    CorporationType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CorporationType record);

    int updateByPrimaryKey(CorporationType record);
}