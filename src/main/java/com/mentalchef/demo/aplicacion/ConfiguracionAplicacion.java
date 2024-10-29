// package com.mentalchef.demo.aplicacion;


// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
// import com.mentalchef.demo.modelos.Usuario;
// import com.mentalchef.demo.persistencia.IPersistencia;

// @Configuration
// public class ConfiguracionAplicacion {

//     @Bean
//     public AplicacionUsuario getAplicacionUsuario(IPersistencia<Usuario> persistenciaUsuario, UserDtoConverter userConverter, PasswordEncoder passwordEncoder)
//     {
//         return new AplicacionUsuario(persistenciaUsuario, userConverter);
//     }


//     public String getProperty(String propertyName, String defaultValue)
//     {
//         String propiedad = System.getProperty(propertyName);
//         if(propiedad == null)
//         {
//             propiedad = defaultValue;
//         }

//         return propiedad;
//     }
    
// }
