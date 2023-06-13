package facades;

import dtos.HouseDto;
import dtos.RenameMeDTO;
import entities.House;
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
public class HouseFacade {

    private static HouseFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HouseFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HouseFacade getHouseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HouseFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public HouseDto create(HouseDto hd) {
        House house = new House(hd.getAddress(), hd.getCity(), hd.getNumberOfRooms());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hd);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HouseDto(house);
    }

    public HouseDto getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        House house = em.find(House.class, id);

        return new HouseDto(house);

    }

    public HouseDto editHouse(HouseDto hd) {
        EntityManager em = emf.createEntityManager();
        House house = em.find(House.class, hd.getId());
        house.setAddress(hd.getAddress());
        house.setCity(hd.getCity());
        house.setNumberOfRooms(hd.getNumberOfRooms());
        return new HouseDto(house);
    }

    public List<HouseDto> getAll() {
        EntityManager em = emf.createEntityManager();
        List<House> houses;
        try {
            TypedQuery<House> query = em.createQuery("SELECT h FROM House h", House.class);
            houses = query.getResultList();
        } finally {
            em.close();
        }
        return HouseDto.getDtos(houses);
    }

    public long getHouseCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long houseCount = (long) em.createQuery("SELECT COUNT(h) FROM House h").getSingleResult();
            return houseCount;
        } finally {
            em.close();
        }

    }

}