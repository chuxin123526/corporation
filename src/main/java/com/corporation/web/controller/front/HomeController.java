package com.corporation.web.controller.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Corporation;
import com.corporation.po.RecruitInfo;
import com.corporation.po.User;
import com.corporation.service.CorporationService;
import com.corporation.web.vo.RecruitInfoListCondition;

/**
 * 
 * @ClassName: HomeController
 * @Description: 处理首页请求
 * @author JJChen
 * @date 2016年11月14日 上午11:15:46
 *
 */
@Controller
@RequestMapping(path = "/home")
public class HomeController
{
	@Resource
	private CorporationService corporationServiceImpl ;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 首页信息展示
	 * @param @return ModelAndView 
	 * @throws
	 */
	@RequestMapping(path = "/index")
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView("front/home/index");
		
		//准备首页数据
		try
		{
			//查找排名前4的社团
			List<Corporation> corporationList = this.corporationServiceImpl.top4Corporation() ;
			modelAndView.addObject("corporationList" , corporationList) ;
			
			//对内容进行截取,截取200字符
			for(Corporation corporation : corporationList)
			{
				String introduction = corporation.getIntroduction() ;
				if(introduction.length() > 200) 
				{
					introduction = introduction.substring(0, 200) ;
					corporation.setIntroduction(introduction + "...");
				}
			}
			
			//按时间倒序查找前十二的招募令
			RecruitInfoListCondition recruitListCondition = new RecruitInfoListCondition() ;
			recruitListCondition.setRequestPage(1);
			List<com.corporation.web.vo.RecruitInfo> recruitInfoList = this.corporationServiceImpl.recruitInfoList(recruitListCondition) ; 
			modelAndView.addObject("recruitInfoList", recruitInfoList) ;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage", "系统异常") ; 
		}
		
		return modelAndView;
	}

}
