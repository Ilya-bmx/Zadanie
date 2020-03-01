package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
