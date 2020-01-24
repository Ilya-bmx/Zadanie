package com.project.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BusinnessStringsTest {

    @Test
    public void getCity() {
        assertEquals("Moscow", BusinnessStrings.getCity("Moscow,Russia"));
        assertEquals("Moscow", BusinnessStrings.getCity("Avenue,Moscow,Russia ARABIC"));
        assertEquals("Moscow", BusinnessStrings.getCity("Avenue,Moscow,1234"));
    }
}