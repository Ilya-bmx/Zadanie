package com.project.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalePoint {
    private Integer id;
    private String name;
    private String city;
    private String address;

    public String toString() {
        return "[" + id + " " + name +
                " " + city + " " + address +"]";
    }
}
