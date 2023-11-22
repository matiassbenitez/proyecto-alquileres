package com.ejemplo1.biblioteca.Entidades;
import com.ejemplo1.biblioteca.enumeraciones.*;
import javax.persistence.*;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
//import lombok.Data; crea el geter and seter automaticamente
//@Data
@Entity
public class Usuario {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   private String nombre;
   private String email;
   private String passoword;
//   Para agregar el atributo enum hay que utilizar @Enumerated y el tipo de dato. 
   @Enumerated(EnumType.STRING)
   private Rol rol;
   
   @OneToOne
   private Imagen imagen;

   public Usuario() {
   }

   public Usuario(String id, String nombre, String email, String passoword, Rol rol) {
      this.id = id;
      this.nombre = nombre;
      this.email = email;
      this.passoword = passoword;
      this.rol = rol;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassoword() {
      return passoword;
   }

   public void setPassoword(String passoword) {
      this.passoword = passoword;
   }

   public Rol getRol() {
      return rol;
   }

   public void setRol(Rol rol) {
      this.rol = rol;
   }

   public Usuario(Imagen imagen) {
      this.imagen = imagen;
   }

   public Imagen getImagen() {
      return imagen;
   }

   public void setImagen(Imagen imagen) {
      this.imagen = imagen;
   }
   
}