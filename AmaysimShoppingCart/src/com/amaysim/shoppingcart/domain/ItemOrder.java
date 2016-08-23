package com.amaysim.shoppingcart.domain;

import java.util.ArrayList;

public class ItemOrder {
	private Item item;
	private int qty;
	private Promo promo;
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public Promo getPromo() {
		return promo;
	}
	public void setPromo(Promo promo) {
		this.promo = promo;
	}
		
	
}
