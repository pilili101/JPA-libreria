
package Libreria.persistence;

import libreria.entities.Libro;

public final class LibroDAO extends DAO<Libro> {
    
    public void persistLibro(Libro libro){      
        super.persist(libro);
    }
    
    public void removeLibro(Libro libro){
        super.remove(libro);
    }
    
    public void updateLibro(Libro libro){
        super.edit(libro);
    }
    
}
