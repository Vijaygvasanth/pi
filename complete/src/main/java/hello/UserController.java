package hello;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.cart.Cart;
import com.pi.cart.CartItem;
import com.pi.cart.InMemoryDB;
import com.pi.cart.User;

@RestController
@RequestMapping("/user")
public class UserController {

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }
    
    @RequestMapping(method = RequestMethod.GET)
    List<User> getAllUsers(){
    	InMemoryDB db = InMemoryDB.getInstance();
    	return db.getUsers();
    }
    
//    @RequestMapping(method = RequestMethod.POST)
//    public List<CartItem> addItem(@RequestBody CartItem item){
//		System.out.println("adding item:"+item.getItemId());
//		cart.addItem(item.getItemId(),item.getQuantity());
//		System.out.println(cart.getAllCartItems().size());
//		return cart.getAllCartItems();		
//	}
    
    
}
