package com.workpilot_backend.project;

public class ProjectMemberDTO {

    private Long userId;
    private ProjectMemberRole projectMemberRole;

    public ProjectMemberDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProjectMemberRole getRole() {
        return projectMemberRole;
    }

    public void setRole(ProjectMemberRole projectMemberRole) {
        this.projectMemberRole = projectMemberRole;
    }
}