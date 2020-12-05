package backend.controllers;

import backend.DTO.PointDTO;
import backend.entity.Point;
import backend.services.PointService;
import backend.services.UserService;
import backend.services.point.PointBuilder;

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
    @EJB
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoints(@QueryParam("username") String username) throws Exception {
        return Response.ok().entity(userService.findUserByName(username).getPoints()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPoint(PointDTO pointDTO) {
        Point point;
        try {
            point = pointBuilder.createPoint(pointDTO.getX(), pointDTO.getY(), pointDTO.getR());
            userService.updateUser(pointDTO.getUsername(),point);
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        pointDTO.setHit(point.getHit());
        return Response.ok().entity(pointDTO).build();
    }

}