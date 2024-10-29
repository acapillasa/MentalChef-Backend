package com.mentalchef.demo.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mentalchef.demo.dto.userdtos.UserDtoConverter;

@Configuration
public class DtoConfig {
    
    @Bean
    public UserDtoConverter UserDtoConverter(PasswordEncoder passwordEncoder) {
        return new UserDtoConverter(passwordEncoder);
    }
}
