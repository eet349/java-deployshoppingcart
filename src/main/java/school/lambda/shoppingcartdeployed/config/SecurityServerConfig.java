package school.lambda.shoppingcartdeployed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/*
* This is the Authentication server
* Second file to create when adding in authentication
* This is exposing methods (Beans) so that they can be autowired into AuthorizationServerConfig
* */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityServerConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean   //  This annotation is the only reason we are Overriding this method. This makes is so we can autowire it into AuthorizedServerConfig
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService securityUserService;
    /*
    * Next Step is to set up this securityUserService in the services package SecurityUserServiceImpl file. So create that file next
    * */

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        //  using securityUserService to set up our userDetailsService/ our spring users
        auth.userDetailsService(securityUserService)
                .passwordEncoder(passwordEncoder());
    }

}
