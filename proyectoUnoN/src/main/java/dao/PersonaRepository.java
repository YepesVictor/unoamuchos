/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelo.*;

/**
 *
 * @author Diurno
 */
public class PersonaRepository {

    private EntityManager em;

    public PersonaRepository(EntityManager em) {
        this.em = em;
    }

    public void insert(Persona p) {
        if (p != null) {
            //Creo una transacción
            em.getTransaction().begin();
            //Mapear un objeto en la BD
            em.persist(p);
            //Guardar
            em.getTransaction().commit();
        }
    }

    //Devolver una Persona con un Id
    public Persona selectById(int id) {
        Persona p = em.find(Persona.class, id);
        if (p != null) {
            System.out.println("Persona existe");
        } else {
            System.out.println("Persona no existe");
        }
        return p;
    }

    //Devolver todas las personas
    public List<Persona> selectAll() {
        List<Persona> personas = new ArrayList<>();
        Query query = em.createQuery("from Persona");
        personas = query.getResultList();

        return personas;
    }

    //Cambios:
    /*
        En el from ponemos el nombre de la clase de java 
        Debemos darle un nombre a la clase porque lo trata como un objeto
        En las condiciones tenemos que poner : antes de la variable
        Tenemos que resolver los parametros posteriormente
     */
    public List<Persona> personaByName(String nombre) {
        List<Persona> personas = new ArrayList<>();
        Query query = em.createQuery("Select p from Persona p Where p.nombre=:nom").setParameter("nom", nombre);
        personas = query.getResultList();
        return personas;
    }

    //Consultas predefinidas en la propia clase 
    public List<Persona> selectByApellido(String apellido) {
        List<Persona> personas = new ArrayList<>();
        personas = em.createNamedQuery("selectByApellido", Persona.class).setParameter("ape", apellido).getResultList();

        return personas;
    }

    public List<Persona> todo() {
        return em.createNamedQuery("from Persona", Persona.class).getResultList();
    }

    //Actualizacion
    public void update(int id, Persona personaNueva) {
        Persona pActualizar = selectById(id);
        if (pActualizar != null) {
            try {
                pActualizar.setNombre(personaNueva.getNombre());
                pActualizar.setApellido(personaNueva.getApellido());
                pActualizar.setFechaNacimiento(personaNueva.getFechaNacimiento());
                em.getTransaction().begin();
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
    }

    //Borrado
    public void delete(int id) {
        Persona p = selectById(id);
        if (p != null) {
            try {
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
    }

    //Consultas nativas
    public void cerrar(EntityManager e) {
        e.close();
    }
}
