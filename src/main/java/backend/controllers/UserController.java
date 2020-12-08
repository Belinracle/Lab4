package backend.controllers;

import backend.entity.User;
import backend.services.TokenService;
import backend.services.UserService;
import backend.utils.JWTTokenNeeded;

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
}