package com.equipoQ.alquilerQuinchos.Entidades;
import java.io.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;
@Entity
public class Propiedad implements Serializable {
@Id   
private long id;
private String titulo;
private String calle;
private Integer direccion;
private String localidad;
private String provincia;
@Temporal(TemporalType.DATE)
private Date alta;
@ManyToOne
//@JoinColumn(name = "propietario_id")
private Propietario propietario;
@OneToOne(mappedBy = "propiedad", cascade = CascadeType.ALL)
private Publicacion publicacion;

   public Propiedad() {
   }

   public Propiedad(long id, String titulo, String calle, Integer direccion, String localidad, String provincia, Date alta, Propietario propietario, Publicacion publicacion) {
      this.id = id;
      this.titulo = titulo;
      this.calle = calle;
      this.direccion = direccion;
      this.localidad = localidad;
      this.provincia = provincia;
      this.alta = alta;
      this.propietario = propietario;
      this.publicacion = publicacion;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getCalle() {
      return calle;
   }

   public void setCalle(String calle) {
      this.calle = calle;
   }

   public Integer getDireccion() {
      return direccion;
   }

   public void setDireccion(Integer direccion) {
      this.direccion = direccion;
   }

   public String getLocalidad() {
      return localidad;
   }

   public void setLocalidad(String localidad) {
      this.localidad = localidad;
   }

   public String getProvincia() {
      return provincia;
   }

   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }

   public Date getAlta() {
      return alta;
   }

   public void setAlta(Date alta) {
      this.alta = alta;
   }

   public Propietario getPropietario() {
      return propietario;
   }

   public void setPropietario(Propietario propietario) {
      this.propietario = propietario;
   }

   public Publicacion getPublicacion() {
      return publicacion;
   }

   public void setPublicacion(Publicacion publicacion) {
      this.publicacion = publicacion;
   }

}