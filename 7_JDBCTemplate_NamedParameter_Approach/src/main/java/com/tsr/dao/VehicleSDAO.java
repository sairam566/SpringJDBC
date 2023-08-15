package com.tsr.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tsr.bo.VehicleBo;

@Repository
public class VehicleSDAO {

	private final String SQL_GET_COUNT_VEHICLES_BY_MANF = "select count(1) from vehicle where manufacturer = :manufacturer";
	private final String SQL_UPDATE_VEHCILE_BY_VEHICLE_NO = "update vehicle set color = :color, lease_amount = :lAmount where vehicleno = :vehicleNo";
	private final String SQL_INSERT_VEHICLE = "insert into vehicle(VehicleNo, model_name, manufacturer, color, fuel_type, lease_amount) values(:vehicleNo, :modelName, :manufacturer, :color, :fuelType, :leaseAmount)";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public VehicleSDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public int getVehiclesCount(String manufacturer) {
		Map<String, Object> paramMap = null;
		paramMap = new HashMap<String, Object>();
		paramMap.put("manufacturer", manufacturer);
		return namedParameterJdbcTemplate.queryForObject(SQL_GET_COUNT_VEHICLES_BY_MANF, paramMap, Integer.class);
	}

	public int updateVehicle(int vehicleNo, String color, float leaseAmount) {
		MapSqlParameterSource paramSource = null;
		paramSource = new MapSqlParameterSource();
		paramSource.addValue("vehicleNo", vehicleNo);
		paramSource.addValue("color", color);
		paramSource.addValue("lAmount", leaseAmount);

		return namedParameterJdbcTemplate.update(SQL_UPDATE_VEHCILE_BY_VEHICLE_NO, paramSource);
	}
	
	public int saveVehicle(VehicleBo bo) {
		BeanPropertySqlParameterSource paramSource = null;
		paramSource = new BeanPropertySqlParameterSource(bo);		
		return namedParameterJdbcTemplate.update(SQL_INSERT_VEHICLE, paramSource);
	}
}
