package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DinnerEventDTO;
import entities.DinnerEvent;
import facades.DinnerEventFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("event")
public class DinnerEventResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DinnerEventFacade dinnerEventFacade =  DinnerEventFacade.getDinnerEventFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello DinnerEvent\"}";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public List<DinnerEventDTO> allDinnerEvents() {

        List<DinnerEventDTO> de = dinnerEventFacade.getAllEvents();
        return de;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createDinnerEvent(String content) {

        DinnerEventDTO de = GSON.fromJson(content, DinnerEventDTO.class);
        DinnerEventDTO dedto = DinnerEventFacade.createEvent(de);
        return Response.ok(GSON.toJson(new DinnerEvent(de.getTime(), de.getLocation(), de.getDish(), de.getPrice()))).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deleteDinnerEvent(@PathParam("id") Long id, String content) throws Exception {
        DinnerEventDTO de = GSON.fromJson(content, DinnerEventDTO.class);
        dinnerEventFacade.deleteEvent(id);
        return Response.ok().build();
    }


    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateDinnerEvent(@PathParam("id") Long id, String content) throws Exception {
        DinnerEventDTO dedto = GSON.fromJson(content, DinnerEventDTO.class);
        DinnerEventDTO de = dinnerEventFacade.editEvent(id, dedto);
        return Response.ok().entity(de).build();
    }


}
