package com.workpilot_backend.project;

public class ProjectMemberDTO {

    private Long id;
    private Role role;

    public ProjectMember DTO(){
        return null;
    };

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
