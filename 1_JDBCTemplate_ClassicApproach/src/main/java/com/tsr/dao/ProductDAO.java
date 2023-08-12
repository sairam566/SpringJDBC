package com.tsr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.tsr.bo.ProductBO;

public class ProductDAO {

	private static final String SELECT_ALL = "SELECT ID,NAME,PRICE FROM PRODUCTS";
	private JdbcTemplate jdbcTemplate;

	public ProductDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ProductBO> selectAll() {
		List<ProductBO> productBO = jdbcTemplate.execute(new GetAllProductsPrepareStatement(),
				new GetAllProductsCallBack());
		return productBO;
	}

	private final class GetAllProductsPrepareStatement implements PreparedStatementCreator {

		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
			return ps;
		}
	}

	private final class GetAllProductsCallBack implements PreparedStatementCallback<List<ProductBO>> {

		@Override
		public List<ProductBO> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
			ResultSet resultSet = null;
			List<ProductBO> productBO = new ArrayList<ProductBO>();
			resultSet = ps.executeQuery();
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
