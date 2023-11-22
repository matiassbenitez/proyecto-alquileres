package com.equipoQ.alquilerQuinchos.Entidades;
import com.equipoQ.alquilerQuinchos.Enumeraciones.Rol;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
import lombok.*;
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
   private String password;   
   @Enumerated(EnumType.STRING)
   private Rol rol;
   
   @OneToOne
   private Imagen imagen;

   @OneToMany
   private List<Propiedad> propiedades;

   public Usuario() {
       this.propiedades = new ArrayList<>();
   }

   public Usuario(String id, String nombre, String email, String password, Rol rol, Imagen imagen, List<Propiedad> propiedades) {
       this.id = id;
       this.nombre = nombre;
       this.email = email;
       this.password = password;
       this.rol = rol;
       this.imagen = imagen;
       this.propiedades = propiedades;
   }

   public void addPropiedad(Propiedad propiedad) {
       this.propiedades.add(propiedad);
       if (!this.propiedades.isEmpty()) {
           this.rol = Rol.PROPIETARIO;
       }
   }

   public String getId() {
       return id;
   }

   public String getNombre() {
       return nombre;
   }

   public String getEmail() {
       return email;
   }

   public String getPassword() {
       return password;
   }

   public Rol getRol() {
       return rol;
   }

   public Imagen getImagen() {
       return imagen;
   }

   public List<Propiedad> getPropiedades() {
       return propiedades;
   }

   public void setId(String id) {
       this.id = id;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }

   public void setEmail(String email) {
       this.email = email;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public void setRol(Rol rol) {
       this.rol = rol;
   }

   public void setImagen(Imagen imagen) {
       this.imagen = imagen;
   }

   public void setPropiedades(List<Propiedad> propiedades) {
       this.propiedades = propiedades;
   }
}