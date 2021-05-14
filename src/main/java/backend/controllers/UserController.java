package backend.controllers;

import backend.entity.User;
import backend.profiling.AreaManager;
import backend.profiling.AreaManagerMBean;
import backend.profiling.PointsManager;
import backend.profiling.PointsManagerMBean;
import backend.services.TokenService;
import backend.services.UserService;
import backend.utils.JWTTokenNeeded;
import backend.utils.KeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @EJB
    UserService userService;
    @EJB
    TokenService tokenService;
    @EJB
    PointsManager pointManager;
    @EJB
    AreaManager areaManager;
    @EJB
    KeyGenerator keyGenerator;

    @POST
    public Response insertUser(User user) {
        try {
            userService.registerUser(user);
            return Response.ok("Вы успешно зарегистрированы").build();
        } catch (Exception e) {
            return Response.serverError().entity("Ошибка на сервере: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response authenticate(User user) {
        try {
            userService.authenticate(user.getName(), user.getPass());
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        String token = tokenService.issueToken(user.getName());
        return Response.ok().entity(token).build();
    }

    @GET
    @Path("/check")
    @JWTTokenNeeded
    public Response jwtCheck() {
        System.out.println("проверка пользователя");
        return Response.ok("проверка на токен прошла").build();
    }

    @Path("/logout")
    @POST
    public void logout(@HeaderParam("Authorization") String authorization) throws Exception {
        System.out.println("Удаление проверка");
        String token = authorization.substring("Bearer".length()).trim();
        Claims jwt = Jwts.parser().setSigningKey(keyGenerator.generateKey()).parseClaimsJws(token).getBody();
        User user = userService.findUserByName(jwt.getSubject());
        pointManager.removeUser(user.getName());
        areaManager.removeUser(user.getName());
    }
}