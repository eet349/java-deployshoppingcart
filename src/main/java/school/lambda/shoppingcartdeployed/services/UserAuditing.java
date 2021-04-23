package school.lambda.shoppingcartdeployed.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Spring Boot needs to know what username to use for the auditing fields CreatedBy and ModifiedBy
 * For now, a default name will be used
 */
@Component
public class UserAuditing
    implements AuditorAware<String>
{
    /**
     * The current user
     *
     * @return Optional(String) of current user
     */
    @Override
    public Optional<String> getCurrentAuditor()
    {

        String uname;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            uname = "SYSTEM";
            System.out.println(uname + " not authenticated");
        } else {
            uname = authentication.getName();
            System.out.println(uname + " authenticated");
        }

        //uname = authentication == null ? "SYSTEM" : authentication.getName();

        return Optional.of(uname);
    }
}
/*
* Ok all done with this. We can now use the name of someone in our auditing fields if they are authenticated
* Now we have to fix an issue in our UserServiceImpl where we are calling setPassword() which will encrypt our password again
* Since we dont want that we have to find the save() and update() methods using setPassword() and change it to setPasswordNoEncrypt()
* */
