package com.project.models;

import lombok.Data;

@Data
public class GoogleResponse {
    private Object[] html_attributions;
    private Results[] results;
    private String status;
    private Object next_page_token;
}
