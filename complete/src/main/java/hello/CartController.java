package hello;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.cart.Cart;
import com.pi.cart.CartItem;
import com.pi.cart.CartRequest;
import com.pi.cart.InMemoryDB;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private InMemoryDB db = InMemoryDB.getInstance();
    //private  Cart cart = new Cart(1);

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }
    
//    @RequestMapping(method = RequestMethod.GET)
//    List<CartItem> getAllCartItems(){
//    	return cart.getAllCartItems();
//    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Cart createCart(@RequestBody CartRequest cartReq){
		System.out.println("user id"+cartReq.getUserId());
    	Cart cart = new Cart(cartReq.getUserId());
    	System.out.println("Created cart"+cart.getId());
    	db.inserCart(cart);
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
    
    
    @RequestMapping(path = "/{cartId}", method = RequestMethod.POST)
    public List<CartItem> addItem(@PathVariable("cartId") int cartId, @RequestBody CartItem item){
    	System.out.println("cart id:"+cartId);
		System.out.println("adding item:"+item.getItemId());
		Cart cart = db.getCart(cartId);
		cart.addItem(item.getItemId(),item.getQuantity());
		System.out.println(cart.getAllCartItems().size());
		return cart.getAllCartItems();		
	}
    
    @RequestMapping(path = "/{cartId}", method = RequestMethod.DELETE)
    public List<CartItem> deleteItem(@PathVariable("cartId") int cartId, @RequestBody CartItem item){
    	Cart cart = db.getCart(cartId);
    	cart.removeItem(item.getItemId());
    	return cart.getAllCartItems();	
    }
    
    
}
