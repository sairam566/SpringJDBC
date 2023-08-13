package com.tsr.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tsr.bo.ProductBO;

@Repository
public class ProductsDAO {
	
	private final String ALL_PRODUCTS = "SELECT ID,NAME,PRICE FROM PRODUCTS";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductsDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<ProductBO> getAllProducts(){
		return jdbcTemplate.execute((Statement stmt)->{
			ResultSet resultSet = null;
			List<ProductBO> productsBO = new ArrayList<ProductBO>();
			resultSet = stmt.executeQuery(ALL_PRODUCTS);
			while(resultSet.next()) {
				ProductBO product = new ProductBO();
				product.setpId(resultSet.getInt(1));
				product.setpName(resultSet.getString(2));
				product.setpPrice(resultSet.getDouble(3));
				productsBO.add(product);
			}
					
			return productsBO;
		});
	}
	
}
