package com.pi.cart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
        private int id;
        private String name;
        private Long price;

		public Item(int itemId, String itemName, Long price) {
                super();
                this.id = itemId;
                this.name = itemName;
                this.price = price;
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

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

}