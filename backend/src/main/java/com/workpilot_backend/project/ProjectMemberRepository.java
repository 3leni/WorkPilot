package com.workpilot_backend.project;

import com.workpilot_backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    List<ProjectMember> findByProjectId(Long projectId);
    void deleteByProject(Project project);
    Optional<ProjectMember> findByProjectAndUser(Project project, User user);


}
