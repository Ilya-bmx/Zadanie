package com.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    public String getSalePointsAsBody(String lat, String lng) throws JsonProcessingException {
        List<SalePoint> salePoints = getSalePoints(lat, lng);
        if (salePoints.isEmpty()) return "WE ARE SORRY, BUT HERE NO SALE POINTS ((";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(salePoints);
    }

    private List<SalePoint> getSalePoints(String lat, String lng) {
        String city = (lat == null || lng == null)
                ? googleGeoService.getPointCity()
                : googleGeoService.getPointCity(lat, lng);
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
