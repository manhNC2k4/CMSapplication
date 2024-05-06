package com.example.cmsapplication.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String email;
    private Long id;
    private boolean isAdmin;
    private String message;

    public LoginResponse(String username, String email, Long id, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.isAdmin = isAdmin;
    }
}
