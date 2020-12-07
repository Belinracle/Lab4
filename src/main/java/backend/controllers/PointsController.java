package backend.controllers;

import backend.DTO.PointDTO;
import backend.entity.Point;
import backend.entity.User;
import backend.services.PointService;
import backend.services.UserService;
import backend.services.point.PointBuilder;
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
    public Response deletePoints(@HeaderParam("Authorization") String authorization){
        String token = authorization.substring("Bearer".length()).trim();
        Claims jwt = Jwts.parser().setSigningKey(keyGenerator.generateKey()).parseClaimsJws(token).getBody();
        try{
            pointService.deletePoints(userService.findUserByName(jwt.getSubject()));
            return Response.ok().entity("точки удалены").build();
        }catch(Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


    @GET
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoints(@HeaderParam("Authorization") String authorization) throws Exception {
        String token = authorization.substring("Bearer".length()).trim();
        Claims jwt = Jwts.parser().setSigningKey(keyGenerator.generateKey()).parseClaimsJws(token).getBody();
        return Response.ok()
                .entity(convertPointToDTO(pointService.getPoints(userService.findUserByName(jwt.getSubject()))))
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
            System.out.println(user.getName()+user.getPass());
            point.setUser(user);
            pointService.insertPoint(point);
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        pointDTO.setHit(point.getHit());
        return Response.ok().entity(pointDTO).build();
    }

    private List<PointDTO> convertPointToDTO(List<Point> points) {
        List<PointDTO> dtos = new ArrayList<>();
        for (Point point : points) {
            dtos.add(new PointDTO(point.getX().toString(), point.getY().toString(), point.getR().toString(),null, point.getHit()));
        }
        return dtos;
    }
}