/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Persona;
import modelo.Telefono;
import vista.Vista;

/**
 *
 * @author Diurno
 */
public class Controlador {

    private Vista v;
    private PersonaRepository pr;
    private EntityManager em;
    private EntityManager em2;
    private TelefonoRepository tr;

    public Controlador(Vista v) {
        this.v = v;
        EntityManagerFactory entity = Persistence.createEntityManagerFactory("UnidadDePersistencia");
        this.em = entity.createEntityManager();
        this.em2 = entity.createEntityManager();

        this.pr = new PersonaRepository(em);
        this.tr = new TelefonoRepository(em2);
//        insertarPersona();
        insertarTelefono();
    }

    public void insertarPersona() {
        Persona p = new Persona("Victor");
        pr.insert(p);
    }

    public void insertarTelefono() {
        Persona p = new Persona("Pepe");
        Telefono t = new Telefono(123456789, "Movistar", p);

    }
}
