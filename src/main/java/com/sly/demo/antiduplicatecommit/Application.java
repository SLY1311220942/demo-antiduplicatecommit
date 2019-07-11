package com.sly.demo.antiduplicatecommit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sly.plugin.antiduplicatecommit.annotation.EnableAntiDuplicateCommit;
import com.sly.plugin.email.annotation.EnableEmailSender;
import com.sly.plugin.sensitiveword.annotation.EnableSensitiveWord;
import com.sly.plugin.validate.annotation.EnableValidate;
import com.sly.plugin.xss.annotation.EnableXssFilter;

/**
 * 项目启动类
 * @author sly
 * @time 2019年5月16日
 */
@SpringBootApplication
@EnableAntiDuplicateCommit
@EnableXssFilter
@EnableValidate
@EnableSensitiveWord
@EnableEmailSender
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
