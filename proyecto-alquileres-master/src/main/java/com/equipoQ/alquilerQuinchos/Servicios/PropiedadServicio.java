package com.ejemplo1.biblioteca.servicios;
import com.ejemplo1.biblioteca.Entidades.*;
import com.ejemplo1.biblioteca.excepciones.*;
import com.ejemplo1.biblioteca.repositorios.*;
import java.util.*;
import static java.util.Collections.list;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
@Service
public class LibroServicio {

//Autowired, autoinyecta dependencias. esta variable va a ser inicializada por el servidor,
//no hace falta inicializarla para poder operar con la misma   
   @Autowired
   private LibroRepositorio libroRepositorio;
   @Autowired
   private AutorRepositorio autorRepositorio;
   @Autowired
   private EditorialRepositorio editorialRepositorio;
//   cargar datos necesarios para crear un libro por parametro a traves de un formulario

//  Transactional utilizar cada que se crea algo 
   @Transactional
   public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {
//    se le agrega llamado a validacion que esta a lo ultimo para buscar excepciones.
      validar(isbn, titulo, idAutor, idEditorial, ejemplares);
      //se crea un autor que va a ser igual a lo que me retorne este llamado al repositorio
      Autor autor = autorRepositorio.findById(idAutor).get();
      Editorial editorial = editorialRepositorio.findById(idEditorial).get();
      Libro libro = new Libro();

      libro.setIsbn(isbn);
      libro.setTitulo(titulo);
      libro.setEjemplares(ejemplares);
      libro.setAlta(new Date());
      libro.setAutor(autor);
      libro.setEditorial(editorial);

      //recibe entidad por parametro y la guarda(persiste) en la base de datos.
      libroRepositorio.save(libro);
   }
//   metodo para listar los libros de la base de datos, no recibe parametros.
//   se rellena con lo que retorna el repositorio

   public List<Libro> listarLibros() {
      List<Libro> libros = new ArrayList();
//  FindAll(jpa) encuentra todo lo que se encuentre en libroRepositorio.
      libros = libroRepositorio.findAll();
      return libros;
   }

   public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiExcepcion {
//     Libro libro= libroRepositorio.findById(ISBN).get();
//     si el id que se envia por parametro tiene errores o esta incorrecto o no existe
//     Se utiliza optional
//     Optional es un objeto contenedor que puede contener un valor nulo o no nulo
//     Si el valor esta presente devuelve true y retorna el valor con get.
      validar(isbn, titulo, idAutor, idEditorial, ejemplares);
      Optional<Libro> respuesta = libroRepositorio.findById(isbn);
      Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
      Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

      Autor autor = new Autor();
      Editorial editorial = new Editorial();
      if (respuestaAutor.isPresent()) {
         autor = respuestaAutor.get();
      }
      if (respuestaEditorial.isPresent()) {
         editorial = respuestaEditorial.get();
      }
      if (respuesta.isPresent()) {
         Libro libro = respuesta.get();
         libro.setAutor(autor);
         libro.setTitulo(titulo);
         libro.setEditorial(editorial);
         libro.setEjemplares(ejemplares);
         libroRepositorio.save(libro);
      }
   }
       public Libro getOne(Long isbn) {
    
        return libroRepositorio.getOne(isbn);
    }   
       public void eliminar(Long isbn){

       libroRepositorio.deleteById(isbn);
        
    }

//  establecer que si el metodo se ejecuta sin excepciones se realiza un commit a la base de datos y aplica cambios
//  si lanza excepcion y no es atrapada no aplica nada a la base de datos.
//  se le agrega despues del parametro que puede recibir excepciones.
   private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiExcepcion {
      if (isbn == null) {
         throw new MiExcepcion("El isbn no puede ser nulo");
      }
      if (titulo.isEmpty() || titulo == null) {
         throw new MiExcepcion("El titulo no puede ser nulo");
      }
      if (ejemplares == null) {
         throw new MiExcepcion("Los ejemplares no pueden ser nulos");
      }
      if (idAutor.isEmpty() || idAutor == null) {
         throw new MiExcepcion("El idAutor no puede ser nulo");
      }
      if (idEditorial.isEmpty() || idEditorial == null) {
         throw new MiExcepcion("El idEditorial no puede ser nulo");
      }
   }
}
