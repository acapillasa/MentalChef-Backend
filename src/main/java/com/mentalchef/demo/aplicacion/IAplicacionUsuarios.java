package com.mentalchef.demo.aplicacion;

import java.util.List;

import java.util.Optional;

import com.mentalchef.demo.dto.userdtos.UserGetDto;
import com.mentalchef.demo.dto.userdtos.UserRegisterDto;
import com.mentalchef.demo.modelos.Usuario;

public interface IAplicacionUsuarios {

    public Usuario getUsuario(Long id);

    public List<Usuario> getUsuarios();

    public Usuario buscarPorNombre(String nombre);

    public Optional<UserGetDto> guardarPinche(UserRegisterDto userRegisterDto);

    public Optional<UserGetDto> guardarChef(UserRegisterDto userRegisterDto);

    public String insertUsuario(Usuario usuario);

    public String deleteUsuario(Usuario usuario);

    public String updateUsuario(Usuario usuario);
}
