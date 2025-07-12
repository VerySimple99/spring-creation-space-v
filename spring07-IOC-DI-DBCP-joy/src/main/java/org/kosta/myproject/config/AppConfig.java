package org.kosta.myproject.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.kosta.myproject")
public class AppConfig {
	//메서드가 리턴하는 객체를 bean 으로 생성, 관리  bean name은 method 명이 된다 
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/kosa?serverTimezone=UTC");
		dataSource.setUsername("joyofbeing");
		dataSource.setPassword("1234");
		return dataSource;
	}
}











