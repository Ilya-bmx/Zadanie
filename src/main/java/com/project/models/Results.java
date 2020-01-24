package com.project.models;

import lombok.Data;

@Data
public class Results {
    private String formatted_address;
    private Object geometry;
    private String name;
    private Object opening_hours;
    private Object[] photos;
    private Object icon;
    private String rating;
    private String status;
    private String id;
    private String place_id;
    private Object plus_code;
    private Object reference;
    private Object types;
    private Object user_ratings_total;
}
