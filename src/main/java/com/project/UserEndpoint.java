package com.project;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Controller
@Path("/")
public class UserEndpoint {

    //в разработке
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/request")
    @GET
    public String checkRequest(@Context UriInfo uriInfo, @Context HttpServletRequest request) {
        System.out.println(uriInfo);
        System.out.println(request.toString());
        return "All is Fine";
    }

    //в разработке
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/response")
    @GET
    public String addCookie(@Context UriInfo uriInfo, @Context HttpServletResponse response) {
        System.out.println(uriInfo);
        System.out.println(response.toString());
        return "All is Fine";
    }
}
