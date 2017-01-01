package com.corporation.web.controller.util;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;

/**
 * 
 * @ClassName: SecurityCode
 * @Description: 验证码工具
 * @author: JJChen
 * @date: 2016年11月26日 下午2:52:16
 */
@Controller
@RequestMapping(path = "/util")
public class SecurityCode
{
	@Resource 
    private Producer captchaProducer ;
	
	/**
	 * 
	 * @Title: code
	 * @Description: 生成验证码
	 * @param request
	 * @param response
	 * @return: void
	 */
	@RequestMapping(path = "/securityCode")
	public void code(HttpServletRequest request , HttpServletResponse response) 
	{
		HttpSession session = request.getSession();  
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        
        String capText = captchaProducer.createText();  
        
        session.setAttribute(session.getId(), capText);  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = null ; 
        try
        {
        	out = response.getOutputStream();  
        	ImageIO.write(bi, "jpg", out);  
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	
        }
        finally
        {
        	try
        	{
        		out.flush();  
        		out.close();  
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	 
        }
	}
	
	/**
	 * 
	 * @Title: checkSecurityCode
	 * @Description: 验证码校验
	 * @param securityCode
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping(path = "/checkSecurityCode")
	@ResponseBody
	public String checkSecurityCode(@RequestParam("securityCode") String securityCode , HttpServletRequest request)
	{
		JSONObject result = new JSONObject() ; 
		//获取原session验证码
		String sessionSecurityCode = (String)request.getSession().getAttribute(request.getSession().getId()) ;
		//比较
		if(sessionSecurityCode.equals(securityCode))
		{
			result.put("responseCode", "1") ;
		}
		else
		{
			result.put("responseCode", "0") ;
		}
		
		return result.toString() ;
	}
}
