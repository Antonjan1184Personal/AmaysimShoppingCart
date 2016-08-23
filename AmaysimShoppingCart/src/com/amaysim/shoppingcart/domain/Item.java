package com.amaysim.shoppingcart.domain;

import java.math.BigDecimal;

import com.amaysim.shoppingcart.utilities.ConfigUtility;

public class Item {
	private String code;
	private String name;
	private BigDecimal price;
	private Promo promo;
	
	public Promo getPromo() {
		return promo;
	}
	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
		
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public double retrievePrice(String code){
		ConfigUtility configUtil = new ConfigUtility();
		String value = configUtil.getValue(code);
		String[] valueArray = value.split(";");
		
		return new Double(valueArray[1]);
	}
}
