/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

/**
 *
 * @author Diurno
 */
public class TelefonoRepository {

    private EntityManager em;

    public TelefonoRepository(EntityManager em) {
        this.em = em;
    }

    public void insert(Telefono t) {
        em.getTransaction().begin();
        //em.find(Persona.class, t.getP().getId());
        em.persist(t);
        em.getTransaction().commit();
    }

    public Telefono selectById(int telefono) {
        Telefono t = em.find(Telefono.class, telefono);
        return t;
    }

    public void update(int id, Telefono telfNuevo) {
        Telefono t = selectById(id);
        if (t != null) {
            try {
                t.setC(telfNuevo.getC());
                t.setP(telfNuevo.getP());
                em.getTransaction().begin();
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
    }

    public void delete(int id) {
        Telefono t = selectById(id);
        if (t != null) {
            try {
                em.getTransaction().begin();
                em.remove(t);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
    }

    /*
        1. Devolver todos los telefonos
        2. Devolver todos los telefonos de una persona
        3. Devolver los numero de Movistar
        4. NameQuery:Devolver los telefono de una localidad determinada
        5. NameQuery: Devolver los telefono de una persona y de una localidad
     */
    public List<Telefono> todoTelefono() {
        List<Telefono> telefonos = new ArrayList<>();
        telefonos = em.createQuery("select t from Telefono t").getResultList();
        return telefonos;
    }

    public List<Telefono> telefonoByPersona(int idPersona) {
        List<Telefono> telefonos = new ArrayList<>();
        telefonos = em.createQuery("select t from Telefono t where id_persona=:id").setParameter("id", idPersona).getResultList();
        return telefonos;
    }

    public List<Telefono> telefonoByCompania(String nombreCompania) {
        List<Telefono> telefonos = new ArrayList<>();
        telefonos = em.createQuery("select t from Telefono t where nombreCompania=:compania").setParameter("compania", nombreCompania).getResultList();
        return telefonos;
    }

    public List<Telefono> telefonoByCompaniaNamedQ(String nombreCompania) {
        List<Telefono> telefonos = new ArrayList<>();
        telefonos = em.createNamedQuery("telByCompania", Telefono.class).setParameter("compania", nombreCompania).getResultList();
        return telefonos;
    }

    public List<Telefono> telByPerComNamedQ(int id, String localidad) {
        List<Telefono> telefonos = em.createNamedQuery("telByPersonaLocalidad", Telefono.class).setParameter("id", id).setParameter("compania", localidad).getResultList();
        return telefonos;
    }

    public void cerrar(EntityManager e) {
        e.close();
    }
}
