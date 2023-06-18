package facades;

import dtos.AssignmentDTO;
import dtos.RenameMeDTO;
import entities.Assignment;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class AssignmentFacade {

    private static AssignmentFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AssignmentFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AssignmentFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssignmentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AssignmentDTO assignToEvent(AssignmentDTO adto){
        EntityManager em = emf.createEntityManager();
        Assignment a = new Assignment(adto.getFamilyName(), adto.getDate(), adto.getContact());
        try{
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new AssignmentDTO(a);
    }

    public AssignmentDTO getAssignmentById(long id){
        EntityManager em = emf.createEntityManager();
        try{
            Assignment a = em.find(Assignment.class, id);
            return new AssignmentDTO(a);
        }finally {
            em.close();
        }
    }

    public AssignmentDTO deleteAssignmentById(long id){
        EntityManager em = emf.createEntityManager();

        try{
            Assignment a = em.find(Assignment.class, id);
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
            return new AssignmentDTO(a);
        }finally {
            em.close();
        }
    }

}
