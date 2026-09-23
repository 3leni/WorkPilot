package com.workpilot_backend.project;

import com.workpilot_backend.auth.AuthService;
import com.workpilot_backend.exception.UnauthorizedException;
import com.workpilot_backend.exception.UserNotFoundException;
import com.workpilot_backend.user.User;
import com.workpilot_backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    public ProjectMemberService(ProjectMemberRepository projectMemberRepository, UserRepository userRepository, AuthService authService){
        this.projectMemberRepository = projectMemberRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public void replaceMembers(Project project, List<ProjectMemberDTO> members){
        deleteMembers(project);
        createMembers(project, members );
    }

    public void createMembers(Project project, List<ProjectMemberDTO> members){
        for (ProjectMemberDTO memberDTO: members) {
            ProjectMember member = createMember(project, memberDTO);
            projectMemberRepository.save(member);
        }
    }

    private ProjectMember createMember(Project project, ProjectMemberDTO memberDTO){
        User user = userRepository.findById(memberDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(user);
        projectMember.setRole(memberDTO.getRole());

        return projectMember;
    }

    private ProjectMemberDTO toDTO(ProjectMember projectMember){
        ProjectMemberDTO dto = new ProjectMemberDTO();

        dto.setId(projectMember.getUser().getId());
        dto.setRole(projectMember.getRole());

        return dto;
    }

    public List<ProjectMemberDTO> toMemberDTOS(Project project){
        return project.getMembers().stream().map(this::toDTO).toList();

    }

    public void deleteMembers(Project project){
                projectMemberRepository.deleteByProject(project);
    }

    public void belongUserAndProjectWithAuth(Project project) {
        User user = authService.getAuthenticatedUser();
        ProjectMember member = projectMemberRepository.findByProjectAndUser(project, user)
                .orElseThrow(() -> new UnauthorizedException("You are not a member of this project"));

        if (member.getRole() != Role.ADMIN) {
            throw new UnauthorizedException(
                    "Only the project ADMIN can delete/update the project"
            );
        }
    }

}
