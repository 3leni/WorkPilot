package com.workpilot_backend.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ProjectCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    private List<ProjectMemberDTO> members;

    public ProjectCreateDTO(){};

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public List<ProjectMemberDTO> getMembers(){
        return this.members;
    }

    public void setMembers(List<ProjectMemberDTO> members) {
        this.members = members;
    }
}
