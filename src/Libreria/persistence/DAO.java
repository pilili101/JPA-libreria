package Libreria.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO<T> {

    protected final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    protected EntityManager em = null;

    public void connect() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
    }

    public void disconnect() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public void persist(T object) {
        connect();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        disconnect();
    }

    public void edit(T object) {
        connect();
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        disconnect();
    }

    public void remove(T object) {
        connect();
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
        disconnect();
    }

    public Object findObjectById(Integer id) throws Exception{
        connect();
        Object object=null;
        try {
            object = em.find(Object.class, id);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            disconnect();
        }
        if (object != null) {
            return object;
        } else {
            System.out.println("OBJETO CON EL ID "+id+" NO ENCONTRADO");
            return object;
        }
    }

    public List<Object> list(String tabla) {
        List<Object> lista = em.createQuery("select t from tabla t").setParameter("tabla", tabla).getResultList();
        return lista;
    }

}
