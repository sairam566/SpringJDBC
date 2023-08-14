package com.tsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tsr.bo.ProductBO;

@Repository("dao")
public class ProductsDAO {
	
	private final String SQL_NO_OF_PRODUCTS = "select count(1) from Products";
	private final String SQL_MAX_PRODUCT_AMOUNT = "select max(price) from products";
	private final String SQL_GET_PRODUCT_NM_BY_PRODUCT_ID = "select name from products where id = ?";
	private final String SQL_GET_PRODUCT_BY_ID="select id, name, price from products where id = ?";
	private final String SQL_PRODUCTS_GREATER_THEN_GN_PRICE = "select id, name, price from products where price > ?";
	private final String SQL_GET_PRODUCTS_BY_PRICE = "select name, price from products where price >= ?";
	private final String SQL_INSERT_PRODUCT = "insert into PRODUCTS(id, name, price) values(?,?,?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductsDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/*-----------------------------------------------
	 * Query's Returning Single Column
	 * ----------------------------------------------
	 */
	
	public int getNoOfProducts() {
		return jdbcTemplate.queryForObject(SQL_NO_OF_PRODUCTS, Integer.class);
	}
	
	public float maxProductAmount() {
		return jdbcTemplate.queryForObject(SQL_MAX_PRODUCT_AMOUNT, Float.class);
	}
	
	public String getProductNameById(int productId) {
		return jdbcTemplate.queryForObject(SQL_GET_PRODUCT_NM_BY_PRODUCT_ID, String.class,productId);
	}
	
	/*-----------------------------------------------
	 * Query's Returning Multiple Column
	 * ----------------------------------------------
	 */
	
	public ProductBO getProductById(int productId) {
		return jdbcTemplate.queryForObject(SQL_GET_PRODUCT_BY_ID, new CustomRowMapper(),productId);
	}
	
	class CustomRowMapper implements RowMapper<ProductBO>{
		@Override
		public ProductBO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductBO bo = new ProductBO();
			bo.setpId(rs.getInt(1));
			bo.setpName(rs.getString(2));
			bo.setpPrice(rs.getDouble(3));
			return bo;
		}
	}
	
	public List<ProductBO> getProductsGreaterThanGivenPrice(Double price){
		return jdbcTemplate.query(SQL_PRODUCTS_GREATER_THEN_GN_PRICE, (rs,rowNo)->{
			ProductBO bo = new ProductBO();
			bo.setpId(rs.getInt(1));
			bo.setpName(rs.getString(2));
			bo.setpPrice(rs.getDouble(3));
			return bo;
		},price);
	}
	
	/*-----------------------------------------------
	 * Query's Returning arbitrary number of Column
	 * ----------------------------------------------
	 */
	
	public List<Map<String, Object>> getProductsByPrice(Double price) {
		return jdbcTemplate.queryForList(SQL_GET_PRODUCTS_BY_PRICE, price);
	}
	/*-----------------------------------------------
	 * Insert Query
	 * ----------------------------------------------
	 */
	
	public int saveProduct(ProductBO bo) {
		return jdbcTemplate.update(SQL_INSERT_PRODUCT, bo.getpId(), bo.getpName(), bo.getpPrice());
	}
	
}
