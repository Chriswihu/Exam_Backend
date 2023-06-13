package facades;

import dtos.RentalDto;
import entities.RenameMe;
import entities.Rental;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class RentalFacadeTest {

    private static EntityManagerFactory emf;
    private static RentalFacade facade;

    public RentalFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = RentalFacade.getRentalFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Rental.deleteAllRows").executeUpdate();
            em.persist(new Rental("StartDateTest", "EndDateTest", 420, 69, "ContactPersonTest"));
            em.persist(new Rental("StartDateTest2", "EndDateTest2", 421, 70, "ContactPersonTest2"));
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.getAllRentals().size(), "Expects two rows in the database");
    }

    @Test
    public void create() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Rental.deleteAllRows").executeUpdate();
            Rental r1 = new Rental("StartDateTest3", "EndDateTest3", 422, 71, "ContactPersonTest3");
            em.persist(r1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Test
    public void getAllRentals() throws Exception {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Rental.deleteAllRows").executeUpdate();

            Rental r1 = new Rental("StartDateTest4", "EndDateTest4", 423, 72, "ContactPersonTest4");
            Rental r2 = new Rental("StartDateTest5", "EndDateTest5", 424, 73, "ContactPersonTest5");
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        assertEquals(2, facade.getAllRentals().size(), "Expects two rows in the database");

    }

//    @Test
//    public void getRentalById() throws Exception {
//        EntityManager em = emf.createEntityManager();
//
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Rental.deleteAllRows").executeUpdate();
//
//            Rental r1 = new Rental("StartDateTest1", "EndDateTest6", 425, 74, "ContactPersonTest6");
//
//            em.persist(r1);
//
//            em.getTransaction().commit();
//
//        } finally {
//
//            em.close();
//        }
//
//        assertEquals(1L, facade.getById(1L).getId(), "Expects 1L");
//        assertEquals("StartDateTest1", facade.getById(1L).getStartDate(), "Expects StartDateTest1");
//        assertEquals("EndDateTest1", facade.getById(1L).getEndDate(), "Expects EndDateTest1");
//        assertEquals(425, facade.getById(1L).getPriceAnnual(), "Expects 425");
//        assertEquals(74, facade.getById(1L).getContactPerson(), "Expects 74");
//        assertEquals("ContactPersonTest1", facade.getById(1L).getContactPerson(), "Expects ContactPersonTest1");
//
//
//
//    }
//
//    @Test
//    public void edit() throws Exception{
//        EntityManager em = emf.createEntityManager();
//
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Rental.deleteAllRows").executeUpdate();
//
//            Rental r1 = new Rental("StartDateTest8", "EndDateTest8", 427, 76, "ContactPersonTest8");
//            Rental r2 = new Rental("StartDateTest9", "EndDateTest9", 428, 77, "ContactPersonTest9");
//            em.persist(r1);
//            em.persist(r2);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//
//        Rental r1 = new Rental("StartDateTest10", "EndDateTest10", 429, 78, "ContactPersonTest10");
//        RentalDto rdto = new RentalDto(r1);
//        facade.edit(rdto);
//
//        assertEquals("StartDateTest10", facade.getById(1L).getStartDate(), "Expects StartDateTest10");
//        assertEquals("EndDateTest10", facade.getById(1L).getEndDate(), "Expects EndDateTest10");
//        assertEquals(429, facade.getById(1L).getPriceAnnual(), "Expects 429");
//        assertEquals(78, facade.getById(1L).getContactPerson(), "Expects 78");
//        assertEquals("ContactPersonTest10", facade.getById(1L).getContactPerson(), "Expects ContactPersonTest10");
//    }



}
