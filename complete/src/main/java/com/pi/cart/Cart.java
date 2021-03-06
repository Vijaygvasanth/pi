package com.pi.cart;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi.cart.CartItem;
import com.pi.cart.Item;
import com.pi.cart.InMemoryDB;

public class Cart {
	
	int id;
	int userId;
	@JsonIgnore
	private Map<Integer, CartItem> cartItems;
	@JsonIgnore
	private List<Item> items;
	@JsonIgnore
	private List<String> rfids;
	@JsonIgnore
	private Map<Integer, Item> itemMap;
	@JsonIgnore
	private static AtomicInteger counter = new AtomicInteger();
	@JsonIgnore
	private static InMemoryDB db = InMemoryDB.getInstance();
	
	public Cart(){
		
	}
	
	public Cart(int userId){
		
		this.userId = userId;
		
		id=counter.incrementAndGet();
		System.out.println("Cart Id"+id);
		cartItems = new HashMap<>();
		items = db.getItems();
		
		itemMap = new HashMap<>();
		for(Item item : items)
		{
			itemMap.put(item.getId(), item);
		}
		
		rfids = new ArrayList<>();
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}



	
	
	public void addItem(int itemId){
		
		if(itemMap.containsKey(itemId)){
			if(cartItems.containsKey(itemId)){
				incrementItemQuanity(itemId);
			}
			else{
				CartItem cartItem = new CartItem(itemId, 1);
				cartItem.setItem(itemMap.get(itemId));
				
				cartItems.put(itemId, cartItem);
			}
		}	
	}
	
	public void removeItem(int itemId){
		cartItems.remove(itemId);
	}
	
	
	public void incrementItemQuanity(int itemId){
		if(cartItems.containsKey(itemId)){
			CartItem cartItem = cartItems.get(itemId);
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}
	}
	
	public void decrementItemQuanity(int itemId){
		if(cartItems.containsKey(itemId)){
			CartItem cartItem = cartItems.get(itemId);
			cartItem.setQuantity(cartItem.getQuantity()-1);
			if(cartItem.getQuantity() < 1){
				cartItems.remove(itemId);
			}
		
		}
	}
	
	@JsonIgnore
	public List<CartItem> getAllCartItems(){
		return new ArrayList<>(cartItems.values());
	}
	
	@JsonIgnore
	public Long getTotalAmount(){
		Long amount = 0L;
		for(CartItem cartItem: cartItems.values())
		{
			amount += (cartItem.getItem().getPrice() * cartItem.getQuantity());
		}
		return amount;
	}


	public CartItem updateCartItem(String rfid) {
		if(rfids.contains(rfid))
		{
			Item item = db.getItemForRfid(rfid);
			decrementItemQuanity(item.getId());
			rfids.remove(rfid);
		}
		else
		{
			Item item = db.getItemForRfid(rfid);
			if(item != null)
			{
				addItem(item.getId());
				rfids.add(rfid);
				return cartItems.get(item.getId());
			}
		}
		return null;
	}
	

}
