package com.liftoff.project.controller.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO {

    private String token;
    private String username;
    private String role;
    private String firstName;
    private String lastName;

}
