package com.pi.cart;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CartItem {
        private int itemId;
        private int quantity;
		private Item item;
        
		public CartItem(){

        }

        public CartItem(int itemId, int quantity) {
                super();
                this.itemId = itemId;
                this.quantity = quantity;
        }
        
     
        public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

        public int getItemId() {
                return itemId;
        }

        public void setItemId(int itemId) {
                this.itemId = itemId;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }


}