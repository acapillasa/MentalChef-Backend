package com.mentalchef.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mentalchef.demo.aplicacion.AplicacionUsuarios;

@Configuration
public class SecurityConf {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AplicacionUsuarios aplicacionUsuarios) {
        return new CustomUserDetailService(aplicacionUsuarios);
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    //     UserDetails user1 = User.builder()
    //             .username("user1")
    //             .password(passwordEncoder().encode("1234"))
    //             .roles("ADMIN")
    //             .build();

    //     UserDetails user2 = User.builder()
    //             .username("user2")
    //             .password(passwordEncoder().encode("123456"))
    //             .roles("PINCHE")
    //             .build();

    //     manager.createUser(user1);
    //     manager.createUser(user2);

    //     return manager;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                
                // .requestMatchers("/preguntas/**")
                // .authenticated()

                // .requestMatchers("/categorias/**")
                // .hasRole("/chef")
                
                .anyRequest().permitAll()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
