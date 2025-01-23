/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import modelo.Compania;
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
//        insertarPersonaTelefono();
//        insertarTelefono();
//        insertarPersona();
//        personaTodo();
//        personaByName();
        selectByApellido();
    }

    public void insertarPersona() {
        Persona p = new Persona("Victor");
        pr.insert(p);

    }

    public void insertarPersonaTelefono() {
        Compania c = new Compania("Movista", 123456789, "Logroño");
        Telefono t1 = new Telefono(123456789, c);
        Telefono t2 = new Telefono(987654321, c);
        Telefono t3 = new Telefono(456789123, c);
        Collection<Telefono> telefonos = new ArrayList<Telefono>();
        telefonos.add(t1);
        telefonos.add(t2);
        telefonos.add(t3);
        Persona p = new Persona("Victor", telefonos);
        pr.insert(p);
    }

    public void insertarTelefono() {
        Persona p = pr.selectById(1);
        Compania c = new Compania("Movistar", 123456789, "Lorgoño");
        Telefono t = new Telefono(112346789, c, p);
        tr.insert(t);
        cerrar2();
    }

    public void personaTodo() {
        ArrayList<Persona> p = (ArrayList<Persona>) pr.selectAll();
        for (Persona persona : p) {
            v.mostrar(persona.toString());
        }
    }

    public void personaByName() {
        ArrayList<Persona> p = (ArrayList<Persona>) pr.personaByName("Victor");
        for (Persona persona : p) {
            v.mostrar(persona.toString());
        }
    }

    public void selectByApellido() {
        ArrayList<Persona> p = (ArrayList<Persona>) pr.selectByApellido("null");
        for (Persona persona : p) {
            v.mostrar(persona.toString());
        }
    }

    public void cerrar() {
        em.close();
    }

    public void cerrar2() {
        em2.close();
    }
}
