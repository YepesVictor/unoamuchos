/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Diurno
 */
@Entity
@Table(name = "Telefono")
public class Telefono implements Serializable {

    @Id
    @Column(name = "numero", length = 9)
    private int telefono;

    @Column(name = "nombreCompania")
    @Basic(optional = false)
    private String nombre;

    @Column(name = "telCompania", length = 9)
    private int tecC;

    @Column(name = "localidadCompania", length = 30)
    private String loc;

    @ManyToOne
    @JoinColumn(name = "id_persona", foreignKey = @ForeignKey(name = "tel_per_fk"))
   // @JoinColumn(name = "id_persona", referencedColumnName = "cod")
    private Persona p;

    /*
        OneToOne
        OneToMany
        ManyToOne
        ManyToMany
     */
    public Telefono(int telefono, Persona p) {
        this.telefono = telefono;
        this.p = p;
    }

    public Telefono(int telefono, String nombre, Persona p) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.p = p;
    }

    public Telefono(int telefono, String nombre, int tecC, String loc, Persona p) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.tecC = tecC;
        this.loc = loc;
        this.p = p;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTecC() {
        return tecC;
    }

    public void setTecC(int tecC) {
        this.tecC = tecC;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.telefono;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + this.tecC;
        hash = 61 * hash + Objects.hashCode(this.loc);
        hash = 61 * hash + Objects.hashCode(this.p);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefono other = (Telefono) obj;
        if (this.telefono != other.telefono) {
            return false;
        }
        if (this.tecC != other.tecC) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.loc, other.loc)) {
            return false;
        }
        return Objects.equals(this.p, other.p);
    }

}
