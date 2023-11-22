/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo1.biblioteca.Entidades;
import javax.persistence.*;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
@Entity
public class Imagen {
   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   private String mime;
   private String nombre;
//   Con las siguientes anotaciones decimos que puede ocupar mas espacio y su tipo de carga va a ser perezosa
//   que solo se carga si se hace un get
   @Lob @Basic(fetch = FetchType.LAZY)
   private byte[] contenido;

   public Imagen(String id, String mime, String nombre, byte[] contenido) {
      this.id = id;
      this.mime = mime;
      this.nombre = nombre;
      this.contenido = contenido;
   }

   public Imagen() {
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getMime() {
      return mime;
   }

   public void setMime(String mime) {
      this.mime = mime;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public byte[] getContenido() {
      return contenido;
   }

   public void setContenido(byte[] contenido) {
      this.contenido = contenido;
   }
   

}
