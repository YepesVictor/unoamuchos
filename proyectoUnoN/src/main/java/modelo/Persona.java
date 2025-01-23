package modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ProfDiurno
 */
@Entity
@Embeddable
@Table(name = "Persona")
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod", length = 11)
    private int id;

    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;
    /* Para poner que un atributo NO puede ser nulo
    podemos poner o bien la anotación Basic con optional igual a false
    o bien la anotación Column con nullable igual a false
     */

    @Column(name = "apellidoPrimero", length = 30)
    private String apellido;

    @Column(name = "fNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    /* Siempre hay que indicar cuando sea date, time, datetime o timestamp
    la anotación Temporal */
 /*
    private String calle;
    private int numero;
    private int codigoPostal;
     */

    public Persona() {
    }

    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Persona(int id) {
        this.id = id;
    }

    
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public Persona(int id, String nombre, String apellido, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona(String nombre, String apellido, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Persona other = (Persona) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return Objects.equals(this.fechaNacimiento, other.fechaNacimiento);
    }

}
