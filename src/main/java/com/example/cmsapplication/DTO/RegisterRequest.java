package com.example.cmsapplication.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterRequest(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
