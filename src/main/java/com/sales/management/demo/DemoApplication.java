package com.sales.management.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackages = {"com.sales.management.entity"})
@EnableJpaRepositories(basePackages = {"com.sales.management.repository"})
@ComponentScan(basePackages = {"com.sales.management.servico", "com.sales.management.controller", "com.sales.management.common"})
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
