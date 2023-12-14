package com.spring.validation.springvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.spring.validation.springvalidation.helper.SubcriberProperties;

@EnableConfigurationProperties(value = {
	SubcriberProperties.class
})
@SpringBootApplication
public class SpringValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringValidationApplication.class, args);
	}
}
