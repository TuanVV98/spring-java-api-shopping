package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.beans.factory.annotation.Value;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringJavaAsmApplication {

	@Value("${spring.servlet.multipart.max-file-size}") String maxFileSize;
	@Value("${spring.servlet.multipart.max-request-size}") String maxRequestSize;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJavaAsmApplication.class, args);
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(DataSize.parse(maxRequestSize).toBytes());
		multipartResolver.setMaxUploadSizePerFile(DataSize.parse(maxFileSize).toBytes());
	    return multipartResolver;
	}

}
