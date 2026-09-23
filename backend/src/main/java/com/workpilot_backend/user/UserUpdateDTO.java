package com.workpilot_backend.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @Size(min=8, max=30)
    @Pattern(regexp = ".*[A-ZÁ-ÚÜÑ].*")
    private String password;

    public UserUpdateDTO(){}

    public UserUpdateDTO(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
