package facades;

import dtos.AssignmentDTO;
import entities.Assignment;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class AssignmentFacadeTest {

    private static EntityManagerFactory emf;
    private static AssignmentFacade facade;

    public AssignmentFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssignmentFacade.getAssignmentFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        //Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            em.persist(new Assignment("TestFamilyName1", "TestDat1", "TestContact1"));
            em.persist(new Assignment("TestFamilyName2", "TestDate2", "TestContact2"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @AfterEach
    public void tearDown() {
        //Remove any data after each test was run
    }

    @Test
    public void testAssignmentFacade() {
        List<AssignmentDTO> af = new ArrayList<>();
        af = facade.getAllAssignments();
        assertEquals(2, af.size());
    }


    @Test
    public void createAssignmentTest() {
        Assignment a = new Assignment("TestFamilyName3", "TestDate3", "TestContact3");
        facade.create(new AssignmentDTO(a));
        List<AssignmentDTO> af = new ArrayList<>();
        af = facade.getAllAssignments();
        assertEquals(3, af.size());

    }

//    @Test
//    public void deleteAssignmentTest() {
//
//        AssignmentDTO a = new AssignmentDTO(new Assignment("TestFamilyNameToDelete", "TestDateToDelete", "TestContactToDelete"));
//        facade.create(a);
//        List<AssignmentDTO> af = new ArrayList<>();
//        af = facade.getAllAssignments();
//        assertEquals(3, af.size());
//        facade.deleteAssignmentById();
//        af = facade.getAllAssignments();
//        assertEquals(2, af.size());
//
//    }


}
