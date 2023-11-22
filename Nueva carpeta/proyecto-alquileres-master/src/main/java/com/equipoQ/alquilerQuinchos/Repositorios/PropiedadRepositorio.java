package com.equipoQ.alquilerQuinchos.Repositorios;
import com.equipoQ.alquilerQuinchos.Entidades.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
@Repository
public interface PropiedadRepositorio extends JpaRepository<Propiedad, Long>{
   @Query("SELECT l FROM Propiedad l WHERE l.titulo = :titulo")
   //@Param hace referencia a que ("titulo") es atributo del Libro  y que en String titulo guardaria el titulo que 
   //llega de la Query
   public Propiedad buscarPorTitulo(@Param("titulo") String titulo);

//   @Query("SELECT l FROM Propiedad l WHERE l.autor.nombre = :nombre")
//   public List<Propiedad> buscarPorAutor(@Param("nombre")String nombre);
}
