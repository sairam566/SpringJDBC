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
@ComponentScan(basePackages = {"com.tsr.dao"})
public class JavaConfig {
	
	@Bean
	public DataSource getDataSource(@Value("${db.driverClassName}") String driverCls,@Value("${db.url}")String url,@Value("${db.usr}")String usrName,@Value("${db.pwd}") String pwd) {
		DriverManagerDataSource source = new DriverManagerDataSource(url, usrName, pwd);
		source.setDriverClassName(driverCls);
		return source;
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
