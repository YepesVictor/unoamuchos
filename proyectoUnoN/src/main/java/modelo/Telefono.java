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
@NamedQueries({
    @NamedQuery(name = "telByCompania", query = "select t from Telefono t where nombreCompania=:compania"),
    @NamedQuery(name = "telByPersonaLocalidad", query = "select t from Telefono t where id_persona=:id AND localidadCompania=:compania")
})
public class Telefono implements Serializable {

    @Id
    @Column(name = "numero", length = 9)
    private int telefono;

//    @Column(name = "nombreCompania")
//    @Basic(optional = false)
//    private String nombre;
//
//    @Column(name = "telCompania", length = 9)
//    private int tecC;
//
//    @Column(name = "localidadCompania", length = 30)
//    private String loc;
    @Embedded
//    @AttributeOverrides({
//        @AttributeOverride(name = "nombre", column = @Column(name = "nombreCompania")),
//        @AttributeOverride(name = "telefono", column = @Column(name = "telCompania")),
//        @AttributeOverride(name = "localidad", column = @Column(name = "localidadCompania"))
//    })
    private Compania c;

    //Tanto el name como la foreign key como el valor referenciado son de la Base de datos
    @ManyToOne//(cascade = CascadeType.ALL)
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

    public Telefono(int telefono, Compania c, Persona p) {
        this.telefono = telefono;
        this.c = c;
        this.p = p;
    }

    public Telefono() {
    }

    public Telefono(int telefono, Compania c) {
        this.telefono = telefono;
        this.c = c;
    }

//    public Telefono(int telefono, String nombre, Persona p) {
//        this.telefono = telefono;
//        this.nombre = nombre;
//        this.p = p;
//    }
//
//    public Telefono(int telefono, String nombre, int tecC, String loc, Persona p) {
//        this.telefono = telefono;
//        this.nombre = nombre;
//        this.tecC = tecC;
//        this.loc = loc;
//        this.p = p;
//    }
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public int getTecC() {
//        return tecC;
//    }
//
//    public void setTecC(int tecC) {
//        this.tecC = tecC;
//    }
//
//    public String getLoc() {
//        return loc;
//    }
//
//    public void setLoc(String loc) {
//        this.loc = loc;
//    }
    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }

    public Compania getC() {
        return c;
    }

    public void setC(Compania c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Telefono{" + "telefono=" + telefono + ", c=" + c + ", p=" + p + '}';
    }

}
