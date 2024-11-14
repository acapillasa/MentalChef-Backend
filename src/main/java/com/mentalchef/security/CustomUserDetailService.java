package com.mentalchef.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;

import com.mentalchef.demo.aplicacion.impl.AplicacionUsuarios;
import com.mentalchef.demo.modelos.Usuario;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private AplicacionUsuarios aplicacionUsuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = aplicacionUsuarios.buscarPorNombre(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService(aplicacionUsuarios);
    }
}
