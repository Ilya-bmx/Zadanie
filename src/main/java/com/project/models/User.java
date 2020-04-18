package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;

    public String toString() {
        return "User:::::::: " + this.email + " " + this.name + " " + this.password;
    }
}
