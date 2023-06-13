package rest;

import com.google.gson.Gson;
import dtos.UserDto;
import entities.RenameMe;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class UserResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static User user1, user2;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        user1 = new User("user", "test", "12345678", "student");
        user2 = new User("admin", "test", "12345678", "student");
//        Role role1 = new Role("user");
//        Role role2 = new Role("admin");
//        user1.addRole(role1);
//        user2.addRole(role2);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
//            em.persist(role1);
//            em.persist(role2);
            em.persist(user1);
            em.persist(user2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //Test to se if the server is up
    @Test
    public void testServerIsUp() {
        given().when().get("/user").then().statusCode(200);
    }

    //Test to get all users
    @Test
    public void testCount() throws Exception {

        given()
                .contentType("application/json")
                .get("/user/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body(equalTo("[2]"));
    }


//    @Test
//    public void testCreateUser() {
//        User user = new User("test", "test", "12345678", "student");
//
//
//        given()
//                .contentType("application/json")
//                .body(new Gson().toJson(user))
//                .when()
//                .post("/user/create")
//                .then()
//                .body("userName", equalTo("test"))
//                .body("password", equalTo("test"))
//                .body("phone", equalTo("12345678"))
//                .body("role", equalTo("student"))
//                .statusCode(200);
//
//    }


}
