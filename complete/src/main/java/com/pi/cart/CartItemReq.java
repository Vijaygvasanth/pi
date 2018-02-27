package com.pi.cart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartItemReq {
	private String rfid;

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
}
