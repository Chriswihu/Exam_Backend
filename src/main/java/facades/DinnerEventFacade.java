package facades;

import dtos.AssignmentDTO;
import dtos.DinnerEventDTO;
import dtos.DinnerEventDTO;
import dtos.RenameMeDTO;
import entities.Assignment;
import entities.DinnerEvent;
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
public class DinnerEventFacade {

    private static DinnerEventFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DinnerEventFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DinnerEventFacade getDinnerEventFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DinnerEventFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static DinnerEventDTO createEvent(DinnerEventDTO dd) {
        DinnerEvent d = new DinnerEvent(dd.getTime(), dd.getLocation(), dd.getDish(), dd.getPrice());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DinnerEventDTO(d);
    }

    public List<DinnerEventDTO> getAllEvents() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<DinnerEvent> query = em.createQuery("SELECT d FROM DinnerEvent d", DinnerEvent.class);
        List<DinnerEvent> dList = query.getResultList();
        em.close();
        return DinnerEventDTO.getDinnerEventsDTOs(dList);
    }

    public DinnerEventDTO getEventById(long id){
        EntityManager em = emf.createEntityManager();
        try{
            DinnerEvent d = em.find(DinnerEvent.class, id);
            return new DinnerEventDTO(d);
        }finally {
            em.close();
        }
    }

    public DinnerEventDTO editEvent(Long id, DinnerEventDTO ddto){
        EntityManager em = emf.createEntityManager();
        DinnerEvent d = em.find(DinnerEvent.class, id);
        try{
            em.getTransaction().begin();
            d.setTime(ddto.getTime());
            d.setLocation(ddto.getLocation());
            d.setDish(ddto.getDish());
            d.setPrice(ddto.getPrice());
            em.merge(d);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new DinnerEventDTO(d);
    }

    public DinnerEventDTO deleteEvent(long id){
        EntityManager em = emf.createEntityManager();
        DinnerEvent d = em.find(DinnerEvent.class, id);
        try{
            em.getTransaction().begin();
            em.remove(d);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new DinnerEventDTO(d);
    }

}
