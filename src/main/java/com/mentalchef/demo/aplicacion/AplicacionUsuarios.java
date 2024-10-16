package com.mentalchef.demo.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AplicacionUsuarios implements IAplicacionUsuarios {

    IPersistencia<Usuario> persistencia;

    @Override
    public Usuario getUsuario(Long id) {
        try {
            System.out.println("egpiw"+persistencia.obtener(id));
            return persistencia.obtener(id);
        } catch (Exception e) {
            System.err.println("Error al obtener el usuario con id: " + id + " - " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        return persistencia.obtenerTodos();
    }

    @Override
    public String insertUsuario(Usuario usuario) {
        try {
            persistencia.guardar(usuario);
            return "Usuario insertada con exito";
        } catch (Exception e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
            return "Error al insertar usuario.";
        }
    }

    @Override
    public String deleteUsuario(Usuario usuario) {
        try {
            persistencia.eliminar(usuario);
            return "Usuario eliminado";
        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
            return "Error al eliminar el usuario";
        }
    }

    @Override
    public String updateUsuario(Usuario usuario) {
        try {
            persistencia.actualizar(usuario);
            return "Usuario actualizado";
        } catch (Exception e) {
            System.err.println("Error al acutalizar el usuario: " + e.getMessage());
            return "Error al actualizar el usuario";
        }
    }

}
