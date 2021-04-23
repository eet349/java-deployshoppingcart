package school.lambda.shoppingcartdeployed.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.lambda.shoppingcartdeployed.ShoppingcartdeployedApplicationTests;
import school.lambda.shoppingcartdeployed.models.*;
import school.lambda.shoppingcartdeployed.repository.CartItemRepository;
import school.lambda.shoppingcartdeployed.repository.ProductRepository;
import school.lambda.shoppingcartdeployed.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingcartdeployedApplicationTests.class,
        properties = {"command.line.runner.enable=false"})
public class CartItemServiceImplUnitTestNoDB {

    private List<User> userList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();

    @Autowired
    CartItemService cartItemService;

    //  Everything that is Autowired in the original file gets @MockBean'd in this file
    @MockBean
    private UserRepository userrepo;

    @MockBean
    private ProductRepository prodrepo;

    @MockBean
    private CartItemRepository cartitemrepo;

    @Before
    public void setUp() throws Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
//        r1 = roleService.save(r1);
//        r2 = roleService.save(r2);
        r1.setRoleid(1);
        r2.setRoleid(2);

        User u1 = new User("barnbarntest",
                "LambdaLlama",
                "barnbarn@host.local",
                "added via seed data");
        u1.getRoles()
                .add(new UserRoles(u1,
                        r1));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r2));
        u1.setUserid(1);
        //        u1 = userService.save(u1);
        User u2 = new User("cinnamontest",
                "LambdaLlama",
                "cinnamon@host.local",
                "added via seed mock data");
        u2.getRoles()
                .add(new UserRoles(u2,
                        r2));
        u2.setUserid(2);
//        u2 = userService.save(u2);

        User u3 = new User("stumpstest",
                "LambdaLlama",
                "stumps@host.local",
                "added via seed mock data");
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));
        u3.setUserid(3);
//        u3 = userService.save(u3);

        // Adding Products

        Product p1 = new Product();
        p1.setProductid(1);
        p1.setName("PEN");
        p1.setDescription("MAKES WORDS");
        p1.setPrice(2.50);
        p1.setComments("added via seed mock data");

        Product p2 = new Product();
        p2.setProductid(2);
        p2.setName("PENCIL");
        p2.setDescription("DOES MATH");
        p2.setPrice(1.50);
        p2.setComments("added via seed mock data");

        Product p3 = new Product();
        p3.setProductid(3);
        p3.setName("COFFEE");
        p3.setDescription("EVERYONE NEEDS COFFEE");
        p3.setPrice(4.00);
        p3.setComments("added via seed mock data");

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
//        p1 = productService.save(p1);
//        p2 = productService.save(p2);
//        p3 = productService.save(p3);

        //  Creating Carts
        CartItem cart1 = new CartItem();
        cart1.setUser(u1);
        cart1.setProduct(p1);
        cart1.setComments("added via seed mock data");
        cart1.setQuantity(4);
        u1.getCarts().add(cart1);

        CartItem cart2 = new CartItem();
        cart1.setUser(u1);
        cart1.setProduct(p2);
        cart1.setComments("added via seed mock data");
        cart1.setQuantity(3);
        u1.getCarts().add(cart2);

        CartItem cart3 = new CartItem();
        cart1.setUser(u1);
        cart1.setProduct(p3);
        cart1.setComments("added via seed mock data");
        cart1.setQuantity(2);
        u1.getCarts().add(cart3);


        CartItem cart4 = new CartItem();
        cart1.setUser(u2);
        cart1.setProduct(p3);
        cart1.setComments("added via seed mock data");
        cart1.setQuantity(1);
        u1.getCarts().add(cart4);


        CartItem cart5 = new CartItem();
        cart1.setUser(u3);
        cart1.setProduct(p3);
        cart1.setComments("added via seed mock data");
        cart1.setQuantity(17);
        u1.getCarts().add(cart5);


        userList.add(u1);
        userList.add(u2);
        userList.add(u3);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCart() {
        // Set up data
        CartItemId cartItemId = new CartItemId(1, 1);
        CartItem cart3 = new CartItem();
        cart3.setUser(userList.get(0));
        cart3.setProduct(productList.get(0));
        cart3.setComments("test");
        cart3.setQuantity(2);

        Mockito.when(userrepo.findById(1L)).thenReturn(Optional.of(userList.get(0)));
        Mockito.when(prodrepo.findById(1L)).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(cartitemrepo.findById(any(CartItemId.class))).thenReturn(Optional.of(cart3));
        Mockito.when(cartitemrepo.save(any(CartItem.class))).thenReturn(cart3);

        //  Assert - If we add one to cart we should get 1 more from getQuantity() -- 2 -> 3
        assertEquals(3,
                cartItemService.addToCart(1L,
                        1L,
                        "hello")
                        .getQuantity());
    }

    @Test
    public void removeFromCart() {

        // Set up data
        CartItemId cartItemId = new CartItemId(1, 1);
        CartItem cart3 = new CartItem();
        cart3.setUser(userList.get(0));
        cart3.setProduct(productList.get(0));
        cart3.setComments("test");
        cart3.setQuantity(3);

        Mockito.when(userrepo.findById(1L)).thenReturn(Optional.of(userList.get(0)));
        Mockito.when(prodrepo.findById(1L)).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(cartitemrepo.findById(any(CartItemId.class))).thenReturn(Optional.of(cart3));
        Mockito.when(cartitemrepo.save(any(CartItem.class))).thenReturn(cart3);

        //  Assert - If we we remove one we should get 1 fewer from getQuantity() -- 3 -> 2
        assertEquals(2, cartItemService.removeFromCart(1L,
                1l,
                "Bye")
                .getQuantity());
    }
}