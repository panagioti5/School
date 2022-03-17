package com.school.student;

import com.school.teacher.Teacher;
import com.school.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherService teacherService;

    @Autowired
    public StudentService(StudentRepository studentRepository, TeacherService teacherService) {
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
    }

    public Student createNewStudent(Student student){
        return studentRepository.save(student);
    }

    public Student findStudentById(Long studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with id: " + studentId + " Does not exists"));
    }

    public Teacher findTeacherFromStudentId(Long studentId) {
        Student student = this.findStudentById(studentId);
        return student.getTeacher();
    }

    public Student addTeacherForStudent(Long studentId, Long teacherId){
        Student student = this.findStudentById(studentId);
        student.setTeacher(teacherService.findTeacherById(teacherId));
        return student;
    }

    public Student findStudentByStudentName(String studentName) {
        return studentRepository.findByName(studentName)
                .orElseThrow(() -> new IllegalArgumentException("Student with name: " + studentName + " Does not exists"));
    }
}