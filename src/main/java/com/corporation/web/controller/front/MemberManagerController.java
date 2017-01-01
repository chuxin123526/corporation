package com.corporation.web.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Corporation;
import com.corporation.po.MemberPosition;
import com.corporation.po.User;
import com.corporation.po.User_Corporation;
import com.corporation.po.User_MemberPosition;
import com.corporation.service.CorporationService;
import com.corporation.service.MemberManagerService;
import com.corporation.service.UserService;
import com.corporation.web.vo.MemberListCondition;

@Controller
@RequestMapping(path = "/memberManager")
public class MemberManagerController
{
	@Resource
	private MemberManagerService memberManagerServiceImpl ;
	
	@Resource
	private CorporationService corporationServiceImpl ; 
	
	@Resource
	private UserService userServiceImpl ;
	
	/**
	 * 
	 * @Title: lsit
	 * @Description: 成员列表
	 * @param corporationID
	 * @param requestPage
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/list")
	public ModelAndView list(@RequestParam("corporationID") String corporationID , 
								@RequestParam(value = "requestPage" , required = false) String requestPage)
	{
		ModelAndView modelAndView = new ModelAndView("front/memberManager/list") ;
		
		//设置分页条件
		MemberListCondition memberListCondition = new MemberListCondition() ; 
		try
		{
			if(requestPage == null || requestPage.equals("") || requestPage.equals("0"))
			{
				memberListCondition.setRequestPage(1);
			}
			else
			{
				memberListCondition.setRequestPage(Integer.parseInt(requestPage));
			} 
			memberListCondition.setCorporationID(Long.parseLong(corporationID));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "参数错误") ; 
			return modelAndView ;
		}
		
		//获取社团名称
		try
		{
			//查询成员列表
			List<User> memberList = this.memberManagerServiceImpl.list(memberListCondition) ;
	
			//查询社团
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ; 
			
			//页面回显数据
			modelAndView.addObject("memberList", memberList) ; 
			modelAndView.addObject("requestPage", memberListCondition.getRequestPage()) ; 
			modelAndView.addObject("count", memberListCondition.getCount()) ;
			modelAndView.addObject("corporationID", memberListCondition.getCorporationID()) ;
			modelAndView.addObject("corporation", corporation) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "系统异常") ; 
			return modelAndView ;
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: memberPositionList
	 * @Description: 成员职位列表
	 * @param corporationID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/memberPositionList")
	public ModelAndView memberPositionList(@RequestParam("corporationID") String corporationID)
	{
		ModelAndView modelAndView = new ModelAndView("front/memberManager/memberPositionList") ; 

		try
		{
			//获取成员职位列表
			List<MemberPosition> memberPositionList = this.memberManagerServiceImpl.memberPositionList(Long.parseLong(corporationID)) ; 
			modelAndView.addObject("memberPositionList", memberPositionList) ;
			
			//获取社团
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ;
			modelAndView.addObject("corporation", corporation) ;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "系统异常") ; 
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: deleteMemberPosition
	 * @Description: 删除职位
	 * @param memberPositionID
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/deleteMemberPosition")
	public String deleteMemberPosition(@RequestParam("memberPositionID") String memberPositionID , 
											@RequestParam("corporationID") String corporationID , 
											HttpServletRequest request)
	{
		try
		{
			if(this.memberManagerServiceImpl.deleteMemberPosition(Long.parseLong(memberPositionID)) ==false)
			{
				request.setAttribute("errorMessage", "删除失败");
				return "forward:../user/forwardErrorPage" ; 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ; 
		}
		
		
		return "redirect:memberPositionList?corporationID=" + corporationID ; 
	}
	
	/**
	 * 
	 * @Title: addMemberPosition
	 * @Description: 增加成员职位
	 * @param corporationID
	 * @param memberPositionName
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/addMemberPosition")
	public String addMemberPosition(@RequestParam("corporationID") String corporationID,
												@RequestParam("memberPositionName") String memberPositionName , 
												HttpServletRequest request)
	{
		try
		{
			MemberPosition memberPosition = new MemberPosition() ; 
			memberPosition.setName(memberPositionName);
			memberPosition.setCorporationID(Long.parseLong(corporationID));
			if(this.memberManagerServiceImpl.addMemberPosition(memberPosition) == false)
			{
				//添加失败
				request.setAttribute("errorMessage", "添加失败");
				return "common/error" ;
				
			}
		}
		catch(Exception e)
		{
			//系统异常
			request.setAttribute("errorMessage", "系统异常");
			return "common/error" ;
		}
		
		return "redirect:memberPositionList?corporationID="+corporationID ; 
	}
	
	/**
	 * 
	 * @Title: setMemberPositionUI
	 * @Description: 设置成员职位界面
	 * @param corporationID
	 * @param memberID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/setMemberPositionUI")
	public ModelAndView setMemberPositionUI(@RequestParam("corporationID") String corporationID , 
												@RequestParam("memberID") String memberID) 
	{
		ModelAndView modelAndView = new ModelAndView("front/memberManager/setMemberPositionUI") ; 
		
		try
		{
			//获取社团信息
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ; 
			modelAndView.addObject("corporation", corporation) ;
			
			//根据社团ID获取职位列表
			List<MemberPosition> memberPositionList = this.memberManagerServiceImpl.memberPositionList(Long.parseLong(corporationID)) ; 
			modelAndView.addObject("memberPositionList", memberPositionList) ; 
			
			//获取之前设置的职位
			Map<String, Long> hashmap = new HashMap<>() ;
			hashmap.put("userID", Long.parseLong(memberID)) ; 
			hashmap.put("corporationID", Long.parseLong(corporationID)) ;
			User_MemberPosition user_memberPosition = this.memberManagerServiceImpl.selectByUserIDAndCorporationID(hashmap) ;
			modelAndView.addObject("user_memberPosition", user_memberPosition) ;
	
			//根据成员ID获取成员
			User user = new User() ; 
			user.setId(Long.parseLong(memberID));
			user = this.userServiceImpl.selectUserByPrimaryKey(user) ; 
			modelAndView.addObject("member", user) ;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "参数错误") ; 
		}
		
		return modelAndView ; 
	}
	
	/**
	 * 
	 * @Title: setMemberPosition
	 * @Description: 为成员设置职位
	 * @param memberPosition
	 * @param memberPositionID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/setMemberPosition")
	public String setMemberPosition(@RequestParam("memberID") String memberID , 
											@RequestParam("memberPositionID") String memberPositionID , 
											@RequestParam("corporationID") String corporationID , 
											@RequestParam("user_memberPositionID") String user_memberPositionID , 
											HttpServletRequest request)
	{	
		
		try
		{
			if(memberPositionID.equals("0"))
			{
				//未设置职位,如果之前设置过，删除原职位
				if(!user_memberPositionID.equals("") && user_memberPositionID != null)
				{
					this.memberManagerServiceImpl.deleteUser_MemberPositionByPrimaryKey(Long.parseLong(user_memberPositionID)) ; 
				}
				return "redirect:../memberManager/list?corporationID="+corporationID ; 
			}
			
			//设置成员职位
			User_MemberPosition user_memberPosition = new User_MemberPosition() ; 
			user_memberPosition.setUserID(Long.parseLong(memberID));
			user_memberPosition.setMemberPositionID(Long.parseLong(memberPositionID));
			if(this.memberManagerServiceImpl.setMemberPosition(user_memberPosition , Long.parseLong(corporationID)) == false)
			{
				//设置失败
				request.setAttribute("errorMessage", "操作失败");
				return "common/error" ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "common/error" ;
		}
		
		return "redirect:../memberManager/list?corporationID="+corporationID ; 
	}
	
	@RequestMapping(path = "/kick")
	public String kick(@RequestParam("memberID") String memberID , 
						@RequestParam("corporationID") String corporationID , 
						@RequestParam("requestPage") String requestPage , 
						HttpServletRequest request)
	{
		try
		{
			//将成员移出社团
			User_Corporation user_corporation = new User_Corporation() ; 
			user_corporation.setUserID(Long.parseLong(memberID));
			user_corporation.setCorporationID(Long.parseLong(corporationID));
			this.memberManagerServiceImpl.kick(user_corporation) ; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "common/error" ;
		}
		
		return "redirect:list?requestPage="+requestPage+"&corporationID="+corporationID ;
	}
	
}
