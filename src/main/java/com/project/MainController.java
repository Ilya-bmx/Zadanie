package com.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MainController {

    @Autowired
    private PointsService pointsService;

    @Path("/points")
    @GET
    public String getSalePoints(@QueryParam(value = "lat") String lat,
                                @QueryParam(value = "long") String lng) throws JsonProcessingException {
        return pointsService.getSalePointsAsBody(lat, lng);
    }

}
