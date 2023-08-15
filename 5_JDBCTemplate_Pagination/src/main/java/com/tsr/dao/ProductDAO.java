package com.tsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.tsr.bo.ProductBO;

@Repository
public class ProductDAO {

	private static final String SELECT = "select id,name,price from products order by name";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ProductBO> getProductsList(int pageNo, int pageSize, String sortColumn) {
		return jdbcTemplate.query(SELECT, new CustomResultSetExtractor(pageNo, pageSize));
	}

	class CustomResultSetExtractor implements ResultSetExtractor<List<ProductBO>> {

		private int pageNo;
		private int pazeSize;

		public CustomResultSetExtractor(int pageNo, int pazeSize) {
			super();
			this.pageNo = pageNo;
			this.pazeSize = pazeSize;
		}

		@Override
		public List<ProductBO> extractData(ResultSet rs) throws SQLException, DataAccessException {
			int startIndex = (pageNo - 1) * (pazeSize) + 1;
			int endIndex = pageNo * pazeSize;
			List<ProductBO> productBO = new ArrayList<ProductBO>();
			int index = 1;
			while (rs.next()) {
				if (index >= startIndex && index <= endIndex) {
					ProductBO product = new ProductBO();
					product.setpId(rs.getInt(1));
					product.setpName(rs.getString(2));
					product.setpPrice(rs.getDouble(3));
					productBO.add(product);
				}
				index++;
			}
			return productBO;
		}
	}
}
