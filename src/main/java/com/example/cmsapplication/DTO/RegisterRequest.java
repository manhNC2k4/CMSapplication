package com.example.cmsapplication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String email;

}
