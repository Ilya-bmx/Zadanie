package com.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private PointsService pointsService;


    @GetMapping("/salesPoints")
    public String getSalePoints(@RequestParam(name = "lat", required = false) String lat,
                                @RequestParam(name = "long", required = false) String lng
    ) throws JsonProcessingException {
        return pointsService.getSalePointsAsBody(lat, lng);
    }

}
