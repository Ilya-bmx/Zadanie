package com.project.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class PointView {

    @JsonView(Views.Point.class)
    private Double lat;
    @JsonView(Views.Point.class)
    private Double lng;

}
