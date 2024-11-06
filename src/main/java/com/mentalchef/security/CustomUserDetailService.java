package com.mentalchef.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mentalchef.demo.aplicacion.AplicacionUsuarios;
import com.mentalchef.demo.modelos.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private AplicacionUsuarios aplicacionUsuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = aplicacionUsuarios.buscarPorNombre(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return user;
    }

}
