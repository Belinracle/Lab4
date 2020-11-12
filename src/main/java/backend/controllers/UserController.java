package backend.controllers;

import backend.entity.User;
import backend.services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("users")
public class UserController {
    @EJB
    UserService userService;

    @POST
    public Response insertUser(@FormParam("name") String name, @FormParam("pass")
            String pass){
        System.out.println(name+pass);
        User user = new User(name,pass);
        try {
            userService.registerUser(user);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().entity("Ошибка на сервере: "+e.getMessage()).build();
        }
    }

    @GET
    public Response check(){
        return Response.ok().entity("па кайфуЭ").build();
    }
}
