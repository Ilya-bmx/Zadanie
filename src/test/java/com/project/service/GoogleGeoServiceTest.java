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
}