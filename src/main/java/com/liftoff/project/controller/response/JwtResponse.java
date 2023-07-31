package com.liftoff.project.controller.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    //private String refreshToken;
    //private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private UUID uuid;


    public JwtResponse(String accessToken, UUID uuid, String username, String email, List<String> roles, String firstName, String lastName) {
        this.token = accessToken;
        //this.refreshToken = refreshToken;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;

    }

}
