package school.lambda.shoppingcartdeployed.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);  //  allows us to do testing
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //  The meat of all the servers
        //  Which Roles have access to which endpoints
        http.authorizeRequests()
            .antMatchers("/", "/h2-console/**", "/swagger-resources/**", "/swagger-resource/**", "/sagger-ui.html", "/v2/api-docs", "/webjars/**", "/createnewuser")
            .permitAll()
            .antMatchers("/roles/**", "/products/**")
            .hasAnyRole("ADMIN")
            .antMatchers("/carts/**", "/logout")
            .authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();
    }
}
/*
* All done here
* Now we are going to copy in the SimpleCorsFilter file into the config package. This is going to handle the CORS policies and is generally boilerplate code
*
* After that you are going to head to the UserController to set up the /getuserinfo endpoint
* */
