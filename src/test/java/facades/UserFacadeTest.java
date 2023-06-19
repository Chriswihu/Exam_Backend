package facades;

import dtos.UserDTO;
import entities.RenameMe;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);
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
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(new User("user", "test"));
            em.persist(new User("admin", "test"));
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
    public void testUserFacade() {
        List<UserDTO> uf = new ArrayList();
        uf.add(facade.getUserByUserName("user"));
        uf.add(facade.getUserByUserName("admin"));
        assertEquals(2, uf.size());
    }

    @Test
    public void createUserTest() {
        UserDTO user = facade.getUserByUserName("user");
        UserDTO admin = facade.getUserByUserName("admin");
        assertEquals(user.getUserName(), "user");
        assertEquals(admin.getUserName(), "admin");
    }

    @Test
    public void editUserTest() {
        UserDTO user = facade.getUserByUserName("user");
        UserDTO udto = new UserDTO(new User("NewUser", "NewTest"));
        facade.editUser(user.getUserName(), udto);
        assertEquals(udto.getUserName(), "NewUser");

    }


}
