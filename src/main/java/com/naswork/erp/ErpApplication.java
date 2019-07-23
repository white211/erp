package com.naswork.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.naswork.erp.mapper.*")
public class ErpApplication {

	private final static Logger logger = LoggerFactory.getLogger(ErpApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ErpApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
		logger.info("start success-------port:8082");
	}

}





