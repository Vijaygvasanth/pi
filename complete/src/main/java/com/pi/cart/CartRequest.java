package com.pi.cart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartRequest {
	
	int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userID) {
		this.userId = userID;
	}

	public CartRequest(int userID) {
		super();
		this.userId = userID;
	}

	public CartRequest() {
		super();
	}

}
