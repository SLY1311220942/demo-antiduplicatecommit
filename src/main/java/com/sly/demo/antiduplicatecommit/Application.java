package com.sly.demo.antiduplicatecommit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sly.plugin.antiduplicatecommit.annotation.EnableAntiDuplicateCommit;

/**
 * 项目启动类
 * @author sly
 * @time 2019年5月16日
 */
@SpringBootApplication
@EnableAntiDuplicateCommit
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
