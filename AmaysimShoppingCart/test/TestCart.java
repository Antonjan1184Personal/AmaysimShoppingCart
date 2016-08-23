import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.amaysim.shoppingcart.domain.Cart;
import com.amaysim.shoppingcart.domain.Item;
import com.amaysim.shoppingcart.domain.ItemOrder;
import com.amaysim.shoppingcart.domain.Promo;


public class TestCart {

	private Cart addItemOrder(ItemOrder itemOrder){
				
		Cart cart = new Cart();		
		cart.addItemOrder(itemOrder);		
		return cart;
	}
	
	private Cart addItemOrder(ItemOrder itemOrder, String promoCode){
		
		Cart cart = new Cart();		
		cart.addItemOrder(itemOrder,promoCode);		
		return cart;
	}
	
	/*
	
	@Test
	public void test_addItemOrderSimple() {
		
		Item item = new Item();
		item.setCode("ult_small");
		item.setPrice(new BigDecimal(24.90));
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setItem(item);
		itemOrder.setQty(1);
		
		Cart cart = addItemOrder(itemOrder);
		
		assertEquals("ult_small",cart.getItemOrders().get(0).getItem().getCode());
		assertEquals(new BigDecimal("24.90"),cart.getTotal());
		
		item.setCode("ult_medium");
		item.setPrice(new BigDecimal(29.91));
		assertEquals(new BigDecimal("24.90"),cart.getTotal());

		item.setCode("ult_large");
		item.setPrice(new BigDecimal(44.90));
		assertEquals(new BigDecimal("24.90"),cart.getTotal());


		item.setCode("1gb");
		item.setPrice(new BigDecimal(9.90));
		assertEquals(new BigDecimal("24.90"),cart.getTotal());
	}
	
	
	@Test
	public void test_addItemOrderComplex_Scenario1() {
		Cart cart = new Cart();
		
		Item item = new Item();
		item.setCode("ult_small");
		item.setPrice(new BigDecimal(24.90));
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setItem(item);
		itemOrder.setQty(3);		
								
		cart.addItemOrder(itemOrder);
		
		//assertEquals(Double.parseDouble("49.80"),cart.getTotal(),0);
		
		Item item2 = new Item();
		item2.setCode("ult_large");
		item2.setPrice(new BigDecimal(44.9));
		ItemOrder itemOrder2 = new ItemOrder();
		itemOrder2.setItem(item2);
		itemOrder2.setQty(1);		
		cart.addItemOrder(itemOrder2);
		
		
		assertEquals(new BigDecimal("94.70"),cart.getTotal());
		
	
	}
	
	@Test
	public void test_addItemOrderComplex_Scenario2() {
		Cart cart = new Cart();
		
		Item item = new Item();
		item.setCode("ult_small");
		item.setPrice(new BigDecimal(24.90));
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setItem(item);
		itemOrder.setQty(2);		
								
		cart.addItemOrder(itemOrder);
		
		
		Item item2 = new Item();
		item2.setCode("ult_large");
		item2.setPrice(new BigDecimal(44.9));
		ItemOrder itemOrder2 = new ItemOrder();
		itemOrder2.setItem(item2);
		itemOrder2.setQty(4);		
		cart.addItemOrder(itemOrder2);
		
		
		assertEquals(new BigDecimal("209.40"),cart.getTotal());
			
	}	
*/
	@Test
	public void test_addItemOrderComplex_Scenario3() {
		Cart cart = new Cart();
		
		Item item = new Item();
		item.setCode("ult_small");
		item.setPrice(new BigDecimal(24.90));
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setItem(item);
		itemOrder.setQty(1);		
								
		cart.addItemOrder(itemOrder);
		
		
		Item item2 = new Item();
		item2.setCode("ult_medium");
		item2.setPrice(new BigDecimal(29.9));
		ItemOrder itemOrder2 = new ItemOrder();
		itemOrder2.setItem(item2);
		itemOrder2.setQty(2);		
		cart.addItemOrder(itemOrder2);
		
		
		assertEquals(new BigDecimal("84.70"),cart.getTotal());
		assertEquals("1gb",cart.getItemOrders().get(2).getItem().getCode());		
			
	}	
	
	/*
	@Test
	public void test_addItemOrderComplex_Scenario4() {
		Cart cart = new Cart();
		
		Item item = new Item();
		item.setCode("ult_small");
		item.setPrice(new BigDecimal(24.90));
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setItem(item);
		itemOrder.setQty(1);		
		Promo promo = new Promo();
		promo.setPromoCode("|<3AMAYSIM");
		promo.setItemCode("*");
		promo.setDiscount(new Float(".10"));
		itemOrder.setPromo(promo);						
		cart.addItemOrder(itemOrder);
		
		
		Item item2 = new Item();
		item2.setCode("1gb");
		item2.setPrice(new BigDecimal(9.9));
		ItemOrder itemOrder2 = new ItemOrder();
		itemOrder2.setItem(item2);
		itemOrder2.setQty(1);
		itemOrder2.setPromo(promo);
		cart.addItemOrder(itemOrder2);
		
		
		assertEquals(new BigDecimal("31.32"),cart.getTotal());
			
	}
	*/
}
