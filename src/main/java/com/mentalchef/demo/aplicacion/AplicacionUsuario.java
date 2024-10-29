// package com.mentalchef.demo.aplicacion;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.mentalchef.demo.dto.userdtos.UserDtoConverter;
// import com.mentalchef.demo.dto.userdtos.UserGetDto;
// import com.mentalchef.demo.dto.userdtos.UserRegisterDto;
// import com.mentalchef.demo.modelos.Usuario;
// import com.mentalchef.demo.persistencia.IPersistencia;


// @Service
// public class AplicacionUsuario extends Aplicacion<Usuario> {

//     UserDtoConverter userDtoConverter;

//     public AplicacionUsuario(IPersistencia<Usuario> persistencia, UserDtoConverter userDtoConverter) {
//         super(persistencia);
//         this.userDtoConverter = userDtoConverter;
//     }

//     @Override
//     public boolean guardar(Usuario usuario) {
//         return false;
//     }

//     public UserGetDto guardar(UserRegisterDto userRegisterDto) {
//         Usuario usuarioADevolver = null;
        
//         try {
//             if (userRegisterDto.getPassword().compareTo(userRegisterDto.getPasswordConfirm()) == 0) {
//                 usuarioADevolver = userDtoConverter.toUser(userRegisterDto);
//                 if (persistencia.guardar(usuarioADevolver)) {
//                     return userDtoConverter.toUserGetDto(usuarioADevolver);
//                 }
//                 else {
//                     return null;
//                 }
//             }
//             else {
//                 return null;
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public Usuario buscarPorNombre(String nombre) {
//         return persistencia.query("usernamne", nombre).get(0);
//     }
    
// }
