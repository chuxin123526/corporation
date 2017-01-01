package com.corporation.web.controller.front;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.corporation.po.Corporation;
import com.corporation.po.CorporationType;
import com.corporation.po.RecruitInfo;
import com.corporation.service.CorporationService;
import com.corporation.web.vo.RecruitInfoListCondition;

/**
 * 
 * @ClassName: CorporationManagerController
 * @Description: 处理社团请求
 * @author: JJChen
 * @date: 2016年11月28日 上午8:50:55
 */
@Controller
@RequestMapping(path = "/corporation")
public class CorporationController
{
	@Resource
	private CorporationService corporationServiceImpl ; 
	
	/**
	 * 
	 * @Title: list
	 * @Description: 社团列表
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/list")
	public String list()
	{
		return "redirect:../search/search" ;
	}
	
	/**
	 * 
	 * @Title: get 
	 * @Description: 获取指定社团
	 * @param
	 * @return ModelAndView    
	 * @throws
	 */
	@RequestMapping(path = "/get")
	public ModelAndView get(@RequestParam("corporationID") String corporationID)
	{
		ModelAndView modelAndView = new ModelAndView("front/corporation/get") ;
		try
		{
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.
					selectByPrimaryKey(Long.parseLong(corporationID)) ; 
			if(corporation == null)
			{
				//未找到该社团
				modelAndView.addObject("errorMessage" , "未找到社团") ;
				modelAndView.setViewName("common/error");
				return modelAndView ;
			}
			else
			{
				//社团点击量加1
				this.corporationServiceImpl.click(Long.parseLong(corporationID));
				//add object
				modelAndView.addObject("corporation" , corporation) ;
			}
		}
		catch(Exception e)
		{
			//系统异常
			modelAndView.addObject("errorMessage" , "参数错误") ;
			modelAndView.setViewName("common/error");
			e.printStackTrace();
		}
		
		return modelAndView ;
	}

	/**
	 * 
	 * @Title: recruitInfo
	 * @Description: 招募令信息
	 * @param recruitInfoID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/recruitInfo")
	public ModelAndView recruitInfo(@RequestParam("recruitInfoID") String recruitInfoID)
	{
		ModelAndView modelAndView = new ModelAndView("front/corporation/recruitInfo") ; 
		try
		{
			com.corporation.web.vo.RecruitInfo recruitInfo = this.corporationServiceImpl.recruitInfo(Long.parseLong(recruitInfoID)) ; 
		
			modelAndView.addObject("recruitInfo" , recruitInfo) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage" , "参数错误") ;
			modelAndView.setViewName("common/error");
		}
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: publishRecruitUI
	 * @Description: 发布招募令界面
	 * @param corporationID
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/publishRecruitUI")
	public ModelAndView publishRecruitUI(@RequestParam("corporationID") String corporationID)
	{	
		ModelAndView modelAndView = new ModelAndView("front/corporation/publishRecruitUI") ; 
		try
		{
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ; 
			modelAndView.addObject("corporation", corporation) ; 
			modelAndView.addObject("corporationID", corporationID) ;
		}
		catch(Exception e)
		{
			modelAndView.addObject("errorMessage" , "参数错误") ;
			modelAndView.setViewName("common/error");
			e.printStackTrace();
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: publishRecruit
	 * @Description: 发布招募令
	 * @param corporationID 社团ID
	 * @param title 标题
	 * @param content 内容
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param amount 招募人数
	 * @param cost 费用
	 * @return
	 * @return: String 重定向
	 */
	@RequestMapping(path = "/publishRecruit")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public String publishRecruit(@RequestParam("corporationID") String corporationID , 
											@RequestParam("title") String title , 
											@RequestParam("content") String content , 
											@RequestParam("beginTime") String beginTime , 
											@RequestParam("endTime") String endTime ,
											@RequestParam("amount") String amount , 
											@RequestParam("cost") String cost , 
											HttpServletRequest request)
	{
		RecruitInfo recruitInfo = new RecruitInfo() ;
		try
		{
			//封装参数到实体
			recruitInfo.setAmount(Integer.parseInt(amount));
			recruitInfo.setContent(content);
			recruitInfo.setCorporationID(Long.parseLong(corporationID));
			recruitInfo.setCost(Integer.parseInt(cost));
			recruitInfo.setTitle(title);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd") ; 
			Date beginDate = simpleDateFormat.parse(beginTime) ;
			Date endDate =  simpleDateFormat.parse(endTime) ;
			recruitInfo.setBeginTime(beginDate);
			recruitInfo.setEndTime(endDate);
		}
		catch(Exception e)
		{
			//参数错误
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ;
		}
		
		//保存
		try
		{
			if(this.corporationServiceImpl.publishRecruit(recruitInfo) == false)
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
		
		return "redirect:../corporation/get?corporationID="+corporationID ;
	}
	
	/**
	 * 
	 * @Title: updateCorporationInfoUI
	 * @Description: 修改社团信息页面
	 * @param corporationID
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/updateCorporationInfoUI")
	public ModelAndView updateCorporationInfoUI(@RequestParam("corporationID") String corporationID , HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("front/corporation/updateCorporationInfoUI") ; 
		
		try
		{
			//根据社团ID获取社团
			com.corporation.web.vo.Corporation corporation = this.corporationServiceImpl.selectByPrimaryKey(Long.parseLong(corporationID)) ;
			modelAndView.addObject("corporation" , corporation) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.addObject("errorMessage" , "参数错误") ;
			modelAndView.setViewName("common/error");
		}
		
		return modelAndView ;
	}
	
	/**
	 * 
	 * @Title: updateCorporationInfo
	 * @Description: 修改社团信息
	 * @param corporationID
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param introduction
	 * @param multipartFile
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "updateCorporationInfo")
	public String updateCorporationInfo(@RequestParam("corporationID") String corporationID ,
											@RequestParam("name") String name , 
											@RequestParam("address") String address ,
											@RequestParam("phoneNumber") String phoneNumber , 
											@RequestParam("inroduction") String introduction , 
											@RequestParam("image") MultipartFile multipartFile , 
											HttpServletRequest request)
	{
		try
		{
			//封装corporation
			Corporation corporation = new Corporation() ; 
			corporation.setId(Long.parseLong(corporationID));
			corporation.setName(name);
			corporation.setAddress(address);
			corporation.setPhoneNumber(phoneNumber);
			corporation.setIntroduction(introduction);
			
			//图片处理
			if(multipartFile != null)
			{
				String fileName = multipartFile.getOriginalFilename() ;
				
				byte[] bytes = multipartFile.getBytes() ;
			
				if(bytes.length != 0)
				{
					//判读上传文件类型
					//二进制转16进制
					StringBuilder stringBuilder = new StringBuilder() ; 
					for(int i = 0 ; i < bytes.length ; i++)
					{
						int v = bytes[i] & 0xFF ; 
						String hv = Integer.toHexString(v) ; 
						if(hv.length() < 2)
						{
							stringBuilder.append(0) ; 
						}
						stringBuilder.append(hv) ; 
					}
					String s = stringBuilder.toString().toUpperCase() ; 
					//FFD8FF:文件为jpg格式，89504E47：文件为png格式
					if(!s.startsWith("FFD8FF") && !s.startsWith("89504E47"))
					{
						//文件类型既不是jpg格式也不是png格式
						request.setAttribute("errorMessage", "上传文件类型错误");
						return "forward:../user/forwardErrorPage" ; 
					}
					
					String uuid = UUID.randomUUID().toString() ;
					String path = "C:/userUploadImages/"+uuid+fileName ;
				
					File file = new File(path) ; 
					if(!file.exists())
					{
						file.createNewFile() ;
					}
					OutputStream outputStream = new FileOutputStream(file) ;
					outputStream.write(bytes);
					outputStream.flush();
					outputStream.close();
					corporation.setImageUrl(path);
				}
			}
			
			//修改社团信息
			this.corporationServiceImpl.udpateCorporationByPrimaryKeySelective(corporation) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ; 
		}
		
		return "redirect:../corporation/get?corporationID=" + corporationID ;
	}
	
	@RequestMapping(path = "/logoutCorporation")
	public String logoutCorporation(@RequestParam("corporationID") String corporationID , 
										HttpServletRequest request)
	{
		//注销社团
		try
		{
			this.corporationServiceImpl.logoutCorporation(Long.parseLong(corporationID));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMessage", "参数错误");
			return "forward:../user/forwardErrorPage" ; 
		}
		return "redirect:../home/index" ;
	}
	
	/**
	 * 
	 * @Title: getImage
	 * @Description: 获取图片
	 * @param response
	 * @param imageUrls
	 * @return: void
	 */
	@RequestMapping(path = "/getImage")
	public void getImage(HttpServletResponse response , @RequestParam("imageUrl") String imageUrl)
	{
		try
		{
			OutputStream outputStream = response.getOutputStream() ; 
			File file = new File(imageUrl) ; 
			if(!file.exists())
			{
				//社团图片不存在，使用默认图片
				file = new File("C:/userUploadImages/corporationDefaultImage.jpg") ;
			}
			InputStream inputStream = new FileInputStream(file) ; 
			byte[] buffer = new byte[1024] ;
			int length = 0  ;
			while((length = inputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush() ;
			outputStream.close();
			inputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @Title: recruitInfoList
	 * @Description: 获取招募令列表
	 * @param requestPage
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(path = "/recruitInfoList")
	public ModelAndView recruitInfoList(@RequestParam(value = "requestPage" , required = false) String requestPage)
	{
		ModelAndView modelAndView = new ModelAndView("front/corporation/recruitInfoList") ;  
		
		//设置搜索条件
		RecruitInfoListCondition recruitInfoListCondition = new RecruitInfoListCondition() ;
		
		if(requestPage == null || requestPage.equals(""))
		{
			recruitInfoListCondition.setRequestPage(1);
		}
		else
		{
			try
			{
				recruitInfoListCondition.setRequestPage(Integer.parseInt(requestPage));
			}
			catch(Exception e)
			{
				e.printStackTrace();
				modelAndView.setViewName("common/error");
				modelAndView.addObject("errorMessage" , "参数错误") ; 
				return modelAndView ;
			}
		}
		try
		{
			//获取招募令列表
			List<com.corporation.web.vo.RecruitInfo> recruitInfoList = this.corporationServiceImpl.recruitInfoList(recruitInfoListCondition) ; 
			
			//截取字符串
			for(com.corporation.web.vo.RecruitInfo recruitInfo : recruitInfoList)
			{
				String content = recruitInfo.getContent() ; 
				if(content.length() > 200)
				{
					content = content.substring(0, 200) ; 
					recruitInfo.setContent(content + "...");
				}
			}
			
			
			modelAndView.addObject("recruitInfoList" , recruitInfoList) ;
			//页面回显数据
			modelAndView.addObject("requestPage" , recruitInfoListCondition.getRequestPage()) ;
			modelAndView.addObject("count", recruitInfoListCondition.getCount()) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			modelAndView.setViewName("common/error");
			modelAndView.addObject("errorMessage" , "系统异常") ;
		}
		
		return modelAndView ;
	}
	
	
	
}
