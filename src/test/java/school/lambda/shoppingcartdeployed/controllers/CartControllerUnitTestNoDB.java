package school.lambda.shoppingcartdeployed.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import school.lambda.shoppingcartdeployed.ShoppingcartdeployedApplicationTests;
import school.lambda.shoppingcartdeployed.models.*;
import school.lambda.shoppingcartdeployed.services.CartItemService;
import school.lambda.shoppingcartdeployed.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

    @RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
            classes = ShoppingcartdeployedApplicationTests.class,
            properties = {"command.line.runner.enabled=false"})
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @AutoConfigureMockMvc
public class CartControllerUnitTestNoDB {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CartItemService cartItemService;

    @MockBean
    private UserService userService;

    private List<User> userList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();


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

//        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listCartItemsByUserId() throws Exception {
        String apiUrl = "/carts/user";
        Mockito.when(userService.findByName(any(String.class)))
                .thenReturn(userList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb)
                .andReturn();
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0));

        //  Assert - check to see if the response is the same as what we were expecting
        assertEquals(er, tr);

    }

    @Test
    public void addToCart() throws Exception{
        String apiUrl = "/carts/add/product/1";

        CartItem cart3 = new CartItem();
        cart3.setUser(userList.get(0));
        cart3.setProduct(productList.get(0));
        cart3.setComments("");
        cart3.setQuantity(2);

        Mockito.when(userService.findByName(any(String.class))).thenReturn(userList.get(0));
        Mockito.when(cartItemService.addToCart(any(Long.class), any(Long.class), any(String.class)))
                .thenReturn(cart3);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();

        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(cart3);

        //  Assert
        assertEquals(er, tr);
    }

    @Test
    public void removeFromCart() throws Exception{
        String apiUrl = "/carts/remove/product/1";

        CartItem cart3 = new CartItem();
        cart3.setUser(userList.get(0));
        cart3.setProduct(productList.get(0));
        cart3.setComments("");
        cart3.setQuantity(2);

        Mockito.when(userService.findByName(any(String.class))).thenReturn(userList.get(0));
        Mockito.when(cartItemService.removeFromCart(any(Long.class), any(Long.class), any(String.class)))
                .thenReturn(cart3);

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();

        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(cart3);

        //  Assert
        assertEquals(er, tr);
    }
}