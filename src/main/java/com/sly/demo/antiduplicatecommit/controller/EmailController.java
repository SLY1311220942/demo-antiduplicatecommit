package com.sly.demo.antiduplicatecommit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.EmailAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.plugin.common.constant.ResultStatus;
import com.sly.plugin.common.result.BaseResult;
import com.sly.plugin.email.model.MailInfo;
import com.sly.plugin.email.sender.EmailSender;

/**
 * 邮件发送测试
 * @author sly
 * @time 2019年7月11日
 */
@Controller
@RequestMapping("/email")
public class EmailController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailSender emailSender;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年7月11日
	 */
	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public BaseResult sendSimpleEmail(HttpServletRequest request,HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			MailInfo mailInfo = new MailInfo();
			
			mailInfo.setContent("sendSimpleEmail测试内容");
			mailInfo.setSubject("sendSimpleEmail测试");
			mailInfo.addAddressee("1311220942@qq.com");
			
			emailSender.sendSimpleEmail(mailInfo);
			
			result.setStatus(ResultStatus.SUCCESS);
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.setStatus(ResultStatus.FAILED);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年7月11日
	 */
	@ResponseBody
	@RequestMapping("/sendMultiPartEmail")
	public BaseResult sendMultiPartEmail(HttpServletRequest request,HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setName("测试文件.txt");
			attachment.setPath("D:\\test\\ftpdownload.txt");
			
			MailInfo mailInfo = new MailInfo();
			
			mailInfo.setContent("sendMultiPartEmail测试内容");
			mailInfo.setSubject("sendMultiPartEmail测试");
			mailInfo.addAddressee("1311220942@qq.com");
			mailInfo.addAttachment(attachment);
			
			emailSender.sendMultiPartEmail(mailInfo);
			
			result.setStatus(ResultStatus.SUCCESS);
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.setStatus(ResultStatus.FAILED);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年7月11日
	 */
	@ResponseBody
	@RequestMapping("/sendHtmlEmail")
	public BaseResult sendHtmlEmail(HttpServletRequest request,HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setName("测试文件.txt");
			attachment.setPath("D:\\test\\ftpdownload.txt");
			
			MailInfo mailInfo = new MailInfo();
			
			mailInfo.setContent("<h1>sendHtmlEmail测试内容</h1>");
			mailInfo.setSubject("sendSimpleEmail测试");
			mailInfo.addAddressee("1311220942@qq.com");
			mailInfo.addAttachment(attachment);
			
			emailSender.sendHtmlEmail(mailInfo);
			
			result.setStatus(ResultStatus.SUCCESS);
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.setStatus(ResultStatus.FAILED);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年7月11日
	 */
	@ResponseBody
	@RequestMapping("/sendImageHtmlEmail")
	public BaseResult sendImageHtmlEmail(HttpServletRequest request,HttpServletResponse response) {
		BaseResult result = new BaseResult();
		try {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setName("测试文件.txt");
			attachment.setPath("D:\\test\\ftpdownload.txt");
			
			MailInfo mailInfo = new MailInfo();
			
			
			mailInfo.setContent("<h1>sendImageHtmlEmail测试内容</h1>" + "<img src=\"D:\\test\\image.jpg\">"
					+ " <img src=\"http://commons.apache.org/proper/commons-email/images/commons-logo.png\">");
			mailInfo.setSubject("sendSimpleEmail测试");
			mailInfo.addAddressee("1311220942@qq.com");
			mailInfo.addAttachment(attachment);
			
			emailSender.sendImageHtmlEmail(mailInfo);
			
			result.setStatus(ResultStatus.SUCCESS);
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.setStatus(ResultStatus.FAILED);
		}
		
		return result;
	}
}

