package com.tsr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class ProductsDAO {
	
	private final String SQL_NO_OF_PRODUCTS = "select count(1) from Products";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductsDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		System.out.println("called");
	}
	
	public int getNoOfProducts() {
		return jdbcTemplate.queryForObject(SQL_NO_OF_PRODUCTS, Integer.class);
	}
	
}
