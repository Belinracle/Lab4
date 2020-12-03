package backend.controllers;

import backend.entity.User;
import backend.services.TokenService;
import backend.services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("users")
public class UserController {
    @EJB
    UserService userService;
    @EJB
    TokenService tokenService;

    @POST
    public Response insertUser(@FormParam("name") String name, @FormParam("pass")
            String pass){
        User user = new User(name,pass);
        try {
            userService.registerUser(user);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().entity("Ошибка на сервере: "+e.getMessage()).build();
        }
    }

    @GET
    @Path("/login")
    public Response authenticate(@FormParam("login") String login,
                                 @FormParam("password") String password){
        try {
            userService.authenticate(login, password);
        }catch(Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        String token = tokenService.issueToken(login);
        return Response.ok().entity(token).build();
    }
}