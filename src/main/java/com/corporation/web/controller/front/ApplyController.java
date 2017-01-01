package com.corporation.web.controller.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Corporation;
import com.corporation.po.CorporationCreateApply;
import com.corporation.po.CorporationJoinApply;
import com.corporation.po.CorporationType;
import com.corporation.po.User;
import com.corporation.po.User_Corporation;
import com.corporation.service.ApplyService;
import com.corporation.service.CorporationService;
import com.corporation.service.UserService;
import com.corporation.web.vo.CorporationCreateApplyListCondition;
import com.corporation.web.vo.CorporationJoinApplyListConditin;
import com.corporation.web.vo.MyApplyCreateCorporationListCondition;
import com.corporation.web.vo.MyApplyJoinCorporationListCondition;

@Controller
@RequestMapping(path = "/apply")
public class ApplyController
{
	@Resource
	private CorporationService corporationServiceImpl ; 
	
	@Resource
	private ApplyService applyServiceImpl ;
	
	/**
	 * 
	 * @Title: applyJoinCorporation
	 * @Description: 申请加入社团界面
	 * @param corporationID  社团ID
	 * @return
	 * @return: ModelAndView 视图
	 */
	@RequestMapping(path = "/applyJoinCorporationUI")
	public ModelAndView applyJoinCorporationUI(@RequestParam("corporationID") String corporationID , 
													HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("front/apply/applyJoinCorporationUI") ;
		try
		{
			//判断当前用户是否属于该社团
			User_Corporation user_corporation = new User_Corporation() ; 
			User user = (User)request.getSession().getAttribute("user") ;
			user_corporation.setUserID(user.getId());
			user_corporation.setCorporationID(Long.parseLong(corporationID));
			if(this.applyServiceImpl.judgeUserWhetherBelongCorporation(user_corporation))
			{
				//用户属于该社团
				modelAndView.setViewName("common/error");
				modelAndView.addObject("errorMessage" , "您已经是该社团的成员，不能申请加入该社团") ;
				return modelAndView ;
			}
			
			//获取社团信息
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ;
			
			//判断当前用户是否是会长
			if(user.getId() == corporation.getPresidentID())
			{
				//当前用户是该协会会长
				modelAndView.setViewName("common/error");
				modelAndView.addObject("errorMessage" , "您已经是该社团的会长,不能申请加入该社团") ;
				return modelAndView ;
			}
			
			modelAndView.addObject("corporation", corporation) ;
		}
		catch(Exception e)
		{
			modelAndView.addObject("errorMessage", "参数错误") ;
			modelAndView.setViewName("common/error");
			e.printStackTrace();
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: applyJoinCorporation
	 * @Description: 申请加入社团
	 * @param corporationID
	 * @param reason
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/applyJoinCorporation")
	public String applyJoinCorporation(@RequestParam("corporationID") String corporationID , 
											@RequestParam("reason") String reason , 
											HttpServletRequest request)
	{
		try
		{
			//保存申请记录
			CorporationJoinApply corporationJoinApply = new CorporationJoinApply() ; 
			corporationJoinApply.setCorporationID(Long.parseLong(corporationID));
			corporationJoinApply.setReason(reason);
			User user = (User)request.getSession().getAttribute("user") ;
			corporationJoinApply.setUserID(user.getId());
			corporationJoinApply.setStatus("未审核");
			this.applyServiceImpl.applyJoinCorporation(corporationJoinApply) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ;
		}
		
		return  "redirect:myApplyJoinCorporationList" ;
	}
	
	/**
	 * 
	 * @Title: applyCreateCorporationUI
	 * @Description: 申请创建社团界面
	 * @return
	 * @return: ModelAndView 视图
	 */
	@RequestMapping(path = "/applyCreateCorporationUI")
	public ModelAndView applyCreateCorporationUI()
	{
		ModelAndView modelAndView = new ModelAndView("front/apply/applyCreateCorporationUI") ; 
		
		try
		{
			//获取所有社团类型
			List<CorporationType> corporationTypeList = this.corporationServiceImpl.corporationTypeList() ; 
			modelAndView.addObject("corporationTypeList", corporationTypeList) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "系统异常");
			modelAndView.setViewName("common/error");
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: applyCreateCorporatin
	 * @Description: 申请创建社团
	 * @param name 社团名称
	 * @param address 地址
	 * @param phoneNumber 联系号码
	 * @param email 联系邮箱
	 * @param introduction 社团简介
	 * @return
	 * @return: String 
	 */
	@RequestMapping(path = "/applyCreateCorporation")
	public String applyCreateCorporatin(@RequestParam("name") String name , 
											@RequestParam("address") String address , 
											@RequestParam("phoneNumber") String phoneNumber , 
											@RequestParam("email") String email , 
											@RequestParam("introduction") String introduction ,
											@RequestParam("corporationTypeID") String corporationTypeID , 
											HttpServletRequest request)
	{
		try
		{
			//封装参数到实体
			CorporationCreateApply corporationCreateApply = new CorporationCreateApply() ; 
			corporationCreateApply.setName(name);
			corporationCreateApply.setAddress(address);
			corporationCreateApply.setEmail(email);
			corporationCreateApply.setIntroduction(introduction);
			corporationCreateApply.setPhoneNumber(phoneNumber);
			corporationCreateApply.setCorporationTypeID(Long.parseLong(corporationTypeID));
			User user = (User)request.getSession().getAttribute("user") ; 
			long userID = user.getId() ;
			corporationCreateApply.setUserID(userID);
			corporationCreateApply.setStatus("未审核");
			
			//保存申请记录
			if(this.applyServiceImpl.applyCreateCorporation(corporationCreateApply) == false)
			{
				//保存失败
				request.setAttribute("errorMessage", "保存失败");
				return "forward:../user/forwardErrorPage" ; 
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ; 
		}
		
		return "redirect:myApplyCreateCorporationList" ; 
	}
	
	/**
	 * 
	 * @Title: myApplyJoinCorporation
	 * @Description: 我申请加入的社团列表
	 * @param requestPage
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "myApplyJoinCorporationList")
	public ModelAndView myApplyJoinCorporationList(@RequestParam(value = "requestPage" , required = false) String requestPage ,
													HttpServletRequest request )
	{
		ModelAndView modelAndView = new ModelAndView("front/apply/myApplyJoinCorporationList") ; 
		
		try
		{
			//获取我申请加入的社团
			MyApplyJoinCorporationListCondition myApplyJoinCorporationListCondition = new MyApplyJoinCorporationListCondition() ; 
			if( requestPage == null || requestPage.equals("") )
			{
				requestPage = "1" ;
			}
			myApplyJoinCorporationListCondition.setRequestPage(Integer.parseInt(requestPage));
			User user = (User)request.getSession().getAttribute("user") ;
			myApplyJoinCorporationListCondition.setUserID(user.getId());
			List<com.corporation.web.vo.CorporationJoinApply> corporationJoinApplyList =
					this.applyServiceImpl.myApplyJoinCorporationList(myApplyJoinCorporationListCondition) ; 
			
			//界面数据回显
			modelAndView.addObject("corporationJoinApplyList", corporationJoinApplyList) ;
			modelAndView.addObject("requestPage", myApplyJoinCorporationListCondition.getRequestPage()) ; 
			modelAndView.addObject("count" , myApplyJoinCorporationListCondition.getCount()) ;
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
	 * @Title: myApplyCreateCorporation
	 * @Description: 我申请创建的社团列表
	 * @param requestPage
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "myApplyCreateCorporationList")
	public ModelAndView myApplyCreateCorporationList(@RequestParam(value = "requestPage" , required = false) String requestPage ,
													HttpServletRequest request )
	{
		ModelAndView modelAndView = new ModelAndView("front/apply/myApplyCreateCorporationList") ; 
		
		try
		{
			//获取我申请加入的社团
			MyApplyCreateCorporationListCondition myApplyCreateCorporationListCondition = new MyApplyCreateCorporationListCondition() ; 
			if( requestPage == null || requestPage.equals("") )
			{
				requestPage = "1" ;
			}
			myApplyCreateCorporationListCondition.setRequestPage(Integer.parseInt(requestPage));
			User user = (User)request.getSession().getAttribute("user") ;
			myApplyCreateCorporationListCondition.setUserID(user.getId());
			List<com.corporation.web.vo.CorporationCreateApply> corporationCreateApplyList =
					this.applyServiceImpl.myApplyCreateCorporationList(myApplyCreateCorporationListCondition) ; 
			
			//界面数据回显
			modelAndView.addObject("corporationCreateApplyList", corporationCreateApplyList) ;
			modelAndView.addObject("requestPage", myApplyCreateCorporationListCondition.getRequestPage()) ; 
			modelAndView.addObject("count" , myApplyCreateCorporationListCondition.getCount()) ;
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
	 * @Title: corporationJoinApplyList
	 * @Description: 处理申请页面（申请加入社团列表）
	 * @param corporationID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "corporationJoinApplyList")
	public ModelAndView corporationJoinApplyList(@RequestParam("corporationID") String corporationID , 
														@RequestParam(value = "requestPage" , required = false) String requestPage)
	{
		ModelAndView modelAndView = new ModelAndView("front/apply/corporationJoinApplyList") ; 
		
		try
		{
			//设置分页条件
			CorporationJoinApplyListConditin corporationJoinApplyListCondition = new CorporationJoinApplyListConditin() ; 
			corporationJoinApplyListCondition.setCorporationID(Long.parseLong(corporationID));
			if(requestPage == null || requestPage.equals(""))
			{
				corporationJoinApplyListCondition.setRequestPage(1);
			}
			else
			{
				corporationJoinApplyListCondition.setRequestPage(Integer.parseInt(requestPage));
			}
			
			//获取社团加入申请列表
			List<com.corporation.web.vo.CorporationJoinApply> corporationJoinApplyList = this.applyServiceImpl.corporationJoinApplyList(corporationJoinApplyListCondition) ;
			//获取社团
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ; 
			
			//界面数据回显
			modelAndView.addObject("requestPage", corporationJoinApplyListCondition.getRequestPage()) ; 
			modelAndView.addObject("count" , corporationJoinApplyListCondition.getCount()) ;
			modelAndView.addObject("corporationJoinApplyList" , corporationJoinApplyList) ;
			modelAndView.addObject("corporation", corporation) ;
			
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
	 * @Title: reviewJoinCorporationApply
	 * @Description: 审核申请加入社团页面
	 * @param corporationJoinApplyID 申请加入记录ID 
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/reviewJoinCorporationApplyUI")
	public ModelAndView reviewJoinCorporationApplyUI(@RequestParam("corporationJoinApplyID") String corporationJoinApplyID , 
														@RequestParam("corporationID") String corporationID , 
														@RequestParam("requestPage") String requestPage)
	{
		ModelAndView modelAndView = new ModelAndView("/front/apply/reviewJoinCorporationApplyUI") ; 
		
		try
		{
			//获取社团加入申请信息
			com.corporation.web.vo.CorporationJoinApply corporationJoinApply = this.applyServiceImpl.selectCorporationJoinApplyID(Long.parseLong(corporationJoinApplyID)) ; 
			//获取社团信息 
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ; 
			
			//页面数据回显
			modelAndView.addObject("corporationJoinApply", corporationJoinApply) ; 
			modelAndView.addObject("corporation", corporation) ;
			modelAndView.addObject("requestPage", requestPage) ;
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
	 * @Title: reviewJoinCorporationApply
	 * @Description: 审核加入社团申请
	 * @param corporationJoinApplyID
	 * @param handle
	 * @param requestPage
	 * @param corporationID
	 * @param userID
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/reviewJoinCorporationApply")
	public String reviewJoinCorporationApply(@RequestParam("corporationJoinApplyID") String corporationJoinApplyID , 
												@RequestParam("handle") String handle , 
												@RequestParam(value = "requestPage" , required = false) String requestPage , 
												@RequestParam("corporationID") String corporationID , 
												@RequestParam("userID") String userID , 
												HttpServletRequest request)
	{
		try
		{
			CorporationJoinApply corporationJoinApply = new CorporationJoinApply() ; 
			corporationJoinApply.setId(Long.parseLong(corporationJoinApplyID));
			if(handle.equals("通过"))
			{
				corporationJoinApply.setStatus("已通过");
			}
			else
			{
				corporationJoinApply.setStatus("未通过");
			}
			if(this.applyServiceImpl.reviewJoinCorporationApply(corporationJoinApply , Long.parseLong(corporationID) , Long.parseLong(userID)) == false)
			{
				//service处理异常
				request.setAttribute("errorMessage", "参数错误");
				return "forward:../user/forwardErrorPage" ;
			}
				
		}
		catch(Exception e)
		{
			//系统异常
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ;
		}
		
		return "redirect:corporationJoinApplyList?requestPage="+requestPage+"&corporationID="+corporationID ;
	}
	
	/**
	 * 
	 * @Title: corporationCreateApplyList
	 * @Description: 申请创建社团列表
	 * @param requestPage
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/corporationCreateApplyList")
	public ModelAndView corporationCreateApplyList(@RequestParam(value = "requestPage" , required = false) String requestPage) 
	{
		ModelAndView modelAndView = new ModelAndView("background/apply/corporationCreateApplyList") ;
		
		try
		{
			//设置分页条件
			CorporationCreateApplyListCondition condition = new CorporationCreateApplyListCondition() ; 
			if(requestPage == null || requestPage.equals("") || requestPage.equals("0"))
			{
				condition.setRequestPage(1);
			}
			else
			{
				condition.setRequestPage(Integer.parseInt(requestPage));
			}
			
			//查询社团创建申请列表
			List<com.corporation.web.vo.CorporationCreateApply> corporationCreateApplyList = this.applyServiceImpl.corporationCreateApplyList(condition) ; 
			
			//页面回显数据
			modelAndView.addObject("corporationCreateApplyList", corporationCreateApplyList) ;
			modelAndView.addObject("requestPage", condition.getRequestPage()) ; 
			modelAndView.addObject("count", condition.getCount()) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "参数错误") ;
		}
		
		return modelAndView ;
	}
	
	@RequestMapping(path = "/reviewCorporationCreateApplyUI")
	public ModelAndView reviewCorporationCreateApplyUI(@RequestParam("corporationCreateApplyID") String corporationCreateApplyID , 
														@RequestParam(value = "requestPage" , required = false) String requestPage) 
	{
		ModelAndView modelAndView = new ModelAndView("background/apply/reviewCorporationCreateApplyUI") ; 
		
		try
		{
			//根据id获取申请信息
			com.corporation.web.vo.CorporationCreateApply corporationCretaeApply = this.applyServiceImpl.selectCorporationCreateApplyByID(Long.parseLong(corporationCreateApplyID)) ; 
			
			//页面回显数据
			modelAndView.addObject("corporationCreateApply", corporationCretaeApply) ;
			modelAndView.addObject("requestPage", requestPage) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "参数错误") ; 
			modelAndView.setViewName("common/error");
		}
		
		
		return modelAndView ; 
	}
	
	/**
	 * 
	 * @Title: reviewCorporationCreateApply
	 * @Description: 处理创建社团申请
	 * @param corporationCreateApplyID
	 * @param handle
	 * @param requestPage
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/reviewCorporationCreateApply")
	public String reviewCorporationCreateApply(@RequestParam("corporationCreateApplyID") String corporationCreateApplyID , 
													@RequestParam("handle") String handle ,
													@RequestParam(value = "requestPage" ,required = false) String requestPage , 
													HttpServletRequest request)
	{
		try
		{
			//审核
			if(this.applyServiceImpl.reviewCorporationCreateApply(Long.parseLong(corporationCreateApplyID) , handle) == false)
			{
				request.setAttribute("errorMessage", "审核失败");
				return "forward:../user/forwardErrorPage" ;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ;
		}
		
		return "redirect:corporationCreateApplyList?requestPage="+requestPage ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
