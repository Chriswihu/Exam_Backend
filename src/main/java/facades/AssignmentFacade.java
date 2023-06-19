package facades;

import dtos.AssignmentDTO;
import entities.Assignment;

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
    public static AssignmentFacade getAssignmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssignmentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AssignmentDTO create(AssignmentDTO adto){
        Assignment a = new Assignment(adto.getFamilyName(), adto.getDate(), adto.getContact());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new AssignmentDTO(a);
    }

    public AssignmentDTO assignToEvent(AssignmentDTO adto){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Assignment a = em.find(Assignment.class, adto.getId());

            em.getTransaction().commit();
            return new AssignmentDTO(a);
        }finally {
            em.close();
        }
    }

    public List<AssignmentDTO> getAllAssignments(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Assignment> query = em.createQuery("SELECT a FROM Assignment a", Assignment.class);
            List<Assignment> assignments = query.getResultList();
            return AssignmentDTO.getDTOs(assignments);
        }finally {
            em.close();
        }
    }

    public void deleteAssignmentById(long id){
        EntityManager em = emf.createEntityManager();

        try{
            Assignment a = em.find(Assignment.class, id);
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
            new AssignmentDTO(a);
        }finally {
            em.close();
        }
    }

//    public List<AssignmentDTO> allAssignments() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            TypedQuery<Assignment> query = em.createQuery("SELECT a FROM Assignment a", Assignment.class);
//            List<Assignment> assignments = query.getResultList();
//            return
//        } finally {
//            em.close();
//        }
//
//    }
}
