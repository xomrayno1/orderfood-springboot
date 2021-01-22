package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {
//https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/validation.html
	// fix loi can not  Error: Could not find or load main class spring boot
	/* 
	 * <properties>
	    <start-class>com.bt.collab.alu.api.webapp.Application</start-class>
		</properties>
	 */
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
