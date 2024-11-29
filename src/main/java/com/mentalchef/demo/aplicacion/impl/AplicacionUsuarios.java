package com.mentalchef.demo.aplicacion.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mentalchef.demo.aplicacion.IAplicacionUsuarios;
import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
import com.mentalchef.demo.dto.userdtos.UserGetDto;
import com.mentalchef.demo.dto.userdtos.UserRegisterDto;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionUsuarios implements IAplicacionUsuarios {

    private static final Logger logger = LoggerFactory.getLogger(AplicacionUsuarios.class);

    private final IPersistencia<Usuario> persistencia;
    private final UserDtoConverter userDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Long id) {
        try {
            return persistencia.obtener(id);
        } catch (Exception e) {
            logger.error("Error al obtener el usuario con id: {}", id, e);
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return persistencia.obtenerTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorNombre(String nombre) {
        List<Usuario> usuarios = persistencia.query("username", nombre);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Override
    @Transactional
    public Optional<UserGetDto> guardarPinche(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getPasswordConfirm())) {
            logger.warn("Las contraseñas no coinciden para el usuario: {}", userRegisterDto.getUsername());
            return Optional.empty();
        }
        System.out.println(userRegisterDto.toString());
        System.out.println("NO PASA POR AQUI");

        try {
            Usuario usuario = userDtoConverter.toPinche(userRegisterDto);
            if (persistencia.guardar(usuario)) {
                return Optional.of(userDtoConverter.toUserGetDto(usuario));
            } else {
                logger.warn("Error al guardar el usuario en la base de datos: {}", userRegisterDto.getUsername());
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error al guardar el usuario: {}", userRegisterDto.getUsername(), e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<UserGetDto> guardarChef(UserRegisterDto userRegisterDto) {
        logger.info("Attempting to register chef: {}", userRegisterDto.getUsername());
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getPasswordConfirm())) {
            logger.warn("Las contraseñas no coinciden para el usuario: {}", userRegisterDto.getUsername());
            return Optional.empty();
        }

        try {
            Usuario usuario = userDtoConverter.toChef(userRegisterDto);
            if (persistencia.guardar(usuario)) {
                return Optional.of(userDtoConverter.toUserGetDto(usuario));
            } else {
                logger.warn("Error al guardar el usuario en la base de datos: {}", userRegisterDto.getUsername());
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error al guardar el usuario: {}", userRegisterDto.getUsername(), e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public String insertUsuario(Usuario usuario) {
        try {
            persistencia.guardar(usuario);
            return "Usuario insertado con éxito";
        } catch (Exception e) {
            logger.error("Error al insertar el usuario: {}", usuario.getUsername(), e);
            return "Error al insertar usuario.";
        }
    }

    @Override
    @Transactional
    public String deleteUsuario(Usuario usuario) {
        try {
            persistencia.eliminar(usuario);
            return "Usuario eliminado";
        } catch (Exception e) {
            logger.error("Error al eliminar el usuario: {}", usuario.getUsername(), e);
            return "Error al eliminar el usuario";
        }
    }

    @Override
    @Transactional
    public String updateUsuario(Usuario usuario) {
        try {
            persistencia.actualizar(usuario);
            return "Usuario actualizado";
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario: {}", usuario.getUsername(), e);
            return "Error al actualizar el usuario";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorNombreConCompras(String username) {
        try {
            return persistencia.buscarPorNombreConCompras(username);
        } catch (Exception e) {
            logger.error("Error al buscar usuario con compras: {}", username, e);
            return null;
        }
    }
}
