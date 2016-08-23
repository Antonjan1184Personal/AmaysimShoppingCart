package com.amaysim.shoppingcart.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.amaysim.shoppingcart.utilities.ConfigUtility;

public class Cart {
	private ArrayList<ItemOrder> itemOrders;
	private BigDecimal total;
	private PricingRules pricingRules;

	public ArrayList<ItemOrder> getItemOrders() {
		return itemOrders;
	}

	public void setItemOrders(ArrayList<ItemOrder> itemOrders) {
		this.itemOrders = itemOrders;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public PricingRules getPricingRules() {
		return pricingRules;
	}

	public void setPricingRules(PricingRules pricingRules) {
		this.pricingRules = pricingRules;
	}

	public Cart newCart(PricingRules pricingRules) {
		Cart cart = new Cart();
		cart.setPricingRules(pricingRules);

		return cart;
	}

	public void addItemOrder(ItemOrder itemOrder) {

		if (this.itemOrders == null) {
			this.itemOrders = new ArrayList<ItemOrder>();
		}
		this.itemOrders.add(itemOrder);

		if (this.getTotal() == null){
			this.setTotal(new BigDecimal(0));
		}
		this.setTotal(recomputeTotal(itemOrder, this.getTotal()));

	}

	public void addItemOrder(ItemOrder itemOrder, String promoCode) {
		ConfigUtility configUtil = new ConfigUtility();
		String[] valuesArray = configUtil.getValue(promoCode).split(";");

		Promo promo = new Promo();
		promo.setPromoCode(promoCode);
		promo.setItemCode(valuesArray[0]);
		promo.setDiscount(new Float(valuesArray[1]));
		itemOrder.setPromo(promo);

		this.itemOrders.add(itemOrder);

		this.setTotal(recomputeTotal(itemOrder, this.getTotal()));
	}

	private BigDecimal recomputeTotal(ItemOrder itemOrder, BigDecimal curTotal) {
		BigDecimal result = curTotal;
		BigDecimal tempItemPrice = new BigDecimal(0);

		// promo
		Item item = itemOrder.getItem();
		if (itemOrder.getPromo() != null) {
			if (itemOrder.getPromo().getItemCode().equals("*") || itemOrder.getPromo().getItemCode().equals(itemOrder.getItem().getCode())){
				tempItemPrice = item.getPrice().subtract((item.getPrice().multiply(new BigDecimal(itemOrder.getPromo().getDiscount()))));
			}
		} else {
			tempItemPrice = item.getPrice();
		}
		// offer
		Offer offer = new Offer();
		ArrayList<Offer> offerList = offer.getOfferList();

		boolean isComputed = false;
		for (Offer tmpOffer : offerList) {
			if (tmpOffer.getItemCode().equals(itemOrder.getItem().getCode())) {
				String tmpConditionOp = tmpOffer.getConditionOp();
				if (isMatchedCondition(tmpOffer.getConditionOp(), new Integer(
						tmpOffer.getConditionVal()), itemOrder.getQty())) {
					result = result.add(computeOfferPrice(itemOrder, tmpOffer,tempItemPrice));
					isComputed = true;
				} else {
					result = result.add(new BigDecimal(itemOrder.getQty()).multiply(tempItemPrice));
					isComputed = true;
				}
			}
		}
		
		if (!isComputed){
			result = result.add(new BigDecimal(itemOrder.getQty()).multiply(tempItemPrice));
		}

		return result.setScale(2, BigDecimal.ROUND_UP);
	}

	private boolean isMatchedCondition(String conditionOp, int conditionVal,
			int qty) {
		boolean result = false;

		if (conditionOp.equals(">")) {
			if (qty > conditionVal)
				result = true;
		} else if (conditionOp.equals("<")) {
			if (qty < conditionVal)
				result = true;
		} else if (conditionOp.equals("=")) {
			if (qty == conditionVal)
				result = true;
		} else if (conditionOp.equals(">=")) {
			if (qty >= conditionVal)
				result = true;
		} else if (conditionOp.equals("<=")) {
			if (qty <= conditionVal)
				result = true;
		}

		return result;
	}

	private BigDecimal computeOfferPrice(ItemOrder itemOrder, Offer offer,
			BigDecimal curCompItemPrice) {
		BigDecimal result = new BigDecimal(0);

		String offerMode = offer.getDescription().substring(0, 3);
		String offerVal = offer.getDescription().substring(3,
				offer.getDescription().length());

		if (offerMode.equals("pay")) {
			int toBeCharged = Math.round(itemOrder.getQty()
					/ Integer.parseInt(offer.getConditionVal())) * 2;
			result = curCompItemPrice.multiply(new BigDecimal(toBeCharged));

		} else if (offerMode.equals("prc")) {
			result = new BigDecimal(offerVal).multiply(new BigDecimal(itemOrder.getQty()));
		} else if (offerMode.equals("fre")) {
			ItemOrder freeItemOrder = new ItemOrder();
			Item freeItem = new Item();
			freeItem.setCode(offerVal);
			freeItemOrder.setItem(freeItem);
			freeItemOrder.setQty(1);

			this.itemOrders.add(freeItemOrder);
			result = new BigDecimal(itemOrder.getQty()).multiply(itemOrder.getItem().getPrice());
		}
		return result;
	}
}
