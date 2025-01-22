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
public class PersonaRepository {

    private EntityManager em;

    public PersonaRepository(EntityManager em) {
        this.em = em;
    }

    public void insert(Persona p) {
        //Creo una transacción
        em.getTransaction().begin();

        //Mapear un objeto en la BD
        em.persist(p);

        //Guardar
        em.getTransaction().commit();
    }
    
    public void cerrar(EntityManager e){
        e.close();
    }
}
