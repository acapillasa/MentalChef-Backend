package com.mentalchef.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentalchef.demo.aplicacion.impl.AplicacionUsuarios;
import com.mentalchef.demo.dto.userdtos.LoginDto;
import com.mentalchef.demo.dto.userdtos.UserGetDto;
import com.mentalchef.demo.dto.userdtos.UserRegisterDto;
import com.mentalchef.demo.modelos.Chef;
import com.mentalchef.demo.modelos.Pinche;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.security.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import jakarta.annotation.security.PermitAll;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
@EnableMethodSecurity
public class UsuarioController {

    AplicacionUsuarios aplicacionUsuarios;

    AuthenticationManager authenticationManager;

    JwtTokenProvider tokenProvider;

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
        Optional<UserGetDto> toReturn = aplicacionUsuarios.guardarPinche(entity);

        return toReturn
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user))
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PostMapping("/registrarChef")
    public ResponseEntity<UserGetDto> registrarChef(@RequestBody UserRegisterDto entity) {
        System.out.println("Received request to register chef: " + entity.getUsername());
        Optional<UserGetDto> toReturn = aplicacionUsuarios.guardarChef(entity);

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

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public String getMethodName(@AuthenticationPrincipal Usuario usuario) {
        // ROL
        // return
        // SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        // Informacion usuario
        // return
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        // String toReturn;

        // toReturn = "Usuario: " + usuario.getUsername() + "\n" + "Email: " + usuario.getEmail() + "\n" + "Descripcion: "
        //         + usuario.getDescripcion() + "\n" + "Moneda: " + usuario.getMonedaV() + "\n" + "Fecha de creacion: "
        //         + usuario.getFechaCreacion() + "\n" + "Fecha de actualizacion: " + usuario.getFechaActualizacion();
        // return toReturn;
        // return "hola mundo";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        try {
            // Log the login attempt
            System.out.println("Attempting to authenticate user: " + loginDto.getUsername() + " - " + loginDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                    .path("/")
                    .httpOnly(true)
                    .secure(true) // Ensure the Secure attribute is set
                    .sameSite("None")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            // Log successful authentication
            System.out.println("User authenticated successfully: " + loginDto.getUsername());

            return ResponseEntity.ok("Logged in");
        } catch (BadCredentialsException e) {
            // Log the specific exception for bad credentials
            System.out.println("Bad credentials for user: " + loginDto.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            System.out.println("Authentication failed for user: " + loginDto.getUsername());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    @PermitAll
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(0) // Invalidate the cookie
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Boolean> isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser"));
        return ResponseEntity.ok(isAuthenticated);
    }

}
