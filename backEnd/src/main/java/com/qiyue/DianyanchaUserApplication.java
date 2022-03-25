package com.qiyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
//@MapperScan("com.qiyue.dao.mapper")
//@ComponentScan(basePackages = {"com.qiyue.utilis"})
public class DianyanchaUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DianyanchaUserApplication.class, args);
	}

}
