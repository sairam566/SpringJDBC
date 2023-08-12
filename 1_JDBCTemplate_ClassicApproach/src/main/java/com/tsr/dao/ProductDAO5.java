package com.tsr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.tsr.bo.ProductBO;

public class ProductDAO5 {

	private static final String SELECT_ALL = "SELECT ID,NAME,PRICE FROM PRODUCTS where ID=?";
	private JdbcTemplate jdbcTemplate;

	public ProductDAO5(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ProductBO selectById(int id) {
		ProductBO productBO = jdbcTemplate.execute(new GetAllProductsPrepareStatement(id),
				new GetAllProductsCallBack());
		return productBO;
	}

	private final class GetAllProductsPrepareStatement implements PreparedStatementCreator {
		private int id;
		
		public GetAllProductsPrepareStatement(int id) {
			this.id = id;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
			ps.setInt(1, id);
			return ps;
		}
	}

	private final class GetAllProductsCallBack implements PreparedStatementCallback<ProductBO> {

		@Override
		public ProductBO doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
			ResultSet resultSet = null;
			ProductBO productBO = new ProductBO();
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				productBO.setpId(resultSet.getInt(1));
				productBO.setpName(resultSet.getString(2));
				productBO.setpPrice(resultSet.getDouble(3));
			}
			return productBO;
		}

	}
}
