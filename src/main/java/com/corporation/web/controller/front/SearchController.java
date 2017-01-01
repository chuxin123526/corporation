package com.corporation.web.controller.front;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Corporation;
import com.corporation.po.CorporationType;
import com.corporation.service.CorporationService;
import com.corporation.service.SearchService;
import com.corporation.web.vo.SearchCondition;

/**
 * 
 * @ClassName: SearchController
 * @Description: 处理搜索请求
 * @author: JJChen
 * @date: 2016年11月29日 上午10:20:10
 */
@Controller
@RequestMapping(path = "/search")
public class SearchController
{
	@Resource
	private SearchService searchServiceImpl ; 
	
	@Resource
	private CorporationService corporationServiceImpl ;
	/**
	 * 
	 * @Title: search
	 * @Description: 社团搜索
	 * @param keyword 关键字
	 * @param typeID 类型ID
	 * @return
	 * @return: ModelAndView 
	 */
	@RequestMapping(path = "/search")
	public ModelAndView search(@RequestParam(value = "keyword" , required = false)  String keyword , 
									@RequestParam(value = "typeID" , required = false) String typeID , 
									@RequestParam(value = "requestPage",required = false) String requestPage)
	{
		ModelAndView modelAndView = new ModelAndView("/front/corporation/list") ;
		
		//设置搜索条件
		SearchCondition searchCondition = new SearchCondition() ;
		try
		{
			if(requestPage != null && !requestPage.equals("") && !requestPage.equals("0"))
			{
				searchCondition.setRequestPage(Integer.parseInt(requestPage));
			}
			else
			{
				searchCondition.setRequestPage(1);
			}
			if(keyword != null && !keyword.equals(""))
			{
				searchCondition.setKeyword(keyword);
			}
			if(typeID != null && !typeID.equals("0") && !typeID.equals(""))
			{
				searchCondition.setTypeID(Long.parseLong(typeID));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "参数错误") ; 
			modelAndView.setViewName("common/error");
			return modelAndView;
		}
		
		try
		{
			//搜索社团
			List<Corporation> corporationList = this.searchServiceImpl.search(searchCondition) ;
			modelAndView.addObject("corporationList", corporationList) ;
			//获取社团类型
			List<CorporationType> corporationTypeList = this.corporationServiceImpl.corporationTypeList() ;
			modelAndView.addObject("corporationTypeList", corporationTypeList) ; 
			
			//设置分页条件给页面
			modelAndView.addObject("requestPage" , searchCondition.getRequestPage()) ; 
			modelAndView.addObject("keyword", keyword) ;
			modelAndView.addObject("pageCount" , searchCondition.getPageCount()) ;
			modelAndView.addObject("typeID", typeID) ; 
			modelAndView.addObject("count" , searchCondition.getCount()) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage", "系统异常") ; 
			modelAndView.setViewName("common/error");
			return modelAndView;
		}
		
		return modelAndView ;
	}
}
