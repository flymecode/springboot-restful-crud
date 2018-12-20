/**
 * @author: maxu1
 * @date: 2018/12/15 14:09
 * 可以扩展springmvc的功能
 */

package com.example.demo.config;

import com.example.demo.component.MyLocaleResolver;
import com.example.demo.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index.html").setViewName("index");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/asserts/**","/index.html","/","/user/login","/webjars/**","/time/**");
	}

//	@Bean
//	public WebMvcConfigurer webMvcConfigurer() {
//		WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
//			@Override
//			public void addViewControllers(ViewControllerRegistry registry) {
//				registry.addViewController("/").setViewName("login");
//				registry.addViewController("/index.html").setViewName("login");
//				registry.addViewController("/main.html").setViewName("dashboard");
//			}
//
//			@Override
//			public void addInterceptors(InterceptorRegistry registry) {
//				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//						.excludePathPatterns("/index.html","/","/user/login");
//
//			}
//		};
//		return webMvcConfigurer;
//	}

	@Bean
	public LocaleResolver localeResolver() {
		System.out.println("自定义的解析器");
		return new MyLocaleResolver();
	}


}
