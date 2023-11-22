package com.ejemplo1.biblioteca.repositorios;
import com.ejemplo1.biblioteca.Entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen,String> {
   
}
