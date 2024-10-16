package com.mentalchef.demo.aplicacion;

import java.util.List;

import com.mentalchef.demo.modelos.Usuario;

public interface IAplicacionUsuarios {

    public Usuario getUsuario(Long id);

    public List<Usuario> getUsuarios();

    public String insertUsuario(Usuario usuario);

    public String deleteUsuario(Usuario usuario);

    public String updateUsuario(Usuario usuario);
}
