package com.cpan228.webshop.config;

import com.cpan228.webshop.model.User;
import com.cpan228.webshop.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * Configuration class for Spring is like a holder of beans. We can use this
 * class to define beans that we want to use in our application.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(username);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console())
                .hasRole("ADMIN")
                .requestMatchers("/add")
                .hasAnyRole("ADMIN","WAREHOUSE")
                .requestMatchers("/itemlist")
                .hasAnyRole("ADMIN","WAREHOUSE","USER")
                .requestMatchers("/admindash")
                .hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/itemlist", true)
                .and()
                .logout()
                .logoutSuccessUrl("/");

        //for h2-console accessibility
        http.headers().frameOptions().disable();
        http.csrf().disable();

        return http.build();

    }
}
