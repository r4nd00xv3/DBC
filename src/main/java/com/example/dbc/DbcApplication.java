package com.example.dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
@EnableSwagger2
public class DbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbcApplication.class, args);
	}

}
