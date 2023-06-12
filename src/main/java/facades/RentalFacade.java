package facades;

import dtos.RenameMeDTO;
import dtos.RentalDto;
import entities.RenameMe;
import entities.Rental;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class RentalFacade {

    private static RentalFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private RentalFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RentalFacade getRentalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RentalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RentalDto create(RentalDto rd){
        Rental rental = new Rental(rd.getStartDate(), rd.getEndDate(), rd.getPriceAnnual(), rd.getDeposit(), rd.getContactPerson());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDto(rental);
    }

    public RentalDto getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Rental rental = em.find(Rental.class, id);

        return new RentalDto(rental);
    }

    public RentalDto edit(RentalDto rd) {
        EntityManager em = emf.createEntityManager();
        Rental rental = em.find(Rental.class, rd.getId());

        rental.setStartDate(rd.getStartDate());
        rental.setEndDate(rd.getEndDate());
        rental.setPriceAnnual(rd.getPriceAnnual());
        rental.setDeposit(rd.getDeposit());
        rental.setContactPerson(rd.getContactPerson());

        try {
            em.getTransaction().begin();
            em.merge(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDto(rental);
    }

    public RentalDto delete(long id) {
        EntityManager em = emf.createEntityManager();
        Rental rental = em.find(Rental.class, id);

        try {
            em.getTransaction().begin();
            em.remove(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDto(rental);
    }

    public List<RentalDto> getAllRentals(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r", Rental.class);
        List<Rental> rentals = query.getResultList();
        em.close();
        return RentalDto.getDtos(rentals);
    }

    public List<RentalDto> getRentalsByTenantId(long id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.tenant.id = :id", Rental.class);
        query.setParameter("id", id);
        List<Rental> rentals = query.getResultList();
        em.close();
        return RentalDto.getDtos(rentals);
    }

    public List<RentalDto> getRentalsByHouseId(long id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r WHERE r.house.id = :id", Rental.class);
        query.setParameter("id", id);
        List<Rental> rentals = query.getResultList();
        em.close();
        return RentalDto.getDtos(rentals);
    }


}
