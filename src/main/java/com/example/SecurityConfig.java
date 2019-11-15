package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService myUserDetailsService;

//    protected void configure(HttpSecurity http) throws Exception{
//
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
//        //基于内存的用户存储、认证
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123").authorities("admin")   //添加admin角色
//                .and()
//                .withUser("user").password("123").roles("user") //添加user角色，roles方法默认添加的角色是ROLE_user,authorities方法添加的角色是user
//                .and()
//                .withUser("user2").password("123").roles("user2") //添加user绝杀
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());

        auth.userDetailsService(myUserDetailsService)// 设置自定义的userDetailsService
//                .passwordEncoder(passwordEncoder());
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }

    @Override
    public void configure(HttpSecurity http)throws Exception {
        http
                .authorizeRequests()
                /**
                 *用hasRole时，在我们返回的UserDetails的Authority需要加Role_ADMIN,坑爹,或者直接用hasAuthority替代
                 * 不做配置的接口所有角色可以访问，个别接口需要限制，用hasRole进行配置，限制user接口只有user和admin角色可以访问
                 */
                .antMatchers("/user1").hasAnyAuthority("user1","admin")
                .antMatchers("/admin").hasAuthority("admin") //限制admin接口只有admin角色可以访问
                .antMatchers("/user2").hasAnyAuthority("user2","admin")
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic()
                .and().logout();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.getApplicationContext().getBean(UserDetailsService.class));
//    }
}
