package com.tsr.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDAO4 {

	private static final String CREATE = "CREATE TABLE REGISTRATION (id INTEGER not NULL, "
			+ " first VARCHAR(255), last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ))";

	private JdbcTemplate jdbcTemplate;

	public ProductDAO4(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createRegistrationTable() {
		jdbcTemplate.execute(CREATE);
	}

}
