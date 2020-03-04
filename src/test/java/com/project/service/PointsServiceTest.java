package com.project.service;

import com.project.entities.Point;
import com.project.repos.PointsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PointsServiceTest {

    @Mock
    private PointsRepository pointsRepository;
    @Mock
    private GoogleGeoService googleGeoService;
    @InjectMocks
    private PointsService pointsService;

    @Before
    public void init() {
        when(googleGeoService.getPointCity("1", "0"))
                .thenReturn("Moscow");
        when(googleGeoService.getPointCity())
                .thenReturn("Moscow");
        when(pointsRepository.findAllByCity("Moscow"))
                .thenReturn(createSalePointsList());

    }

    @Test
    public void getSalePointsAsBody_SUccess() throws Exception {
        assertEquals(pointsService.getSalePointsAsBody("1", "0"), ("[ {\n" +
                "  \"id\" : null,\n" +
                "  \"name\" : \"Preo8\",\n" +
                "  \"city\" : \"Moscow\",\n" +
                "  \"address\" : null\n" +
                "}, {\n" +
                "  \"id\" : null,\n" +
                "  \"name\" : \"BALABOL\",\n" +
                "  \"city\" : \"Moscow\",\n" +
                "  \"address\" : null\n" +
                "} ]"));
    }

    @Test
    public void getSalePointsAsBody_Success_NUll_Params() throws Exception {
        assertEquals(pointsService.getSalePointsAsBody(null, null), ("[ {\n" +
                "  \"id\" : null,\n" +
                "  \"name\" : \"Preo8\",\n" +
                "  \"city\" : \"Moscow\",\n" +
                "  \"address\" : null\n" +
                "}, {\n" +
                "  \"id\" : null,\n" +
                "  \"name\" : \"BALABOL\",\n" +
                "  \"city\" : \"Moscow\",\n" +
                "  \"address\" : null\n" +
                "} ]"));
    }

    private List<Point> createSalePointsList() {
        List<Point> list = new LinkedList<>();
        list.add(Point.builder()
                .name("Preo8")
                .city("Moscow")
                .build());
        list.add(Point.builder()
                .name("BALABOL")
                .city("Moscow")
                .build());
        return list;
    }

}