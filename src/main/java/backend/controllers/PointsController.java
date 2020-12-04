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
    public Response getPoints(@QueryParam("username") String username) {
        return Response.ok().entity(pointService.getPoints(username)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPoint(PointDTO pointDTO) {
        System.out.println("дошло до точек");
        try {
            Point point = pointBuilder.createPoint(pointDTO.getX(), pointDTO.getY(), pointDTO.getR());
            userService.updateUser(pointDTO.getUsername(),point);
            pointService.insertPoint(point);
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.ok().entity("Точка успешно добавлена").build();
    }

}