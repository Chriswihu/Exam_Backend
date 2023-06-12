package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RentalDto;
import entities.Rental;

import facades.RentalFacade;
import utils.EMF_Creator;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author lam@cphbusiness.dk
 */
@Path("rental")
public class RentalResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final RentalFacade rentalFacade = RentalFacade.getRentalFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allRental() {

        EntityManager em = EMF.createEntityManager();
        try{
            TypedQuery<Rental> query = em.createQuery("select r from Rental r", entities.Rental.class);
            List<Rental> rentals = query.getResultList();
            return "[" + rentals.size() + "]" + rentals;
        }finally{
            em.close();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRentalById(@PathParam("id") Long id) {
        RentalDto rd = rentalFacade.getById(id);
        rd.setId(id);
        return Response.ok(GSON.toJson(new Rental(rd.getStartDate(), rd.getEndDate(), rd.getPriceAnnual(), rd.getDeposit(), rd.getContactPerson()))).build();
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createRental(String content) {
        RentalDto rd = GSON.fromJson(content, RentalDto.class);
        RentalDto r = rentalFacade.create(rd);
        return Response.ok(GSON.toJson(new Rental(r.getStartDate(), r.getEndDate(), r.getPriceAnnual(), r.getDeposit(), r.getContactPerson()))).build();
    }
}