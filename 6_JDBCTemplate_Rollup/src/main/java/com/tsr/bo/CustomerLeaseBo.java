package com.tsr.bo;

import java.util.Date;
import java.util.Set;

public class CustomerLeaseBo {
	private int customerNo;
	private String customerName;
	private Date dob;
	private String gender;
	private Set<VehicleBo> leasedVehicles;

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<VehicleBo> getLeasedVehicles() {
		return leasedVehicles;
	}

	public void setLeasedVehicles(Set<VehicleBo> leasedVehicles) {
		this.leasedVehicles = leasedVehicles;
	}

	@Override
	public String toString() {
		return "CustomerLeaseBo [customerNo=" + customerNo + ", customerName=" + customerName + ", dob=" + dob
				+ ", gender=" + gender + ", leasedVehicles=" + leasedVehicles + "]";
	}

}
