package com.mentalchef.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.mentalchef.demo.dto.userdtos.UserUpdateDto;
import com.mentalchef.demo.modelos.Chef;
import com.mentalchef.demo.modelos.Pinche;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.security.jwt.JwtTokenProvider;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
@EnableMethodSecurity
public class UsuarioController {

    @Autowired
    private AplicacionUsuarios aplicacionUsuarios;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Boolean> comprobarNombre(@PathVariable String nombre) {
        Usuario usuario = aplicacionUsuarios.buscarPorNombre(nombre);
        return ResponseEntity.ok(usuario != null ? true : false);
    }
    

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UserGetDto> actualizarUsuario(@PathVariable Long id, @RequestBody UserUpdateDto usuarioActualizado) {
        Usuario usuario = aplicacionUsuarios.getUsuario(id);

        // Verifica si el usuario existe
        if (usuario == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Verifica si la contraseña es adecuada si no está vacía
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            if (!esContrasenaAdecuada(usuarioActualizado.getPassword())) {
                return ResponseEntity.badRequest().body(null);
            }
            usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }

        // Actualiza los campos del usuario con los datos del cuerpo de la solicitud
        usuario.setUsername(usuarioActualizado.getUsername());
        usuario.setMonedaV(usuarioActualizado.getMonedaV());
        usuario.setEmail(usuarioActualizado.getEmail());
        // Puedes agregar más campos si es necesario

        // Llama a la lógica para actualizar el usuario en la base de datos
        aplicacionUsuarios.updateUsuario(usuario);

        UserGetDto userDto = new UserGetDto();
        userDto.setUsername(usuario.getUsername());
        userDto.setEmail(usuario.getEmail());
        userDto.setMonedaV(usuario.getMonedaV());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    private boolean esContrasenaAdecuada(String contrasena) {
        // Ejemplo de validación: longitud mínima de 8 caracteres
        if (contrasena == null || contrasena.length() < 8) {
            return false;
        }
        // Validación de al menos un carácter especial
        String specialCharacters = "!@#$%^&*()-+";
        boolean hasSpecialCharacter = contrasena.chars().anyMatch(ch -> specialCharacters.indexOf(ch) >= 0);
        if (!hasSpecialCharacter) {
            return false;
        }
        // Puedes agregar más validaciones aquí (por ejemplo, mayúsculas, números, etc.)
        return true;
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
    public ResponseEntity<?> getMethodName(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            System.out.println("Authenticated user is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // Cargar el usuario completo desde la base de datos utilizando el nombre de usuario
        Usuario usuario = aplicacionUsuarios.buscarPorNombre(userDetails.getUsername());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Crear el DTO del usuario
        UserGetDto userDto = new UserGetDto();
        userDto.setId(usuario.getId());
        userDto.setUsername(usuario.getUsername());
        userDto.setEmail(usuario.getEmail());
        userDto.setMonedaV(usuario.getMonedaV());
        userDto.setRole(userDetails.getAuthorities().stream().findFirst().get().getAuthority());

        System.out.println("Authenticated user: " + userDetails.getUsername());
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/monedasV")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMonedasV(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            System.out.println("Authenticated user is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        if (!(userDetails instanceof Usuario)) {
            System.out.println("Authenticated user is not an instance of Usuario");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        Usuario usuario = (Usuario) userDetails;
        System.out.println("Authenticated user: " + usuario.getUsername());
        return ResponseEntity.ok(usuario.getMonedaV());
    }

    @PostMapping("/monedasV/preguntaDiaria")
    public void postMethodName(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = aplicacionUsuarios.buscarPorNombre(userDetails.getUsername());

        usuario.setMonedaV(usuario.getMonedaV() + 5);
        
        aplicacionUsuarios.insertUsuario(usuario);
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
        System.out.println("Authentication status: " + isAuthenticated);
        return ResponseEntity.ok(isAuthenticated);
    }

}
