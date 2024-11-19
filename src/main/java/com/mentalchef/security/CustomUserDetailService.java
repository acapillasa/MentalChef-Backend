package com.mentalchef.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mentalchef.demo.aplicacion.impl.AplicacionUsuarios;
import com.mentalchef.demo.modelos.Usuario;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
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
