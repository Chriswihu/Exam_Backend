package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import entities.Assignment;
import facades.AssignmentFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("assignment")
public class AssignmentResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final AssignmentFacade assignmentFacade = AssignmentFacade.getAssignmentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Assignment\"}";
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public String getAllAssignments() {
        return GSON.toJson(assignmentFacade.getAllAssignments());
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAssignment(String content) {
        AssignmentDTO ad = GSON.fromJson(content, AssignmentDTO.class);
        AssignmentDTO adto = assignmentFacade.assignToEvent(ad);
        return Response.ok(GSON.toJson(adto)).build();

    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deleteAssignment(@PathParam("id") Long id, String content) throws Exception {
//        AssignmentDTO adto = GSON.fromJson(content, AssignmentDTO.class);
        assignmentFacade.deleteAssignmentById(id);
        return Response.ok(GSON.toJson("Assignment deleted")).build();



    }
}
