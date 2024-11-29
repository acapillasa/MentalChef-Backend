package com.mentalchef.demo.dto.userdtos;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mentalchef.demo.modelos.Chef;
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

    public Chef toChef(UserRegisterDto usuarioDTO) {
        Chef chef = new Chef();
        chef.setUsername(usuarioDTO.getUsername());
        chef.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        chef.setEmail(usuarioDTO.getEmail());
        chef.setRoles(Stream.of(UserRoles.CHEF).collect(Collectors.toSet()));
        return chef;
    }

    public UserGetDto toUserGetDto(Usuario usuario) {
        return UserGetDto.builder().username(usuario.getUsername()).email(usuario.getEmail())
                .role(usuario.getRoles().toString()).build();
    }

    public Usuario toUsuario(UserGetDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setMonedaV(usuarioDto.getMonedaV());
        return usuario;
    }
}
