package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(name);
        if (user == null) {
            //log.warn("User: {} not found", login);
            log.info("用户不存在");
            throw new UsernameNotFoundException("User " + name + " was not found in db");
            //这里找不到必须抛异常

        }

        // 2. 设置角色
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String[] roles = user.getRole().split(",");
        for(String role:roles){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(name,
                user.getPassword(), grantedAuthorities);
    }
}
