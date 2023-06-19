package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.User;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
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
    public String getInfoForAll() {
        return "{\"msg\":\"Hello User\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        return GSON.toJson(userFacade.getAllUsers());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}")
//    @RolesAllowed("user")
    public String getUserAssignments(@PathParam("userName") String userName) {
        return GSON.toJson(userFacade.getUserEvents(userName));

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String content) {
        UserDTO udto = GSON.fromJson(content, UserDTO.class);
        UserDTO ud = userFacade.createUser(udto);
        return Response.ok(GSON.toJson(new User(ud.getUserName(), ud.getPassword()))).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{userName}")
    public Response deleteUser(@PathParam("userName") String userName) {
        UserDTO udto = userFacade.deleteUser(userName);
        return Response.ok(GSON.toJson(new User(udto.getUserName(), udto.getPassword()))).build();
    }
}