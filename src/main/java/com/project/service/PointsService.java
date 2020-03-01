package com.project.service;

import com.project.entities.Point;
import com.project.entities.SalePointSpecific;
import com.project.models.PointSpecificInfo;
import com.project.models.SalePoint;
import com.project.repos.PointsRepository;
import com.project.repos.SalePointSpecificRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointsService {

    @Autowired
    SalePointSpecificRepository salePointSpecificRepository;
    @Autowired
    PointsRepository pointsRepository;
    @Autowired
    GoogleGeoService googleGeoService;

    @Transactional
    public void savePointSpecificInfo(PointSpecificInfo info) {
        salePointSpecificRepository.save(
                SalePointSpecific.builder()
                        .id(info.getId())
                        .label(info.getLabel())
                        .build()
        );
        System.out.println(pointsRepository.findAll());
        System.out.println(salePointSpecificRepository.findAll());
    }

    @Transactional
    public void addSalePoint(SalePoint point) {
        Point entityPoint = createEntityPoint(point);
        pointsRepository.save(entityPoint);
    }

    public List<SalePoint> getSalePointsAsBody(String lat, String lng) {
        List<SalePoint> salePoints = getSalePoints(lat, lng);
        if (salePoints.isEmpty()) throw new RuntimeException("WE ARE SORRY, BUT HERE NO SALE POINTS ((");
        return salePoints;
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


    private Point createEntityPoint(SalePoint point) {
        return Point.builder()
                .name(point.getName())
                .city(point.getCity())
                .address(point.getAddress())
                .build();
    }
}
