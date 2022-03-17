package com.school.teacher;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with id: " + teacherId + " Does not exists"));
    }

    public Teacher createNewTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
}
