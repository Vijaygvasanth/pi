package controller;

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
import com.pi.cart.CartItemReq;
import com.pi.cart.InMemoryDB;
import com.pi.cart.User;

@RestController
@RequestMapping("/users")
public class UserController {

	private static InMemoryDB db = InMemoryDB.getInstance();
 
    @RequestMapping(method = RequestMethod.GET)
    List<User> getAllUsers(){
    	return db.getUsers();
    }
    
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public User addItem(@PathVariable("userId") int userId){
    	return db.getUser(userId);
	}
}
