package com.pi.cart;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class User {
	int id;
	String name;
	Long balance;
	@JsonIgnore
	int cartId;
	
	public User(){
		
	}
	
	public User(int id, String name, Long balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	

}
