package com.example;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @GetMapping("/admin")
    public String hello(){
        return "admin";
    }

    @GetMapping("/user1")
    public String test(){
        return "user1";
    }

    @GetMapping("/user2")
    public String user2(){
        return "user2";
    }

    @GetMapping("/test2")
    public String test2(HttpServletRequest request){
        return "test2";
    }


    @RequestMapping("/info")
    public String productInfo(){
        String currentUser = "";
        //获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户账号信息
        Object principl = authentication.getPrincipal();
        if(principl instanceof UserDetails) {
            currentUser = ((UserDetails)principl).getUsername();
        }else {
            currentUser = principl.toString();
        }
        return " 登陆用户: "+currentUser;
    }

}
