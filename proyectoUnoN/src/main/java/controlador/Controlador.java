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
import java.util.List;
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
//        selectByApellido();
//        updatePersona();
//deletePersona();
//        if (tr.selectById(111111111) != null) {
//            v.mostrar(tr.selectById(111111111).toString());
//        } else {
//            v.mostrar("Es nulo");
//        }

//updateTelefono();
//todoTelefono();
//        telefonoByPersona();
//        telefonoByCompania();
//        telefonoByCompaniaNamedQ();
        telByPerComNamedQ();

    }

    public void insertarPersona() {
        Persona p = new Persona("Victor");
        pr.insert(p);

    }

    public void insertarPersonaTelefono() {
        Compania c = new Compania("DIGI", 123456789, "Logroño");
        Telefono t1 = new Telefono(123456489, c);
        Telefono t2 = new Telefono(987654521, c);
        Telefono t3 = new Telefono(456789623, c);
        Collection<Telefono> telefonos = new ArrayList<Telefono>();
        telefonos.add(t1);
        telefonos.add(t2);
        telefonos.add(t3);
        Persona p = new Persona("Juan", telefonos);
        pr.insert(p);
    }

    public void deletePersona() {
        pr.delete(1);
    }

    public void updatePersona() {
        Persona p = new Persona("Pedro");
        pr.update(1, p);
    }

    public void insertarTelefono() {
        Persona p = pr.selectById(3);
        Compania c = new Compania("Movistar", 123456789, "Lorgoño");
        Telefono t = new Telefono(132346789, c, p);
        tr.insert(t);
        cerrar2();
    }

    public void updateTelefono() {
        Compania c = new Compania("DIGI");
        Telefono t = new Telefono(604828168, c);
        tr.update(132346789, t);
    }

    public void todoTelefono() {
        List<Telefono> t = tr.todoTelefono();
        for (Telefono telefono : t) {
            v.mostrar(telefono.toString());
        }
    }

    public void telefonoByPersona() {
        List<Telefono> t = tr.telefonoByPersona(3);
        for (Telefono telefono : t) {
            v.mostrar(telefono.toString());
        }
    }

    public void telefonoByCompania() {
        List<Telefono> t = tr.telefonoByCompania("Movistar");
        v.mostrar("-----------------------");
        for (Telefono telefono : t) {
            v.mostrar(telefono.toString());
        }
        v.mostrar("-----------------------");

    }

    public void telefonoByCompaniaNamedQ() {
        List<Telefono> t = tr.telefonoByCompaniaNamedQ("DIGI");
        v.mostrar("-----------------------");
        for (Telefono telefono : t) {
            v.mostrar(telefono.toString());
        }
        v.mostrar("-----------------------");
    }

    public void telByPerComNamedQ() {
        List<Telefono> t = tr.telByPerComNamedQ(5, "Madrid");
        v.mostrar("-----------------------");
        for (Telefono telefono : t) {
            v.mostrar(telefono.toString());
        }
        v.mostrar("-----------------------");
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
