package school.lambda.shoppingcartdeployed.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import school.lambda.shoppingcartdeployed.ShoppingcartdeployedApplicationTests;

import static org.junit.Assert.*;


@AutoConfigureMockMvc
public class CartItemServiceImplUnitTestWithDB {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCart() {
    }

    @Test
    public void removeFromCart() {
    }
}