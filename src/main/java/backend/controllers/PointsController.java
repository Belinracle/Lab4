package backend.controllers;

import backend.services.PointBuilder;
import backend.services.PointService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("points")
public class PointsController {

    @EJB
    PointService pointService;
    @EJB
    PointBuilder pointBuilder;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoints(@QueryParam("username") String username){
        return Response.ok().entity(pointService.getPoints(username)).build();
    }
    @POST
    public Response postPoint(@FormParam("x") String x, @FormParam("y") String y, @FormParam("r") String r, @FormParam("username") String username){
        try {
            pointBuilder.createPoint(x,y,r,username);
        }catch(Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.ok().entity("Точка успешно добавлена").build();
    }

}