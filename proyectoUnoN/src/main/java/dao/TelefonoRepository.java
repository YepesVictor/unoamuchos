/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
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

    public void cerrar(EntityManager e) {
        e.close();
    }
}
