package school.lambda.shoppingcartdeployed.services;

import school.lambda.shoppingcartdeployed.exceptions.ResourceNotFoundException;
import school.lambda.shoppingcartdeployed.models.User;
import school.lambda.shoppingcartdeployed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/*
* This is the 3rd file in the series of steps for setting up authentication/authorization
* Here we are linking our application Users with the spring version of Users
* */
@Transactional
@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    //  Overriding this method is the whole reason we created this file
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User/*our User*/ user = userRepository.findByUsername(username.toLowerCase());

        if (user == null) {
            throw new ResourceNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
        //  At this point user.getAuthority() doesn't exist so we need to go into our User model and create it so we can fully convert our User into a Spring User
        //  Since we will be working in our User model already, it is a good time to set up the password encoder within setPassword()
    }
}
