package com.spring.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	
	@Value("${allowed.origin}")
	private String allowedOrigin;
	
	/**
	 * Disable CORS support in spring
	 * 
	 * @since 06/06/2021
	 * {@link} https://stackoverflow.com/questions/44697883/can-you-completely-disable-cors-support-in-spring
	 *		   https://medium.com/@valeryyakovlev/how-to-disable-cors-restrictions-in-spring-boot-app-819158aa3919
	 * {@link} What is CORS  https://topdev.vn/blog/cors-la-gi/
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(allowedOrigin)
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
	
}
