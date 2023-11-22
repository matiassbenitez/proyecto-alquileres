package com.ejemplo1.biblioteca.Controlador;
import com.ejemplo1.biblioteca.Entidades.*;
import com.ejemplo1.biblioteca.excepciones.*;
import com.ejemplo1.biblioteca.servicios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/libro")
public class LibroControlador {

   @Autowired
   private AutorServicio autorServicio;

   @Autowired
   private EditorialServicio editorialServicio;

   @Autowired
   private LibroServicio libroServicio;

   @GetMapping("/registrar")
//   se le agrega un modelMap para que a traves de el podamos indentar los valores de autor y editorial
   public String registrar(ModelMap modelo) {
      List<Autor> autores = autorServicio.listarAutores();
      List<Editorial> editoriales = editorialServicio.listarEditoriales();
//      se ancla a un modelo para que lo muestre en la interfaz de usuario 
//      en este caso bajo el nombre autores, los autores de la lista
      modelo.addAttribute("autores", autores);
      modelo.addAttribute("editoriales", editoriales);

      return "libro_form.html";
   }

   @PostMapping("/registro")
//   se le agrega el (required=false) para que si el isbn es nulo ingresa igual al controlador y maneja la excepcion desde el servicio 
   public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
           @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
           @RequestParam String idEditorial, ModelMap modelo) {
      //modelmap sirve para que insrtemos toda la informacion que vamos a mostrar  por pantalla en este caso el error
      try {

         libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
         modelo.put("Exito", "El libro fue cargado correctametne");

      } catch (MiExcepcion ex) {
         List<Autor> autores = autorServicio.listarAutores();
         List<Editorial> editoriales = editorialServicio.listarEditoriales();
         // se ancla a un modelo para que lo muestre en la interfaz de usuario 
         // en este caso bajo el nombre autores, los autores de la lista
         modelo.addAttribute("autores", autores);
         modelo.addAttribute("editoriales", editoriales);
         modelo.put("error", ex.getMessage());

         return "libro_form.html";
      }

      return "index.html";
   }
   @GetMapping ("/listar")
   public String listar(ModelMap modelo){
      List<Libro> libros= libroServicio.listarLibros();
      modelo.addAttribute("libros", libros);
//    returna el html libro_list
      return "libro_list";
   }
 @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        modelo.put("libro", libroServicio.getOne(isbn));

        return "libro_modify.html";

    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap modelo) {

        try {

            libroServicio.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);

            return "redirect:../listar";

        } catch (MiExcepcion e) {

            modelo.put("error", e.getMessage());
            
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            return "libro_modify.html";
        }
    }

    @GetMapping("/eliminar/{isbn}")
    public String eliminar(@PathVariable Long isbn) {

        libroServicio.eliminar(isbn);

        return "redirect:../listar";
    }
}
