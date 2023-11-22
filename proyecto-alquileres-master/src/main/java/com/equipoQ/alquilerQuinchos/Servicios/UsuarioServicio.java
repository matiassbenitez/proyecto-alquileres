package com.ejemplo1.biblioteca.servicios;
import com.ejemplo1.biblioteca.Entidades.*;
import com.ejemplo1.biblioteca.enumeraciones.*;
import com.ejemplo1.biblioteca.excepciones.*;
import com.ejemplo1.biblioteca.repositorios.*;
import java.util.*;
import javax.servlet.http.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.context.request.*;
import org.springframework.web.multipart.*;
@Service
public class UsuarioServicio implements UserDetailsService {
//   UserDetailsService para implementar metodos abstractos para poder autenticar usuarios

   @Autowired
   private UsuarioRepositorio usuarioRepositorio;
   @Autowired
   private ImagenServicio imagenServicio;

   @Transactional
//para que agregue la imagen se utiliza el multipart
   public void registrar(MultipartFile archivo, String nombre, String email, String password, String password2) throws MiExcepcion {
      validar(nombre, email, password, password2);
      Usuario usuario = new Usuario();
      usuario.setNombre(nombre);
      usuario.setEmail(email);
//   para codificar la contraseña
      usuario.setPassoword(new BCryptPasswordEncoder().encode(password));
      usuario.setRol(Rol.USER);
      Imagen imagen = imagenServicio.guardar(archivo);
      usuario.setImagen(imagen);
      usuarioRepositorio.save(usuario);
//   se persiste el usuario con .save

   }
   @Transactional
   public void actualizar(MultipartFile archivo, String idUsuario,String nombre,String email,String password,String passowrd2)throws MiExcepcion{
      validar(nombre, email, password, password);
      Optional<Usuario> respuesta= usuarioRepositorio.findById(idUsuario);
      if(respuesta.isPresent()){
         Usuario usuario=respuesta.get();
         usuario.setNombre(nombre);
         usuario.setEmail(email);
         usuario.setPassoword(new BCryptPasswordEncoder().encode(password));
         usuario.setRol(Rol.USER);
         String idImagen=null;
         if(usuario.getImagen()!=null){
            idImagen=usuario.getImagen().getId();
            Imagen imagen= imagenServicio.actualizar(archivo, idImagen);
            usuario.setImagen(imagen);
            usuarioRepositorio.save(usuario);
         }
      }
   }

   private void validar(String nombre, String email, String password, String password2) throws MiExcepcion {
      if (nombre.isEmpty() || nombre == null) {
         throw new MiExcepcion("El nombre no puede estar vacio");
      }
      if (email.isEmpty() || email == null) {
         throw new MiExcepcion("El email no puede estar vacio");
      }
      if (password.isEmpty() || password == null || password.length() <= 5) {
         throw new MiExcepcion("La contraseña debe tener mas de 5 digitos");
      }
      if (!password.equals(password2)) {
         throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales");
      }
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
      if (usuario != null) {
         List<GrantedAuthority> permisos = new ArrayList<>();
         GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
         permisos.add(p);
//         luego de que el usuario ingreso a la plataforma y le dimos permisos insertamos una llamada para 
//interceptar al usuario y guardarlo en la sesion web 
         ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//se castea la linea. requeiere el contexto y atributos del mismo
         HttpSession session = attr.getRequest().getSession();
//       en la variable session se setea el atributo usariosession que contiene el valor con todos los datos del usuario
         session.setAttribute("usuariosession", usuario);
         return new User(usuario.getEmail(), usuario.getPassoword(), permisos);

      }
      return null;
   }
     public Usuario getOne(String id){
        return usuarioRepositorio.getOne(id);
    }
}
