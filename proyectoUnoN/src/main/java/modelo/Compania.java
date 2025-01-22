/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.*;

/**
 *
 * @author victo
 */
@Embeddable
public class Compania {

    @Column(name = "nombreCompania", nullable = false, length = 30)
    private String nombre;
    @Column(name = "telCompania", length = 9)
    private int telefono;
    @Column(name = "localidadCompania", length = 30)
    private String localidad;

    public Compania() {
    }

    
    public Compania(String nombre) {
        this.nombre = nombre;
    }

    public Compania(String nombre, int telefono, String localidad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Compania{" + "nombre=" + nombre + ", telefono=" + telefono + ", localidad=" + localidad + '}';
    }

}
