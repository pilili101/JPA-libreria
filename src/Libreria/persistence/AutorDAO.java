package Libreria.persistence;

import libreria.entities.Autor;

public final class AutorDAO extends DAO<Autor> {

    public void persistAutor(Autor autor) {
        persist(autor);
    }

    public void updateAutor(Autor autor) {
        edit(autor);
    }

    public void removeAutorById(Integer id) throws Exception {
        if (checkAutorById(id)) {
            remove((Autor) findObjectById(id));
        } else{
            System.out.println("El id ingresado: "+id+" es inexistente");   
        } 
    }

    public Autor findAutorByNombre(String nombre) {
        connect();
        Autor autor = (Autor) em.createQuery("select a from autor a where a.nombre = nombre")
                .setParameter("nombre", nombre).getSingleResult();
        disconnect();
        return autor;
    }

    public boolean checkAutorByNombre(String nombre) {
        connect();
        String consult = "select count(a) from autor a where a.nombre = nombre";
        Long count = em.createQuery(consult, Long.class).setParameter("nombre", nombre).getSingleResult();
        disconnect();
        return count > 0;
    }

    public boolean checkAutorById(Integer id) {
        connect();
        String consult = "select count(a) from autor a where a.id = id";
        Long count = em.createQuery(consult, Long.class).setParameter("id", id).getSingleResult();
        disconnect();
        return count > 0;
    }
}
