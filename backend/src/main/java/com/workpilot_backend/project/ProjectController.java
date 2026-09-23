package com.workpilot_backend.project;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping()
    public ResponseEntity<Void> createProject (@Valid @RequestBody ProjectCreateDTO request){
        projectService.createProject(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity <List<ProjectResponseDTO>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProjectResponseDTO> getProjectByName(@PathVariable String name){
        return ResponseEntity.ok(projectService.getProjectName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO request){
        projectService.updateProject(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

