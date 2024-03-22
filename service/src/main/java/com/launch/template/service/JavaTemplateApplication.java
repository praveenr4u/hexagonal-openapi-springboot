package com.launch.template.service;

import com.launch.template.infrastructure.data.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.launch.template"})
@Import({DataSourceConfig.class})
public class JavaTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTemplateApplication.class, args);
	}

}
