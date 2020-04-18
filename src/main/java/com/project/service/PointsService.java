package com.project.service;

import com.project.entities.Point;
import com.project.entities.SalePointSpecific;
import com.project.models.PointSpecificInfo;
import com.project.models.SalePoint;
import com.project.models.SalePointInfo;
import com.project.provider.BusinessException;
import com.project.repos.PointsRepository;
import com.project.repos.SalePointSpecificRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class PointsService {

    private final SalePointSpecificRepository salePointSpecificRepository;
    private final PointsRepository pointsRepository;
    private final GoogleGeoService googleGeoService;

    public List<SalePointInfo> getAllExistPoints() {
        return pointsRepository.findAll()
                .stream()
                .map(salePoint -> new SalePointInfo(
                        salePoint.getId(),
                        salePoint.getName(),
                        salePoint.getCity(),
                        salePoint.getAddress(),
                        getPointSpecificInfo(salePoint)))
                .collect(Collectors.toList());
    }

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
        // TODO: поинт нуловым быть не должен
        Point entityPoint = createEntityPoint(point);
        pointsRepository.save(entityPoint);
    }

    public List<SalePoint> getSalePointsAsBody(String lat, String lng) throws BusinessException {
        List<SalePoint> salePoints = getSalePoints(lat, lng);
        if (salePoints.isEmpty()) throw new BusinessException("WE ARE SORRY, BUT HERE NO SALE POINTS ((");
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

    private PointSpecificInfo getPointSpecificInfo(Point point) {
        return ofNullable(point.getSalePointSpecific())
                .map(pointObj -> new PointSpecificInfo(pointObj.getId(),pointObj.getLabel()))
                .orElse(null);
    }
}
