package com.ejemplo1.biblioteca.repositorios;
import com.ejemplo1.biblioteca.Entidades.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long>{
   @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
   //@Param hace referencia a que ("titulo") es atributo del Libro  y que en String titulo guardaria el titulo que 
   //llega de la Query
   public Libro buscarPorTitulo(@Param("titulo") String titulo);

   @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
   public List<Libro> buscarPorAutor(@Param("nombre")String nombre);
}
