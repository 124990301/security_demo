package com.example;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class UserConfig implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if(username.equals("zhangsan")){
//            return new org.springframework.security.core.userdetails.User("zhangsan","123",);
//        }
//        return null;
//    }
//}
