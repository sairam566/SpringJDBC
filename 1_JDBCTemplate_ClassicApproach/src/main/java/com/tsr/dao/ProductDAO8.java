package com.tsr.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDAO8 {

	private static final String PRODUCTS_COUNT = "{call productscount(?)}";
	private JdbcTemplate jdbcTemplate;

	public ProductDAO8(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getAllproductsCount() {
		int productsCount = jdbcTemplate.execute(PRODUCTS_COUNT, new ProductsCountCallableCallback());
		return productsCount;
	}

	
	private final class ProductsCountCallableCallback implements CallableStatementCallback<Integer>{

		@Override
		public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			return cs.getInt(1);
		}
		
	}
}
