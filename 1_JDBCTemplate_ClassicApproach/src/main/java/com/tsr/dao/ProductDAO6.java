package com.tsr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.tsr.bo.ProductBO;

public class ProductDAO6 {

	private static final String SELECT_ALL = "SELECT ID,NAME,PRICE FROM PRODUCTS";
	private JdbcTemplate jdbcTemplate;

	public ProductDAO6(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ProductBO> selectAllProducts() {
		List<ProductBO> productsBO = jdbcTemplate.execute(SELECT_ALL, new GetAllProductsCallBack());
		return productsBO;
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
