package com.workpilot_backend.auth;

public class LoginResponseDTO {

    private String token;

    public LoginResponseDTO(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
