package com.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.models.PointData;
import com.project.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

@RestController
public class MainController {

    @Autowired
    private PointsService pointsService;

    @GetMapping("/salesPoints")
    public Response mainPage(@NotNull @RequestParam(name = "lat") String lat,
                             @NotNull @RequestParam(name = "long") String lng) throws JsonProcessingException {
        PointData pointData = new PointData(Double.parseDouble(lat), Double.parseDouble(lng));
        //выпилить в конце или новый эндпоинт создать
        pointsService.createPointRecord(pointData);
        return Response
                .status(200)
                .entity(pointsService.getSalePointsAsBody(pointData))
                .build();
    }

}
