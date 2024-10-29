package com.mentalchef.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.AplicacionUsuarios;
import com.mentalchef.demo.dto.userdtos.UserGetDto;
import com.mentalchef.demo.dto.userdtos.UserRegisterDto;
import com.mentalchef.demo.modelos.Chef;
import com.mentalchef.demo.modelos.Pinche;
import com.mentalchef.demo.modelos.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    AplicacionUsuarios aplicacionUsuarios;

    @GetMapping("")
    public List<Usuario> getUsuarios() {
        return aplicacionUsuarios.getUsuarios();
    }

    @GetMapping("/id/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        System.out.println(aplicacionUsuarios.getUsuario(id));
        return aplicacionUsuarios.getUsuario(id);
    }

    @PostMapping("/registrar")
    public ResponseEntity<UserGetDto> registrar(@RequestBody UserRegisterDto entity) {
        Optional<UserGetDto> toReturn = aplicacionUsuarios.guardar(entity);

        return toReturn
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user))
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = aplicacionUsuarios.getUsuario(id);

        // Verifica si el usuario existe
        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos del usuario con los datos del cuerpo de la solicitud
        usuario.setUsername(usuarioActualizado.getUsername());
        usuario.setMonedaV(usuarioActualizado.getMonedaV());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setDescripcion(usuarioActualizado.getDescripcion());
        usuario.setPassword(usuarioActualizado.getPassword());
        // Puedes agregar más campos si es necesario

        // Llama a la lógica para actualizar el usuario en la base de datos
        aplicacionUsuarios.updateUsuario(usuario);

        return new ResponseEntity<>("Pinche actualizado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/insertarPrueba")
    public String insertarUsuarioPrueba() {

        Usuario usuario = new Usuario("Álvaro", "soy yo", "acapillasa22dw@ikzubirimanteo.com", "1234", 0);
        Pinche pinche = new Pinche("pepe", "nide", "111", "12345", 0, 0);
        Chef chef = new Chef("lara", "nadiee", "rampo", "12345566", 0, 0);
        aplicacionUsuarios.insertUsuario(pinche);
        aplicacionUsuarios.insertUsuario(chef);
        return aplicacionUsuarios.insertUsuario(usuario);
    }

}
