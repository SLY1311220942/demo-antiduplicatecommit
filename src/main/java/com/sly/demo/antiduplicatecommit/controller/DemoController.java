package com.sly.demo.antiduplicatecommit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sly.demo.antiduplicatecommit.constant.DemoToken;
import com.sly.demo.antiduplicatecommit.model.Business;
import com.sly.plugin.antiduplicatecommit.annotation.AntiDuplicateCommit;
import com.sly.plugin.validate.annotation.Valid;
import com.sly.plugin.validate.annotation.Validate;

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
	public Object demoAddSubmit(HttpServletRequest request, HttpServletResponse response,@Valid(group = "add") Business business) {
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
			@Valid(group = "update") Business business) {
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
	@ResponseBody
	@RequestMapping("/demoDeleteSubmit")
	@AntiDuplicateCommit(keys = { DemoToken.DEMO_DELETE_TOKEN })
	public Object demoDeleteSubmit(HttpServletRequest request, HttpServletResponse response) {
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
}
