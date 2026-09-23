package com.workpilot_backend.user;

public class UserResponseDTO {
    public Long id;
    public  String name;
    public String email;
    public Role role;

    public UserResponseDTO(){}

    public void setName(String name){
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
