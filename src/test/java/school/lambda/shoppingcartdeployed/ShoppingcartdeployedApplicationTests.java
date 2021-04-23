package school.lambda.shoppingcartdeployed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = "file:/Users/Ethan/shoppingcartconf.properties", ignoreResourceNotFound = true)
public class ShoppingcartdeployedApplicationTests {


    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartdeployedApplication.class, args);
    }

}
