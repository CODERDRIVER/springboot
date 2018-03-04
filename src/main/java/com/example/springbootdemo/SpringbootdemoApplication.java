package com.example.springbootdemo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.springbootdemo.filter.JwtFilter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加fastJSON依赖
 * 第一步，继承WebMvcConfigurerAdapter
 * 第二步，重写configureMessageConverters方法
 */
@SpringBootApplication
@EnableTransactionManagement		//
public class SpringbootdemoApplication extends WebMvcConfigurerAdapter{

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		/**
		 * 1.需要先定义一个convert转换消息的对象
		 * 2.添加fastjson的配置信息，比如，是否要格式化返回的json的数据
		 * 3.在convert中添加配置信息
		 * 4.将convert添加到converters中
		 */
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastJsonHttpMessageConverter);
	}

	//注入JWT过滤器
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());

		//添加需要拦截的url
		List<String> urls = new ArrayList<>();
		urls.add("/manager/comment/*");
		filterRegistrationBean.addUrlPatterns(urls.toArray(new String[urls.size()]));
		return  filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
