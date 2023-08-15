package com.tsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.tsr.bo.CustomerLeaseBo;
import com.tsr.bo.VehicleBo;

@Repository
public class VehicleSDAO {
	
	private final String SQL_GET_CUSTOMER_LEASED_VEHICLES = "select c.customerNO,c.name,c.dob,c.gender,v.VehicleNo,v.model_name,v.manufacturer,v.color,v.fuel_type,v.lease_amount from customer c inner join vehicle_lease vl on c.CustomerNo = vl.customer_No \r\n" + 
			"inner join vehicle v on vl.vehicle_no = v.vehicleNo";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public VehicleSDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<CustomerLeaseBo> getCustomerWithLeasedVehicles(){
		return jdbcTemplate.query(SQL_GET_CUSTOMER_LEASED_VEHICLES, new CustomerLeaseResultSetExtractor());
	}
	
	class CustomerLeaseResultSetExtractor implements ResultSetExtractor<List<CustomerLeaseBo>> {

		@Override
		public List<CustomerLeaseBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			Map<Integer, CustomerLeaseBo> customerLeasedMap = null;
			List<CustomerLeaseBo> customerLeaseBos = null;
			CustomerLeaseBo cbo = null;
			VehicleBo vbo = null;
			int customerNo = 0;

			customerLeasedMap = new HashMap<Integer, CustomerLeaseBo>();
			while (rs.next()) {
				customerNo = rs.getInt(1);
				if (!customerLeasedMap.containsKey(customerNo)) {
					cbo = new CustomerLeaseBo();
					cbo.setCustomerNo(rs.getInt(1));
					cbo.setCustomerName(rs.getString(2));
					cbo.setDob(rs.getDate(3));
					cbo.setGender(rs.getString(4));

					Set<VehicleBo> leasedVehicles = new HashSet<VehicleBo>();
					cbo.setLeasedVehicles(leasedVehicles);
					customerLeasedMap.put(customerNo, cbo);
				}
				cbo = customerLeasedMap.get(customerNo);
				vbo = new VehicleBo();
				vbo.setVehicleNo(rs.getInt(5));
				vbo.setModelName(rs.getString(6));
				vbo.setManufacturer(rs.getString(7));
				vbo.setColor(rs.getString(8));
				vbo.setFuelType(rs.getString(9));
				vbo.setLeaseAmount(rs.getFloat(10));
				cbo.getLeasedVehicles().add(vbo);
			}

			customerLeaseBos = customerLeasedMap.values().stream().collect(Collectors.toList());
			return customerLeaseBos;
		}
		
	}
}
