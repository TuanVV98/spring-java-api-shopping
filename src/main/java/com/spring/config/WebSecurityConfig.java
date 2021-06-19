package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.filters.JwtAuthencationTokenFilter;
import com.spring.filters.JwtAuthenticationEntryPointFilter;

/*
 * @EnableWebSecuritycho phép Spring tìm và tự động áp dụng lớp vào Bảo mật Web toàn cầu
 * 
 * EnableGlobalMethodSecuritycung cấp bảo mật AOP trên các phương thức.
 * Nó cho phép @PreAuthorize, @PostAuthorizevà nó cũng hỗ trợ JSR-250 . 
 * 
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	private JwtAuthenticationEntryPointFilter unauthorizedHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	/**
	 * Method to configure the type of authentication in the API
	 * 
	 * @since 15/6/2021
	 * 
	 * @param authenticationManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	/**
	 * Method to create an BCrypt password encoder
	 * 
	 * @author Mariana Azevedo
	 * @since 15/6/2021
	 * 
	 * @return PasswordEncoder object
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/**
	 * @see WebSecurityConfigurerAdapter#authenticationManagerBean()
	 * 
	 * @since 15/6/2021
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	/**
	 * Method to create an Authentication Token Filter Bean
	 * 
	 * @since 15/6/2021
	 * 
	 * @return JwtAuthenticationTokenFilter object
	 */
	@Bean
	public JwtAuthencationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthencationTokenFilter();
	}
	
	
	/**
	 * @see WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 * 
	 * @since 15/6/2021
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		System.out.println("disable ***");
        	http.csrf().disable()
        	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        	.authorizeRequests().antMatchers(
        			"/rest-api/auth",
        			"/rest-api/user/**"
        			).permitAll()
        	.antMatchers("/rest-api/category/**",
        			"/rest-api/product/**",
        			"/rest-api/order/**",
        			"/rest-api/order-details/**",
        			"/rest-api/test/**"
        			
        			).permitAll()
        	.anyRequest().authenticated();
        	
    		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    		http.headers().cacheControl();
        
	}
}
