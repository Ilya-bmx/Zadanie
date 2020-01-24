package com.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.entities.Point;
import com.project.models.PointData;
import com.project.models.SalePoint;
import com.project.repos.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointsService {

    @Autowired
    PointsRepository pointsRepository;
    @Autowired
    GoogleGeoService googleGeoService;

    public PointsService() {
    }

    public void createPointRecord(PointData pointData) {
        pointsRepository.save(
                Point.builder()
                        .address("Ozernaya4")
                        .city("Philadelphia")
                        .name("BingoBoom")
                        .build());
        System.out.println("AllIsFine");
    }

    public String getSalePointsAsBody(PointData pointData) throws JsonProcessingException {
        List<SalePoint> salePoints = getSalePoints(pointData);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper
                .writeValueAsString(salePoints);
    }

    private List<SalePoint> getSalePoints(PointData pointData) {
        String city = googleGeoService.getPointCity(pointData.getLat(), pointData.getLng());
        return pointsRepository.findAllByCity(city)
                .stream()
                .map(point ->
                        SalePoint.builder()
                                .address(point.getAddress())
                                .city(point.getCity())
                                .id(point.getId())
                                .name(point.getName())
                                .build())
                .collect(Collectors.toList());
    }

}
