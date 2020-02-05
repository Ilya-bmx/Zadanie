package com.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.models.GoogleResponse;
import com.project.models.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import static com.project.utils.BusinnessStrings.getCity;

@Service
class GoogleGeoService {
    private static final Logger logger = LoggerFactory.getLogger(GoogleGeoService.class.getName());
    private static final String GOOGLE_MAP_URL =
            "https://maps.googleapis.com/maps/api/place/textsearch/json?query=city_hall" +
                    "&type=city_hall" +
                    "&fields=formatted_address" +
                    "&radius=5000" +
                    "&location=";
    private static final String API_KEY = "&key=AIzaSyBNIvHHcY8aMNH2FjuL2CqzbUr5L0On9gw";
    private static final String GOOGLE_MAP_URL_BIAS =
            "https://maps.googleapis.com/maps/api/place/textsearch/json?" +
                    "&type=locality" +
                    "&fields=formatted_address" +
                    "&radius=5000" +
                    "&ipbias";

    public GoogleGeoService() {
    }

    public String getPointCity(String lat, String lng) {
        try {
            GoogleResponse response = callGoogleMap(lat, lng);
            String address = Arrays.stream(response.getResults())
                    .findFirst()
                    .map(Results::getFormatted_address)
                    .orElseGet(() -> {
                        throw new RuntimeException("City Not Found near: " + lat + "," + lng + " coordinates");
                    });

            logger.debug("CITY : " + getCity(address));

            return getCity(address);
        } catch (Exception e) {

            logger.error(e.getMessage());

        }
        return null;
    }

    public String getPointCity() {
        try {
            GoogleResponse response = callGoogleMap();

            String address = Arrays.stream(response.getResults())
                    .findFirst()
                    .map(Results::getFormatted_address)
                    .orElseGet(() -> {
                        throw new RuntimeException("City Not Found near you ");
                    });
            logger.debug("CITY : " + getCity(address));
            return getCity(address);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private GoogleResponse callGoogleMap() throws IOException {
        URL url = new URL(GOOGLE_MAP_URL_BIAS + API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(RequestMethod.GET.name());
        con.setDoOutput(true);

        logger.info("Response status: " + con.getResponseCode());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        logger.info("Response: " + content.toString());

        return parseJson(content.toString());
    }

    private GoogleResponse callGoogleMap(String lat, String lng) throws IOException {
        URL url = new URL(GOOGLE_MAP_URL + lat + "," + lng + API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(RequestMethod.GET.name());
        con.setDoOutput(true);

        logger.info("Response status: " + con.getResponseCode());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        logger.info("Response: " + content.toString());

        return parseJson(content.toString());
    }

    private GoogleResponse parseJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, GoogleResponse.class);
    }

}
