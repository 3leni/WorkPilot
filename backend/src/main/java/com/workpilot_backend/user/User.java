package com.workpilot_backend.user;
import com.workpilot_backend.project.ProjectMember;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String passwordHash;
    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user")
    private List<ProjectMember> ProjectMemberships;

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void  setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<ProjectMember> getProjectMemberships() {
        return ProjectMemberships;
    }

    public void setProjectMemberships(List<ProjectMember> projectMemberships) {
        ProjectMemberships = projectMemberships;
    }
}