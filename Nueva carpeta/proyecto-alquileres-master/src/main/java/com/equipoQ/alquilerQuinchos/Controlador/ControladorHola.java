package com.equipoQ.alquilerQuinchos.Controlador;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Servicios.*;
import java.util.logging.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.multipart.*;

@Controller
//RequestMapping configura cual es la url que escucha este controlador, en este caso escuchar a partir de la " / " 
@RequestMapping("/")
//   Cuando ingrese a la url / se ejecuta todo lo que contiene este metodo. 
public class ControladorHola {

   @Autowired
   private UsuarioServicio usuarioServicio;

   @GetMapping("/")
   public String index() {
      return "index.html";
   }

   @GetMapping("/registrar")
   public String registrar() {
      return "registro.html";
   }

   @PostMapping("/registro")
   public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo, MultipartFile archivo) {

      try {

         usuarioServicio.registrar(archivo, nombre, email, password, password2);

         return "redirect:/";

      } catch (MiExcepcion e) {

         modelo.put("error", e.getMessage());
         modelo.put("nombre", nombre);
         modelo.put("email", email);
         modelo.put("password", password);
         modelo.put("password2", password2);

         return "registro.html";
      }

   }

   @GetMapping("/login")
   public String login(@RequestParam(required = false) String error, ModelMap modelo) {

      if (error != null) {

         modelo.put("error", "Usuario o Contraseña incorrecto.");
      }

      return "login.html";
   }
//    se utiliza la siguiente linea para que autorice el acceso a este metodo bajo determinadas reglas
//    en este caso que tengan rol de user o admin, estar logeados.

   @GetMapping("/inicio")
//    recibe por parametro un httpSession que contiene session
   public String inicio(HttpSession session) {
      Usuario logueado = (Usuario) session.getAttribute("usuariosession");
//       se castea recibe usuario y toma el atributo usariosession y lo guarda en logueado
      if (logueado.getRol().toString().equals("ADMIN")) {
         return "redirect:/admin/dashboard";
      }
      return "inicio.html";
   }
//perfil se encarga de guardar los datos que tiene la sesion iniciada y con el modelo.put para que se autocomplete el 
//usuario_modificar.html    

   @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_ADMIN','ROLE_PROPIETARIO')")
   @GetMapping("/perfil")
   public String perfil(ModelMap modelo, HttpSession session) {
      Usuario usuario = (Usuario) session.getAttribute("usuariosession");
      modelo.put("usuario", usuario);
      return "usuario_modify.html";
   }

   @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_PROPIETARIO', 'ROLE_ADMIN')")
   @PostMapping("/perfil/{id}")
   public String actualizar(MultipartFile archivo, @PathVariable String id, @RequestParam String nombre, @RequestParam String email,
           @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

      try {
         usuarioServicio.actualizar(archivo, id, nombre, email, password, password2);

         modelo.put("exito", "Usuario actualizado correctamente!");

         return "inicio.html";
      } catch (MiExcepcion ex) {

         modelo.put("error", ex.getMessage());
         modelo.put("nombre", nombre);
         modelo.put("email", email);

         return "usuario_modify.html";
      }

   }

}
