package com.tsr.bo;

import java.util.Date;

public class CustomerBo {
	
	int customerNo;
	String customerName;
	Date dob;
	String gender;
	
	public CustomerBo() {
		super();
	}
	
	public CustomerBo(int customerNo, String customerName, Date dob, String gender) {
		super();
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.dob = dob;
		this.gender = gender;
	}

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

	@Override
	public String toString() {
		return "CustomerBo [customerNo=" + customerNo + ", customerName=" + customerName + ", dob=" + dob + ", gender="
				+ gender + "]";
	}
}
