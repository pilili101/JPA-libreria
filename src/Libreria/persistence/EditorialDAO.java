
package Libreria.persistence;

import libreria.entities.Editorial;

public final class EditorialDAO extends DAO<Editorial> {
    
    public void persistEditorial(Editorial editorial){
        super.persist(editorial);
    }
    
    public void updateEditorial(Editorial editorial){
        super.edit(editorial);
    }
    
    public void removeEditorial(Editorial editorial){
        super.remove(editorial);
    }
    
}
