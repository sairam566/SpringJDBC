package com.tsr.bo;

public class ProductBO {
	private int pId;
	private String pName;
	private Double pPrice;

	public ProductBO() {
		super();
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Double getpPrice() {
		return pPrice;
	}

	public void setpPrice(Double pPrice) {
		this.pPrice = pPrice;
	}

	@Override
	public String toString() {
		return "ProductBO [pId=" + pId + ", pName=" + pName + ", pPrice=" + pPrice + "]";
	}
}
