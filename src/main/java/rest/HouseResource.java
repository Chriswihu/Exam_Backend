package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.House;
import entities.User;
import facades.HouseFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("house")
public class HouseResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final HouseFacade houseFacade = HouseFacade.getHouseFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHouses(){
        return GSON.toJson(houseFacade.getAll());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public List<House> allHouses() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<House> query = em.createQuery("select h from House h", entities.House.class);
            List<House> houses = query.getResultList();
            return houses;
        } finally {
            em.close();
        }
    }
}