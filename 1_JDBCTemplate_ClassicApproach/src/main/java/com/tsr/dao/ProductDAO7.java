package com.tsr.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDAO7 {

	private static final String PRODUCTS_PRICE_BY_ID = "{call productsprice(?,?)}";
	private JdbcTemplate jdbcTemplate;

	public ProductDAO7(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getPriceById(int id) {
		int productsCount = jdbcTemplate.execute(new PriceByIdCallableCreator(id), new PriceByIdCallableCallback());
		return productsCount;
	}

	private final class PriceByIdCallableCreator implements CallableStatementCreator {
		
		private int id;
		
		public PriceByIdCallableCreator(int id) {
			this.id = id;
		}

		@Override
		public CallableStatement createCallableStatement(Connection con) throws SQLException {
			CallableStatement callable = con.prepareCall(PRODUCTS_PRICE_BY_ID);
			callable.setInt(1, id);
			callable.registerOutParameter(2, Types.INTEGER);
			return callable;
		}
	}
	
	private final class PriceByIdCallableCallback implements CallableStatementCallback<Integer>{

		@Override
		public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
			cs.execute();
			return cs.getInt(2);
		}
		
	}
}
