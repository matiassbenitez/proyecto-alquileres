package com.ejemplo1.biblioteca.repositorios;
import com.ejemplo1.biblioteca.Entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;
//va a trabajar con <Usuario,String>
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {

   @Query("SELECT u FROM Usuario u WHERE u.email= :email")
   public Usuario buscarPorEmail(@Param("email")String email);

}
