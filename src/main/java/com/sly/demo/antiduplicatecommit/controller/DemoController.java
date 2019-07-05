package com.sly.demo.antiduplicatecommit.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sly.demo.antiduplicatecommit.constant.DemoToken;
import com.sly.demo.antiduplicatecommit.model.Business;
import com.sly.plugin.antiduplicatecommit.annotation.AntiDuplicateCommit;
import com.sly.plugin.sensitiveword.filter.SensitivewordFilter;
import com.sly.plugin.validate.annotation.Valid;
import com.sly.plugin.validate.annotation.Validate;
import com.sly.plugin.validate.constraints.NotBlank;
import com.sly.plugin.validate.constraints.Null;

/**
 * 反重复提交测试controller
 * 
 * @author sly
 * @time 2019年5月16日
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private SensitivewordFilter sensitivewordFilter;
	
	/**
	 * 跳转到页面顺便设置token
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年5月16日
	 */
	@RequestMapping("/toDemo")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_ADD_TOKEN, DemoToken.DEMO_UPDATE_TOKEN,
			DemoToken.DEMO_DELETE_TOKEN }, isCheckToken = false)
	public String toDemo(HttpServletRequest request, HttpServletResponse response) {
		return "/pages/demo.html";
	}

	/**
	 * 去新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年5月16日
	 */
	@RequestMapping("/toAdd")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_ADD_TOKEN }, isCheckToken = false)
	public String toAdd(HttpServletRequest request, HttpServletResponse response) {
		return "/pages/add.html";
	}

	/**
	 * 去修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年5月16日
	 */
	@RequestMapping("/toUpdate")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_UPDATE_TOKEN }, isCheckToken = false)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response) {
		return "/pages/update.html";
	}

	/**
	 * 新增demo
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年5月16日
	 */
	@Validate
	@ResponseBody
	@RequestMapping("/demoAddSubmit")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_ADD_TOKEN }, isReturnToken = false)
	public Object demoAddSubmit(HttpServletRequest request, HttpServletResponse response,@Valid("add") Business business) {
		System.out.println(JSON.toJSONString(business));
		Map<String, Object> result = new HashMap<>(16);
		try {
			System.out.println("我是新增业务方法,我执行了!");
			result.put("status", 200);
			result.put("message", "新增成功!");
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.put("status", 400);
			result.put("message", "新增失败!");
		}
		return result;
	}

	/**
	 * 修改demo
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年5月16日
	 */
	@Validate
	@ResponseBody
	@RequestMapping("/demoUpdateSubmit")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_UPDATE_TOKEN }, isReturnToken = false)
	public Object demoUpdateSubmit(HttpServletRequest request, HttpServletResponse response,
			@Valid("update") Business business) {
		Map<String, Object> result = new HashMap<>(16);
		try {
			System.out.println("我是修改业务方法,我执行了!");
			result.put("status", 200);
			result.put("message", "修改成功!");
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.put("status", 400);
			result.put("message", "修改失败!");
		}
		return result;
	}

	/**
	 * 删除demo
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年5月16日
	 */
	@Validate
	@ResponseBody
	@RequestMapping("/demoDeleteSubmit")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_DELETE_TOKEN },isReturnToken=false)
	public Object demoDeleteSubmit(HttpServletRequest request, HttpServletResponse response,@NotBlank(message="Id不能为空!") @Null(message="Id必须为空!") String id) {
		Map<String, Object> result = new HashMap<>(16);
		try {
			System.out.println("我是删除业务方法,我执行了!");
			result.put("status", 200);
			result.put("message", "删除成功!");
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.put("status", 400);
			result.put("message", "删除失败!");
		}
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param word
	 * @return
	 * @author sly
	 * @time 2019年7月5日
	 */
	@ResponseBody
	@RequestMapping("/sensitiveWord")
	public Object demoSensitiveWord(HttpServletRequest request, HttpServletResponse response,String word) {
		Map<String, Object> result = new HashMap<>(16);
		try {
			Set<String> sensitiveWord = sensitivewordFilter.getSensitiveWord(word, SensitivewordFilter.maxMatchType);
			result.put("status", 200);
			result.put("message", "敏感词验证成功!");
			result.put("sensitiveWord", sensitiveWord);
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			result.put("status", 400);
			result.put("message", "敏感词验证失败!");
		}
		return result;
	}
	
	public static void main(String[] args) {
		double baseTotal = 0;
		double total = 0;
		double base = 7560;
		double increase = 1.07;
		double b = 1.10;
		int year = 15;
		for (int i = 0; i < year; i++) {
			
			total = total * b + base;
			System.out.println(total);
			System.out.println(base);
			baseTotal += base;
			System.out.println(baseTotal);
			base = base * increase;
			
		}
		
		System.out.println("total = " + total);
		System.out.println("baseTotal = " + baseTotal);
		System.out.println("base = " + base );
	}
}
