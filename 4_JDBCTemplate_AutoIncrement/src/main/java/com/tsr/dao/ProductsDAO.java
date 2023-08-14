package com.tsr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tsr.bo.ProductBO;

@Repository("dao")
public class ProductsDAO {
	
	private final String SQL_INSERT_PRODUCT = "insert into PRODUCTS(id, name, price) values(?,?,?)";	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductsDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveProduct(final ProductBO bo) {
		KeyHolder kh = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement pstmt = null;

				pstmt = con.prepareStatement(SQL_INSERT_PRODUCT, new String[] { "id" });
				pstmt.setInt(1, bo.getpId());
				pstmt.setString(2, bo.getpName());
				pstmt.setDouble(3, bo.getpPrice());
				return pstmt;
			}
		}, kh);
		int id = kh.getKey().intValue();
		return id;
	}
}
