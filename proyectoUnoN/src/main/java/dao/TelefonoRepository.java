/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    public void cerrar(EntityManager e) {
        e.close();
    }

    /*
    ESTO FUE LO QUE HICIMOS HOY
     */
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
    ACTIVIDADES QUE MANDO
        1. Devolver todos los telefonos
        2. Devolver todos los telefonos de una persona
        3. Devolver los numero de Movistar
        4. NameQuery:Devolver los telefono de una localidad determinada
        5. NameQuery: Devolver los telefono de una persona y de una localidad
     */
    //CONSULTA 1
    public List<Telefono> todoTelefono() {
        List<Telefono> telefonos = em.createQuery("select t from Telefono t").getResultList();
        return telefonos;
    }

    //CONSULTA 2
    public List<Telefono> telefonoByPersona(int idPersona) {
        List<Telefono> telefonos = em.createQuery("select t from Telefono t where t.p.id=:id_per").setParameter("id_per", idPersona).getResultList();
        return telefonos;
    }

    //CONSULTA 3
    public List<Telefono> telefonoByCompania(String nombreCompania) {
        List<Telefono> telefonos = em.createQuery("select t from Telefono t where t.c.nombre=:compania").setParameter("compania", nombreCompania).getResultList();
        return telefonos;
    }

    public List<Integer> telefonoNumByCompania(String nombreCom) {
        List<Integer> telefonos = em.createQuery("select t.telefono from Telefono t where t.c.nombre=:compania").setParameter("compania", nombreCom).getResultList();
        return telefonos;
    }

    //CONSULTA 4
    public List<Telefono> telefonoByLocNamedQ(String localidad) {
        List<Telefono> telefonos = em.createNamedQuery("telByLoc", Telefono.class).setParameter("loc", localidad).getResultList();
        return telefonos;
    }

    //CONSULTA 5
    public List<Telefono> telByPerComNamedQ(int id, String localidad) {
        List<Telefono> telefonos = em.createNamedQuery("telByPersonaLocalidad", Telefono.class).setParameter("id_per", id).setParameter("com_loc", localidad).getResultList();
        return telefonos;
    }

    //Devolver telefono de unas personas Pepe, Paco, Pedro
    public List<Telefono> getTelefonoNomPer() {
        List<Telefono> telefonos = em.createQuery("select t from Telefono t where t.p.nombre IN ('Pepe','Paco','Pedro')").getResultList();
        return telefonos;
    }

    //CONSULTA CON CRITERIA 
    /*
    select persona
    from Telefono
    group by persona
    having count(*)=(select count(*)
                    from telefono
                    group by persona
                    order by count(*) desc
                    limit 1)
     */
    public List<Telefono> getTelefonosTop() {
        List<Telefono> telefonos = new ArrayList<>();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Telefono> criteria = builder.createQuery(Telefono.class);
        CriteriaQuery<Telefono> criteria2 = builder.createQuery(Telefono.class);
        //Consulta 1
        Root<Telefono> root = criteria.from(Telefono.class);
        criteria.select(root.get("p.id"));
        criteria.groupBy(root.get("p.id"));

//        criteria.subquery(Telefono.class);
        //Subconsulta
        Root<Telefono> root2 = criteria2.from(Telefono.class);
        criteria2.select(root2);
        criteria2.groupBy(root2.get("p.id"));
//        criteria2.orderBy(builder.count(root2));
        //Coger el limit
        //Para coger primer elemento de una lista de criteria, actua como limit
//        criteria.getOrderList().get(0);
        criteria.having(builder.equal(builder.count(root), builder.count(root2)));

        telefonos = em.createQuery(criteria).getResultList();
        return telefonos;
    }
}
