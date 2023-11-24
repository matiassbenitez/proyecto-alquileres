/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipoQ.alquilerQuinchos.servicios;

import com.equipoQ.alquilerQuinchos.entidades.Usuario;
import com.equipoQ.alquilerQuinchos.enums.Rol;
import com.equipoQ.alquilerQuinchos.excepciones.MiException;
import com.equipoQ.alquilerQuinchos.repositorios.UsuarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Karen
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrarUsuario(MultipartFile archivo, String nombre, String email, String password, String password2, Integer telefono) throws MiException {

        // validar(nombre, email, password, password2);
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(Rol.CLIENTE);

        //usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        //Imagen imagen = imagenServicio.guardar(archivo);
        // usuario.setImagen(imagen);
        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public void actualizar(MultipartFile archivo, String idUsuario, String nombre, String email, String password, String password2, Integer telefono) throws MiException {
        //validar(nombre, email, password, password);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            //usuario.setPassword(new BCryptPasswordEncoder().encode(password));
            usuario.setPassword(password);
            usuario.setRol(Rol.CLIENTE);
//         String idImagen = null;
//         if (usuario.getImagen() != null) {
//            idImagen = usuario.getImagen().getId();
//            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
//            usuario.setImagen(imagen);
//            usuarioRepositorio.save(usuario);
        }
    }
   

    
    
    public boolean eliminarPorEmail(String email) {
        // Buscar el usuario por correo electrónico
        Optional<Usuario> usuario = usuarioRepositorio.findById(email);

        // Verificar si el usuario existe antes de intentar eliminarlo
        if (usuario != null) {
            usuarioRepositorio.delete(usuario);
            return true;
        } else {
            return false;
        }
    }
    

}
               
                       
    
    
    
    
    
    
    
    



