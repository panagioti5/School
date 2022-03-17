package com.school.student;

import com.school.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/student").toUriString());
        return ResponseEntity.created(uri).body(studentService.createNewStudent(student));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping("/id/{studentId}/teachers")
    public ResponseEntity<Teacher> findTeacherFromStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok().body(studentService.findTeacherFromStudentId(studentId));
    }

    @PutMapping("/id/{studentId}/add/teacher/id/{teacherId}")
    public ResponseEntity<Student> addTeacherForStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        return ResponseEntity.ok(studentService.addTeacherForStudent(studentId, teacherId));
    }

    @GetMapping("/name/{studentName}")
    public ResponseEntity<Student> findStudentByName(@PathVariable String studentName){
        return ResponseEntity.ok(studentService.findStudentByStudentName(studentName));
    }

}