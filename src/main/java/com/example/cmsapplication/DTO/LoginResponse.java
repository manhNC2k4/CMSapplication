package com.example.cmsapplication.DTO;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
