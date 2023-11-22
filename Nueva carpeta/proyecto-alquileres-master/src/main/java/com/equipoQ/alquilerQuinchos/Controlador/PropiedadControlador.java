package com.equipoQ.alquilerQuinchos.Controlador;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Servicios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

//   @Autowired
//   private AutorServicio autorServicio;
//
//   @Autowired
//   private EditorialServicio editorialServicio;
//
   @Autowired
   private PropiedadServicio propiedadServicio;

   @GetMapping("/registrar")
//   se le agrega un modelMap para que a traves de el podamos indentar los valores de autor y editorial
   public String registrar(ModelMap modelo) {
//      List<Autor> autores = autorServicio.listarAutores();
//      List<Editorial> editoriales = editorialServicio.listarEditoriales();
//      se ancla a un modelo para que lo muestre en la interfaz de usuario 
//      en este caso bajo el nombre autores, los autores de la lista
//      modelo.addAttribute("autores", autores);
//      modelo.addAttribute("editoriales", editoriales);

      return "propiedad_form.html";
   }

   @PostMapping("/registro")
//   se le agrega el (required=false) para que si el isbn es nulo ingresa igual al controlador y maneja la excepcion desde el servicio 
   public String registro(@RequestParam(required = false) Long id, @RequestParam String titulo,@RequestParam String calle,@RequestParam Integer direccion,
           @RequestParam String localidad,@RequestParam String provincia, ModelMap modelo) {
      //modelmap sirve para que insrtemos toda la informacion que vamos a mostrar  por pantalla en este caso el error
      try {
//Long id, String titulo, String calle,Integer direccion,String localidad,String provincia
         propiedadServicio.crearPropiedad(id, titulo, calle, direccion, localidad, provincia);
         modelo.put("Exito", "La propiedad fue cargada correctametne");

      } catch (MiExcepcion ex) {
//         List<Autor> autores = autorServicio.listarAutores();
//         List<Editorial> editoriales = editorialServicio.listarEditoriales();
         // se ancla a un modelo para que lo muestre en la interfaz de usuario 
         // en este caso bajo el nombre autores, los autores de la lista
//         modelo.addAttribute("autores", autores);
//         modelo.addAttribute("editoriales", editoriales);
         modelo.put("error", ex.getMessage());

         return "propiedad_form.html";
      }

      return "index.html";
   }
   @GetMapping ("/listar")
   public String listar(ModelMap modelo){
      List<Propiedad> propiedades= propiedadServicio.listarPropiedades();
      modelo.addAttribute("propiedades",propiedades);
      return "propiedad_list";
   }
 @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {

//        List<Autor> autores = autorServicio.listarAutores();
//        List<Editorial> editoriales = editorialServicio.listarEditoriales();
//
//        modelo.addAttribute("autores", autores);
//        modelo.addAttribute("editoriales", editoriales);

        modelo.put("propiedad", propiedadServicio.getOne(id));

        return "propiedad_modify.html";

    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String calle,Integer direccion,String localidad,String provincia, ModelMap modelo) {

        try {

            propiedadServicio.modificarPropiedad(id, titulo, calle, direccion, localidad, provincia);

            return "redirect:../listar";

        } catch (MiExcepcion e) {

            modelo.put("error", e.getMessage());
            
//            List<Autor> autores = autorServicio.listarAutores();
//            List<Editorial> editoriales = editorialServicio.listarEditoriales();
//
//            modelo.addAttribute("autores", autores);
//            modelo.addAttribute("editoriales", editoriales);

            return "propiedad_modify.html";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        propiedadServicio.eliminar(id);

        return "redirect:../listar";
    }
}
