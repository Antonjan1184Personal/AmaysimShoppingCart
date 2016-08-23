package com.amaysim.shoppingcart.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Offer {
	private String itemCode;
	private String conditionOp;
	private String conditionVal;
	private String monthCovered;
	private String description;

	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getConditionOp() {
		return conditionOp;
	}
	public void setConditionOp(String conditionOp) {
		this.conditionOp = conditionOp;
	}
	public String getMonthCovered() {
		return monthCovered;
	}
	public void setMonthCovered(String monthCovered) {
		this.monthCovered = monthCovered;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<Offer> getOfferList(){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("PricingOffer").getFile());
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tempArray = line.split(";");
				
				Offer offer = new Offer();
				offer.setItemCode(tempArray[0]);
				offer.setConditionOp(tempArray[1]);
				offer.setConditionVal(tempArray[2]);
				offer.setMonthCovered(tempArray[3]);
				offer.setDescription(tempArray[4]);
				
				offerList.add(offer);
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return offerList;
	}
	public String getConditionVal() {
		return conditionVal;
	}
	public void setConditionVal(String conditionVal) {
		this.conditionVal = conditionVal;
	}
}
