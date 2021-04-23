package school.lambda.shoppingcartdeployed.controllers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import school.lambda.shoppingcartdeployed.ShoppingcartdeployedApplicationTests;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
classes = ShoppingcartdeployedApplicationTests.class)
@AutoConfigureMockMvc
@WithMockUser(value = "barnbarn")   //  Needs to actually be in the data
public class CartControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        //  Set up security
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listCartItemsByUserId() {
        given().when()
                .get("/carts/user")
                .then()
                .body(containsString("barnbarn"));
    }

    @Test
    public void addToCart() {
        given().when()
                .get("/carts/add/products/6")
                .then()
                .body(containsString("5"));
    }

    @Test
    public void removeFromCart() {
        given().when()
                .get("/carts/remove/products/7")
                .then()
                .body(containsString("2"));
    }
}