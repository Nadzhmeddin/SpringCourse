package ru.geekbrains.java.newproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.geekbrains.java.newproject.model.RoleName;
import ru.geekbrains.java.newproject.model.UserRole;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r.anyRequest().permitAll()).build();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .formLogin(Customizer.withDefaults())
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/home/projects/**").hasAuthority(RoleName.ADMIN.getName())
////                        .requestMatchers("/home/timesheets/**").hasAuthority(RoleName.USER.getName())
////                        .requestMatchers("/projects", "/employees", "/timesheets").hasAuthority(RoleName.REST.getName())
//                        .anyRequest().authenticated()
//                )
//                .build();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
