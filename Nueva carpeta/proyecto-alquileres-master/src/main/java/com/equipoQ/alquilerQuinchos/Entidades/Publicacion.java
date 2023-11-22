package com.equipoQ.alquilerQuinchos.Entidades;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
@Entity
public class Publicacion {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   private String titulo;
   private String descripcion;
   private Integer precio;
   private String categoria;
//   imagenes
//   fecha
   @OneToOne
   private Propiedad propiedad;
   @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
   private List<Comentario> comentarios;
   @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
   private List<Imagen> imagenes;
//CascadeType.ALL se utiliza para indicar que las operaciones que se realizan en una entidad deben propagarse a las entidades relacionadas. Por ejemplo, si se elimina un Usuario,
//también se eliminarán todas sus Propiedades, Publicaciones, Comentarios y Mensajes asociados.

   public Publicacion() {
   }

   public Publicacion(String id, String titulo, String descripcion, Integer precio, String categoria, Propiedad propiedad, List<Comentario> comentarios, List<Imagen> imagenes) {
      this.id = id;
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.precio = precio;
      this.categoria = categoria;
      this.propiedad = propiedad;
      this.comentarios = comentarios;
      this.imagenes = imagenes;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public Integer getPrecio() {
      return precio;
   }

   public void setPrecio(Integer precio) {
      this.precio = precio;
   }

   public String getCategoria() {
      return categoria;
   }

   public void setCategoria(String categoria) {
      this.categoria = categoria;
   }

   public Propiedad getPropiedad() {
      return propiedad;
   }

   public void setPropiedad(Propiedad propiedad) {
      this.propiedad = propiedad;
   }

   public List<Comentario> getComentarios() {
      return comentarios;
   }

   public void setComentarios(List<Comentario> comentarios) {
      this.comentarios = comentarios;
   }

   public List<Imagen> getImagenes() {
      return imagenes;
   }

   public void setImagenes(List<Imagen> imagenes) {
      this.imagenes = imagenes;
   }
}