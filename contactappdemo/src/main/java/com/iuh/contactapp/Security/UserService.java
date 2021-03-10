package com.iuh.contactapp.Security;

import com.iuh.contactapp.User.entity.User;
import com.iuh.contactapp.User.entity.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserReponsitory userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException{
        User user = userRepository.getBynumberphone(username);
        if (user == null) {
            System.out.println("Not Exist User");
            throw new UsernameNotFoundException("Not Exist User");

        }
     //   user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new CustomUserDetails(user);
    }


    public User registerNewUserAccount(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
