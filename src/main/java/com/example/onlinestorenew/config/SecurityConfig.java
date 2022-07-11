package com.example.onlinestorenew.config;

import com.example.onlinestorenew.controllers.AccessDeniedController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
//    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        System.out.println("configure");
        AccessDeniedController accessDeniedController = new AccessDeniedController();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/public/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/","/index/**", "/signup", "/good/**", "/cart/**", "/order").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/",true)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedController);
    }

//    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//        UserDetails admin = userBuilder
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//    }
}