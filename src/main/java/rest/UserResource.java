package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDto;
import entities.User;

import facades.UserFacade;
import utils.EMF_Creator;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


//Todo Remove or change relevant parts before ACTUAL use
@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        return GSON.toJson(userFacade.getAll());
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public List<User> allInUser() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("select u from User u", entities.User.class);
            List<User> users = query.getResultList();
            System.out.println(users);
            return users;
        } finally {
            em.close();
        }
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("user/create")
    public Response createUser(String content) {
        UserDto ud = GSON.fromJson(content, UserDto.class);
        User user = userFacade.addUser(ud.getUserName(), ud.getUserPass(), ud.getPhone(), ud.getJob());
        return Response.ok(user).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("delete/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = userFacade.deleteUser(id);
        return Response.ok(user).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("edit/{id}")
    public Response editUser(@PathParam("id") Long id, String content) {
        UserDto ud = GSON.fromJson(content, UserDto.class);
        User user = userFacade.editUser(id, ud.getUserName(), ud.getUserPass(), ud.getPhone(), ud.getJob());
        return Response.ok(user).build();
    }


}
