
package com.equipoQ.alquilerQuinchos.Entidades;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
@Entity
public class Propietario extends Usuario {
@OneToMany
private List<Propiedad> propiedades;
}
