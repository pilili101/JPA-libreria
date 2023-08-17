package libreria.service;

import Libreria.persistence.AutorDAO;
import java.util.List;
import java.util.Scanner;
import libreria.entities.Autor;

public class AutorService {
    
    AutorDAO dao = new AutorDAO();
    
    public void menuAutor() {
        System.out.println("Seleccione la opcion deseada: ");
        System.out.println("1) Guardar nuevo autor");
        System.out.println("2) Modificar autor existente");
        System.out.println("3) Eliminar autor pot nombre");
        System.out.println("4) Eliminar autor por id");
        System.out.println("5) Listar autores");
    }
    
    public void createAutor(Scanner sc) throws Exception {
        try {
            Autor a = new Autor();
            System.out.println("Ingrese el nombre del autor");
            a.setNombre(sc.next());
            if (dao.checkAutorByNombre(a.getNombre()) & a.getAlta()) {
                System.out.println("El autor ya existe en la base de datos");
            } else if (dao.checkAutorByNombre(a.getNombre()) & !a.getAlta()) {
                System.out.println("El autor existe en la base de datos, estaba dado de baja en el sistema");
                a = dao.findAutorByNombre(a.getNombre());
                a.setAlta(true);
                dao.updateAutor(a);
                System.out.println("Autor actualizado en la base de datos: ACTIVO");
            } else if (!dao.checkAutorByNombre(a.getNombre())) {
                a.setAlta(true);
                dao.persistAutor(a);
                System.out.println("Nuevo autor registrado!");
            }
        } catch (Exception e) {
            throw new Exception("Error al registrar autor");
        }
    }
    
    public void updateAutor(Scanner sc) {
        Autor a = new Autor();
        System.out.println("ingrese el id del nombre a editar: ");
        a.setNombre(sc.next());
        if (dao.checkAutorByNombre(a.getNombre())) {
            System.out.println("El autor ya existe en la base de datos");
        } else {
            a= dao.findAutorByNombre(a.getNombre());
            System.out.println("Ingrese el nuevo nombre: ");
            a.setNombre(sc.next());
            System.out.println("Ingrese el estado del autor (v/f)");
            String aux = "";
            do {
                aux = sc.next();
            } while (!aux.equalsIgnoreCase("v") || !aux.equalsIgnoreCase("f"));
            if (aux.equalsIgnoreCase("f")) {
                a.setAlta(false);
            }else{
                a.setAlta(true);
            }
        }
        
    }
    
    private void listAutores() {
        System.out.println("-------LISTA DE AUTORES-----");
        List autores = dao.Autores();
        for (Object autor : autores) {
            autor.toString();
        }
    }
    
    public void removeAutor(Scanner sc) {
        System.out.println("Ingrese el id del autor a eliminar: ");
        Integer id = sc.nextInt();
        dao.removeAutorId(id);
    }
    
}
