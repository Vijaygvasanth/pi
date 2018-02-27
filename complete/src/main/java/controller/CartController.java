package controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.cart.Cart;
import com.pi.cart.CartItem;
import com.pi.cart.CartItemReq;
import com.pi.cart.CartRequest;
import com.pi.cart.InMemoryDB;
import com.pi.cart.Item;

@RestController
@RequestMapping("/carts")
public class CartController {

    private InMemoryDB db = InMemoryDB.getInstance();
    
    @RequestMapping(method = RequestMethod.POST)
    public Cart createCart(@RequestBody CartRequest cartReq){
    	int userId = cartReq.getUserId();
    	Cart cart = db.getUserCart(userId);
    	if(cart == null)
    	{
    	//entry
    	cart = new Cart(cartReq.getUserId());
    	db.inserCart(cart);
    	}
    	else
    	{
    		//exit
    		Long totalAmount = cart.getTotalAmount();
    		db.detectUserBalace(cart.getUserId(), totalAmount);
    		db.removeCart(cart.getId());
    	}
		return cart;		
	}
    
    @RequestMapping(method = RequestMethod.PUT)
    public Cart shopCart(@RequestBody CartRequest cartReq){
    	Cart cart = db.getUserCart(cartReq.getUserId());
    	Long totalAmount = cart.getTotalAmount();
    	System.out.println("totalAmount"+totalAmount);
    	db.detectUserBalace(cart.getUserId(), totalAmount);
		return cart;	
	}
    

    @RequestMapping(path = "/{cartId}", method = RequestMethod.GET)
    public List<CartItem> getAllCartItems(@PathVariable("cartId") int cartId){
		Cart cart = db.getCart(cartId);
		return cart.getAllCartItems();		
	}
    
    @RequestMapping(path = "/{cartId}", method = RequestMethod.POST)
    public CartItem addItem(@PathVariable("cartId") int cartId, @RequestBody CartItemReq request){
		Cart cart = db.getCart(cartId);
		return cart.updateCartItem(request.getRfid());		
	}
    
    @RequestMapping(path = "/{cartId}", method = RequestMethod.DELETE)
    public List<CartItem> deleteItem(@PathVariable("cartId") int cartId, @RequestBody CartItem item){
    	Cart cart = db.getCart(cartId);
    	cart.removeItem(item.getItemId());
    	return cart.getAllCartItems();	
    }
    
    
}
