package com.tsr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = { "com.tsr.dao" })
public class JavaConfig {

	@Bean
	public DataSource getDataSource(@Value("${db.driverClassName}") String driverClass,@Value("${db.url}") String url,@Value("${db.usr}")String usr,@Value("${db.pwd}")String pwd) {
		DriverManagerDataSource source  = new DriverManagerDataSource(url, usr, pwd);
		source.setDriverClassName(driverClass);
		return source;
	}
	
	@Bean
	public JdbcTemplate getjdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
