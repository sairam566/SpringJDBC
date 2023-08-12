package com.tsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import com.tsr.bo.ProductBO;

public class ProductDAO2 {

	private static final String SELECT_ALL = "SELECT ID,NAME,PRICE FROM PRODUCTS";
	private JdbcTemplate jdbcTemplate;

	public ProductDAO2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ProductBO> selectAll() {
		List<ProductBO> productBO = jdbcTemplate.execute(new productStatementCallback());
		return productBO;
	}

	private final class productStatementCallback implements StatementCallback<List<ProductBO>> {

		@Override
		public List<ProductBO> doInStatement(Statement stmt) throws SQLException, DataAccessException {
			ResultSet resultSet = null;
			List<ProductBO> productBO = new ArrayList<ProductBO>();
			resultSet = stmt.executeQuery(SELECT_ALL);
			while (resultSet.next()) {
				ProductBO product = new ProductBO();
				product.setpId(resultSet.getInt(1));
				product.setpName(resultSet.getString(2));
				product.setpPrice(resultSet.getDouble(3));
				productBO.add(product);
			}
			return productBO;
		}

	}
}
