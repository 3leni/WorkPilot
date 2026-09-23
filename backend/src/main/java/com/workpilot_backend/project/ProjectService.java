package com.workpilot_backend.project;

import com.workpilot_backend.auth.AuthService;
import com.workpilot_backend.exception.ProjectAlreadyExistsException;
import com.workpilot_backend.exception.ProjectNotFoundException;
import com.workpilot_backend.user.User;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {
    private final AuthService authService;
    private final ProjectMemberService projectMemberService;
    private final ProjectRepository projectRepository;


    public ProjectService(ProjectMemberService projectMemberService, ProjectRepository projectRepository, AuthService authService){
        this.projectMemberService = projectMemberService;
        this.projectRepository = projectRepository;
        this.authService = authService;
    }

    @Transactional
    public Project createProject(ProjectCreateDTO request){
        if(projectRepository.findByName(request.getName()).isPresent()){
            throw new ProjectAlreadyExistsException("Project already exists");
        }
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCreated_at(LocalDateTime.now());
        project.setUpdated_at(LocalDateTime.now());
        project = projectRepository.save(project);

        projectMemberService.createMembers(project, request.getMembers());
        return project;
    }

    private ProjectResponseDTO toResponseDTO(Project project){

        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());

        dto.setMembers(projectMemberService.toMemberDTOS(project));

        return dto;
    }

    public List<ProjectResponseDTO> getAllProjects(){
        User user = authService.getAuthenticatedUser();
        List<Project> projects = projectRepository.findByMembersContaining(user);

        return projects.stream()
        .map(this::toResponseDTO)
                .toList();
    }

    public ProjectResponseDTO getProjectName(String name){
        Project project = projectRepository.findByName(name).orElseThrow(
                () -> new ProjectNotFoundException("Project not found")
        );
        return toResponseDTO(project);
    }

    @Transactional
    public Project updateProject(Long id, ProjectUpdateDTO request){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        projectMemberService.belongUserAndProjectWithAuth(project);
        validateProjectName(request.getName(), id);

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setUpdated_at(LocalDateTime.now());

        projectMemberService.replaceMembers(project,request.getMembers());
        return project;
    }

    private void validateProjectName(String name, Long projectId){
        if(projectRepository.existsByNameAndIdNot(name, projectId)){
            throw new ProjectAlreadyExistsException("Project already exists");
        }
    }

    @Transactional
    public void deleteProject(Long id){

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        projectMemberService.belongUserAndProjectWithAuth(project);

        projectMemberService.deleteMembers(project);
        projectRepository.delete(project);
    }
}

