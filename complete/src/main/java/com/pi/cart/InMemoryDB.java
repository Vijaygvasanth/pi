package com.pi.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pi.cart.CartItem;
import com.pi.cart.Item;


public class InMemoryDB {

    private static List<Item> items;
    private static Map<Integer, Item> itemMap;
    
    //private static List<Cart> carts;
    private static Map<Integer, Cart> cartMap = new HashMap<>();
    
    private static List<User> users;
    private static Map<Integer, User> userMap;
    private static InMemoryDB instance = null;



    private InMemoryDB(){

            //Items Metadata
            items = new ArrayList<>();
            items.add(new Item(1,"Boost-1kg",45000L));
            items.add(new Item(2,"Horlicks-1kg",55000L));
            items.add(new Item(3,"Bru-1kg",145000L));
            items.add(new Item(4,"Dove Shampoo - 100ml",6000L));
            items.add(new Item(5,"Dove soap - 100g",5500L));

            itemMap = new HashMap<>();
            for(Item item : items)
            {
                    itemMap.put(item.getId(), item);
            }
            
            users = new ArrayList<>();
            users.add(new User(1,"Sachin", 1000000L));
            users.add(new User(2,"Sasi", 2000000L));
            userMap = new HashMap<>();
            
            for(User user : users)
            {
                    userMap.put(user.getId(), user);
            }          


    }
    
    public static InMemoryDB getInstance(){
    	if(instance == null){
    		instance = new InMemoryDB();
    	}
    	
    	return instance;
    }

    public void inserCart(Cart cart){
    	cartMap.put(cart.id, cart);
    }
    
    public Cart getCart(int cartId){
    	return cartMap.get(cartId);
    }
    
    public Cart getUserCart(int userId){
    	for(Cart cart : cartMap.values())
    	{
    		if(cart.userId == userId)
    			return cart;
    	}
        return null;
    }
    
    public List<Item> getItems(){
            return items;
    }
    
    public List<User> getUsers(){
        return users;
}
    
    public void detectUserBalace(int userId, Long amt){
    	User user = userMap.get(userId);
     	user.setBalance(user.getBalance()-amt);
    }


}
