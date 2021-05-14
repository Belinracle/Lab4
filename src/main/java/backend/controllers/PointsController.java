package backend.controllers;

import backend.DTO.PointDTO;
import backend.entity.Point;
import backend.entity.User;
import backend.profiling.AreaManager;
import backend.profiling.AreaManagerMBean;
import backend.profiling.PointsManager;
import backend.profiling.PointsManagerMBean;
import backend.services.PointService;
import backend.services.UserService;
import backend.services.point.PointBuilder;
import backend.services.point.PointInVisibleAreaCalculator;
import backend.utils.JWTTokenNeeded;
import backend.utils.KeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("points")
public class PointsController {

    private PointInVisibleAreaCalculator calculator = new PointInVisibleAreaCalculator();
    @EJB
    AreaManager areaManager;
    @EJB
    PointsManager pointManager;
    @EJB
    PointService pointService;
    @EJB
    PointBuilder pointBuilder;
    @EJB
    UserService userService;
    @EJB
    KeyGenerator keyGenerator;

    @DELETE
    @JWTTokenNeeded
    public Response deletePoints(@HeaderParam("Authorization") String authorization) {
        String token = authorization.substring("Bearer".length()).trim();
        Claims jwt = Jwts.parser().setSigningKey(keyGenerator.generateKey()).parseClaimsJws(token).getBody();
        try {
            pointService.deletePoints(userService.findUserByName(jwt.getSubject()));
            return Response.ok().entity("точки удалены").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


    @GET
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoints(@HeaderParam("Authorization") String authorization) throws Exception {
        String token = authorization.substring("Bearer".length()).trim();
        Claims jwt = Jwts.parser().setSigningKey(keyGenerator.generateKey()).parseClaimsJws(token).getBody();
        User user = userService.findUserByName(jwt.getSubject());
        List<Point> points = pointService.getPoints(user);
        pointManager.initUserInMBean(user.getName(), points.size(), points.stream().filter(e -> !e.getHit()).count());
        return Response.ok()
                .entity(convertPointToDTO(points))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response postPoint(PointDTO pointDTO) {
        Point point;
        try {
            point = pointBuilder.createPoint(pointDTO.getX(), pointDTO.getY(), pointDTO.getR());
            User user = userService.findUserByName(pointDTO.getUsername());
            pointManager.increasePointsCounter(user.getName(), point.getHit());
            if (!calculator.isVisible(point.getX(), point.getY(), point.getR()))
                pointManager.notifyAboutInvisiblePoint();
            areaManager.setArea(user.getName(), point.getR());
            point.setUser(user);
            pointService.insertPoint(point);
            pointDTO.setHit(point.getHit());
            pointDTO.setR(point.getR().toString());
            return Response.ok().entity(pointDTO).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    private List<PointDTO> convertPointToDTO(List<Point> points) {
        List<PointDTO> dtos = new ArrayList<>();
        for (Point point : points) {
            dtos.add(new PointDTO(point.getX().toString(), point.getY().toString(), point.getR().toString(), null, point.getHit()));
        }
        return dtos;
    }
}