package com.equipoQ.alquilerQuinchos.Servicios;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import com.equipoQ.alquilerQuinchos.Repositorios.*;
import java.util.*;
import static java.util.Collections.list;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
@Service
public class PropiedadServicio {
// hay que setear la propiedad con el usuario que este logeado
//Autowired, autoinyecta dependencias. esta variable va a ser inicializada por el servidor,
//no hace falta inicializarla para poder operar con la misma   
   @Autowired
   private PropiedadRepositorio propiedadRepositorio;
//   @Autowired
//   private AutorRepositorio autorRepositorio;
//   @Autowired
//   private EditorialRepositorio editorialRepositorio;
//   cargar datos necesarios para crear un libro por parametro a traves de un formulario

//  Transactional utilizar cada que se crea algo 
   @Transactional
   public void crearPropiedad(Long id, String titulo, String calle,Integer direccion,String localidad,String provincia) throws MiExcepcion {
//    se le agrega llamado a validacion que esta a lo ultimo para buscar excepciones.
      validar(id, titulo, calle,direccion, localidad, provincia);
      //se crea un autor que va a ser igual a lo que me retorne este llamado al repositorio
//      Autor autor = autorRepositorio.findById(idAutor).get();
//      Editorial editorial = editorialRepositorio.findById(idEditorial).get();
      Propiedad propiedad = new Propiedad();
 
      propiedad.setId(id);
      propiedad.setTitulo(titulo);
      propiedad.setCalle(calle);
      propiedad.setAlta(new Date());
      propiedad.setDireccion(direccion);
      propiedad.setLocalidad(localidad);
      propiedad.setProvincia(provincia);
      //recibe entidad por parametro y la guarda(persiste) en la base de datos.
      propiedadRepositorio.save(propiedad);
   }
//   metodo para listar los libros de la base de datos, no recibe parametros.
//   se rellena con lo que retorna el repositorio

   public List<Propiedad> listarPropiedades() {
      List<Propiedad> propiedades = new ArrayList();
//  FindAll(jpa) encuentra todo lo que se encuentre en libroRepositorio.
      propiedades = propiedadRepositorio.findAll();
      return propiedades;
   }

   public void modificarPropiedad(Long id, String titulo, String calle,Integer direccion,String localidad,String provincia) throws MiExcepcion {
       Propiedad  propiedad= propiedadRepositorio.findById(id).get();
//     si el id que se envia por parametro tiene errores o esta incorrecto o no existe
//     Se utiliza optional
//     Optional es un objeto contenedor que puede contener un valor nulo o no nulo
//     Si el valor esta presente devuelve true y retorna el valor con get.
      validar(id, titulo, calle,direccion, localidad, provincia);
      Optional<Propiedad> respuesta = propiedadRepositorio.findById(id);
//      Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
//      Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
//
//      Autor autor = new Autor();
//      Editorial editorial = new Editorial();
//      if (respuestaAutor.isPresent()) {
//         autor = respuestaAutor.get();
//      }
//      if (respuestaEditorial.isPresent()) {
//         editorial = respuestaEditorial.get();
//      }
      if (respuesta.isPresent()) {
         propiedad = respuesta.get();
         propiedad.setTitulo(titulo);
         propiedad.setCalle(calle);
         propiedad.setDireccion(direccion);
         propiedad.setLocalidad(localidad);
         propiedad.setProvincia(provincia);
//         propiedad.set usuario.id
         propiedadRepositorio.save(propiedad);
      }
   }
       public Propiedad getOne(Long id) {
    
        return propiedadRepositorio.getOne(id);
    }   
       public void eliminar(Long id){

       propiedadRepositorio.deleteById(id);
        
    }

//  establecer que si el metodo se ejecuta sin excepciones se realiza un commit a la base de datos y aplica cambios
//  si lanza excepcion y no es atrapada no aplica nada a la base de datos.
//  se le agrega despues del parametro que puede recibir excepciones.
   private void validar(Long id, String titulo, String calle,Integer direccion,String localidad,String provincia) throws MiExcepcion {
      if (id == null) {
         throw new MiExcepcion("El isbn no puede ser nulo");
      }
      if (titulo.isEmpty() || titulo == null) {
         throw new MiExcepcion("El titulo no puede ser nulo");
      }
      if (calle == null) {
         throw new MiExcepcion("La calle no puede ser nula");
      }
            
      if (localidad == null) {
         throw new MiExcepcion("La localidad no pueden ser nula");
      }
            
      if (provincia == null) {
         throw new MiExcepcion("La provincia no pueden ser nula");
      }

//      if (idAutor.isEmpty() || idAutor == null) {
//         throw new MiExcepcion("El idAutor no puede ser nulo");
//      }
//      if (idEditorial.isEmpty() || idEditorial == null) {
//         throw new MiExcepcion("El idEditorial no puede ser nulo");
      }
   }

