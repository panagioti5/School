package com.school.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping()
    public ResponseEntity<Teacher> createNewTeacher(@RequestBody Teacher teacher){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/teacher").toUriString());
        return ResponseEntity.created(uri).body(teacherService.createNewTeacher(teacher));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Teacher> retrieveTeacherById(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

}
