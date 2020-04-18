package com.project;

import com.project.models.PointSpecificInfo;
import com.project.models.SalePoint;
import com.project.models.SalePointInfo;
import com.project.provider.BusinessException;
import com.project.service.PointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Path("/")
public class MainController {

    private final PointsService pointsService;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/points")
    @GET
    public List<SalePoint> getSalePoints(@QueryParam(value = "lat") String lat,
                                         @QueryParam(value = "long") String lng) throws BusinessException {
        return pointsService.getSalePointsAsBody(lat, lng);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/point")
    @PUT
    public void addSalePoint(SalePoint point) {
        pointsService.addSalePoint(point);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pointInfo")
    @PUT
    public void specifyPoint(PointSpecificInfo info) {
        pointsService.savePointSpecificInfo(info);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allPoints")
    @GET
    public List<SalePointInfo> getAllExistPoints() {
        return pointsService.getAllExistPoints();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String checkHealth() {
        return "You are now on the first page \n " +
                "Use that paths: \n" +
                "/allPoints @GET \n" +
                "/pointInfo @PUT \n" +
                "/points @GET lat long\n" +
                "/point @PUT SalePoint \n";
    }

    // test delete it
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/point")
    @POST
    public void addSalePointPost(SalePoint point) {
        pointsService.addSalePoint(point);
    }


}
