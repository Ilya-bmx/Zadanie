package com.project.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoogleGeoServiceTest {

    GoogleGeoService googleGeoService;

    @Before
    public void init() {
        googleGeoService = new GoogleGeoService();
    }

    @Test
    public void getPointCity() {
        assertEquals("Moscow", googleGeoService.getPointCity());
    }

    @Test
    public void easyTest() {
        System.out.println("https://maps.googleapis.com/maps/api/place/textsearch/json?" +
                "&type=locality" +
                "&fields=formatted_address" +
                "&radius=5000" +
                "&ipbias");
    }
}