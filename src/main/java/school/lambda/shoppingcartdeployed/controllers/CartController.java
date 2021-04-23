package school.lambda.shoppingcartdeployed.controllers;

import school.lambda.shoppingcartdeployed.models.CartItem;
import school.lambda.shoppingcartdeployed.models.User;
import school.lambda.shoppingcartdeployed.services.CartItemService;
import school.lambda.shoppingcartdeployed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/user/{userid}",
//        produces = {"application/json"})
//    public ResponseEntity<?> listCartItemsByUserId(
//        @PathVariable
//            long userid)
//    {
//        User u = userService.findUserById(userid);
//        return new ResponseEntity<>(u,
//            HttpStatus.OK);
//    }
    @GetMapping(value = "/user",
        produces = {"application/json"})
    public ResponseEntity<?> listCartItemsByUserId()  {
        User u = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    @PutMapping(value = "/add/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> addToCart(
        @PathVariable
            long productid)
    {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        CartItem addCartTtem = cartItemService.addToCart(user.getUserid(),
            productid,
            "I am not working");
        return new ResponseEntity<>(addCartTtem,
            HttpStatus.OK);
    }
//    @PutMapping(value = "/add/user/{userid}/product/{productid}",
//        produces = {"application/json"})
//    public ResponseEntity<?> addToCart(
//        @PathVariable
//            long userid,
//        @PathVariable
//            long productid)
//    {
//        CartItem addCartTtem = cartItemService.addToCart(userid,
//            productid,
//            "I am not working");
//        return new ResponseEntity<>(addCartTtem,
//            HttpStatus.OK);
//    }

    @DeleteMapping(value = "/remove/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> removeFromCart(
        @PathVariable
            long productid)
    {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        CartItem removeCartItem = cartItemService.removeFromCart(user.getUserid(),
            productid,
            "I am still not working");
        return new ResponseEntity<>(removeCartItem,
            HttpStatus.OK);
    }

//    @DeleteMapping(value = "/remove/user/{userid}/product/{productid}",
//        produces = {"application/json"})
//    public ResponseEntity<?> removeFromCart(
//        @PathVariable
//            long userid,
//        @PathVariable
//            long productid)
//    {
//        CartItem removeCartItem = cartItemService.removeFromCart(userid,
//            productid,
//            "I am still not working");
//        return new ResponseEntity<>(removeCartItem,
//            HttpStatus.OK);
//    }
}
