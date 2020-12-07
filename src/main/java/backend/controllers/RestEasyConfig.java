package backend.controllers;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/")
public class RestEasyConfig extends Application{

    private Set<Object> singletons = new HashSet<>();
    private HashSet<Class<?>> classes = new HashSet<>();

    public RestEasyConfig()
    {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        corsFilter.getAllowedOrigins().add("*");
        singletons.add(corsFilter);

        classes.add(UserController.class);
        classes.add(PointsController.class);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public HashSet<Class<?>> getClasses(){
        return classes;
    }
}