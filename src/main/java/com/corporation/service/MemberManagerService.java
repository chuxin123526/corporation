package com.corporation.service;

import java.util.List;
import java.util.Map;

import com.corporation.mapper.User_CorporationMapper;
import com.corporation.po.MemberPosition;
import com.corporation.po.User;
import com.corporation.po.User_Corporation;
import com.corporation.po.User_MemberPosition;
import com.corporation.web.vo.MemberListCondition;

/**
 * 
 * @ClassName: MemberManagerService
 * @Description: 成员管理服务
 * @author: JJChen
 * @date: 2016年12月12日 上午12:24:12
 */
public interface MemberManagerService
{
	/**
	 * 
	 * @Title: list
	 * @Description: 根据分页条件和corporationID获取成员列表
	 * @param memberManagerListCondition
	 * @return
	 * @return: List<User>
	 */
	public List<User> list(MemberListCondition memberManagerListCondition) throws Exception ; 
	
	/**
	 * 
	 * @Title: memberPositionList
	 * @Description: 获取成员职位列表
	 * @return
	 * @return: List<MemberPosition>
	 */
	public List<MemberPosition> memberPositionList(long corporationID) throws Exception; 
	
	/**
	 * 
	 * @Title: addMemberPosition
	 * @Description: 增加成员职位
	 * @param memberPositionVO
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean addMemberPosition(MemberPosition memberPosition) throws Exception ; 
	
	/**
	 * 
	 * @Title: deleteMemberPosition
	 * @Description: 删除成员职位
	 * @param memberPositionVo
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean deleteMemberPosition(long memberPositionID) throws Exception ;
	
	/**
	 * 
	 * @Title: setMemberPosition
	 * @Description: 设置成员职位
	 * @param user_memberPosition
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean setMemberPosition(User_MemberPosition user_memberPosition , long corporationID) throws Exception ; 
	
	/**
	 * 
	 * @Title: kick
	 * @Description: 将成员移出社团
	 * @param user_corporation
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	public boolean kick(User_Corporation user_corporation) throws Exception ; 
	
	/**
	 * 
	 * @Title: selectByUserIDAndCorporationID
	 * @Description: 根据成员ID和社团ID获取职位
	 * @param hashmap
	 * @return
	 * @return: User_MemberPosition
	 */
	public User_MemberPosition selectByUserIDAndCorporationID(Map<String, Long> hashmap)  ;
	
	/**
	 * 
	 * @Title: deleteUser_MemberPositionByPrimaryKey
	 * @Description: 根据id删除成员与成员职位的关系
	 * @param id
	 * @return
	 * @return: int
	 */
	public int deleteUser_MemberPositionByPrimaryKey(long id)  throws Exception;
	
	
}
