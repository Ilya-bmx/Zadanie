package com.project;

import com.project.models.PointSpecificInfo;
import com.project.models.SalePoint;
import com.project.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MainController {

    @Autowired
    private PointsService pointsService;

    @Path("/points")
    @GET
    public List<SalePoint> getSalePoints(@QueryParam(value = "lat") String lat,
                                         @QueryParam(value = "long") String lng) {
        return pointsService.getSalePointsAsBody(lat, lng);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/point")
    @PUT
    public void addSalePoint(SalePoint point){
        pointsService.addSalePoint(point);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pointInfo")
    @PUT
    public void specifyPoint(PointSpecificInfo info) {
        pointsService.savePointSpecificInfo(info);
    }
}
