package com.corporation.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil
{
	public boolean sendTextMail(MailSenderInfo mailInfo)
	{
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate())
		{
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try
		{
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
//			String mailContent = mailInfo.getContent();
//			mailMessage.setText(mailContent);
//			// 发送邮件
//			Transport.send(mailMessage);
			
			 // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();  
            // 创建一个包含HTML内容的MimeBodyPart  
            BodyPart html = new MimeBodyPart();  
            // 设置HTML内容  
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");  
            mainPart.addBodyPart(html);  
              
            //设置信件的附件(用本地上的文件作为附件)  
//            html = new MimeBodyPart();  
//            FileDataSource fds = new FileDataSource("D:\\...javamail.doc");  
//            DataHandler dh = new DataHandler(fds);  
//            html.setFileName("javamail.doc");  
//            html.setDataHandler(dh);  
//            mainPart.addBodyPart(html);  
              
            // 将MiniMultipart对象设置为邮件内容  
            mailMessage.setContent(mainPart);  
            mailMessage.saveChanges();  
              
            // 发送邮件  
            Transport.send(mailMessage);  
            return true; 
		} catch (MessagingException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	@Test
	public boolean send(String email , String title , String content) throws Exception
	{
		// 这个类主要是设置邮件  
        MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost("smtp.163.com");  
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("XXXXXXX@163.com"); // 实际发送者  
        mailInfo.setPassword("XXXXXX");// 您的邮箱密码  
        mailInfo.setFromAddress("XXXXXXXXXX"); // 设置发送人邮箱地址  
        mailInfo.setToAddress(email); // 设置接受者邮箱地址  
        mailInfo.setSubject(title); //标题  
        mailInfo.setContent(content);  //内容
        // 这个类主要来发送邮件  
        //SimpleMailSender sms = new SimpleMailSender();  
        boolean isSuccess = this.sendTextMail(mailInfo); // 发送文体格式  
        
        return isSuccess ;
	}
}
