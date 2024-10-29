package com.mentalchef.demo.dto.userdtos;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.modelos.Pinche;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.demo.modelos.usuario.UserRoles;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserDtoConverter {

    private PasswordEncoder passwordEncoder;

    public Pinche toPinche(UserRegisterDto usuarioDTO) {
        Pinche pinche = new Pinche();
        pinche.setUsername(usuarioDTO.getUsername());
        pinche.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        pinche.setEmail(usuarioDTO.getEmail());
        pinche.setRoles(Stream.of(UserRoles.PINCHE).collect(Collectors.toSet()));
        return pinche;
    }
    
    

    public UserGetDto toUserGetDto(Usuario usuario) {
        return UserGetDto.builder().username(usuario.getUsername()).email(usuario.getEmail())
                .role(usuario.getRoles().toString()).build();
    }
}
