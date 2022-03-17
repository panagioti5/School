package com.school.student;

import com.school.teacher.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @BeforeEach
    void setUp() {
        Teacher teacher = Teacher.builder()
                .teacherName("Mrs John")
                .build();

        Student student = Student.builder()
                .name("Panagiotis")
                .teacher(teacher)
                .build();

        underTest.save(student);
    }

    @Test
    void itShouldReturnTheStudentWhenSearchingByName() {
        String studentName = "Panagiotis";
        Optional<Student> expected = underTest.findByName(studentName);
        assertThat(expected).isPresent();
    }

    @Test
    void itShouldNotReturnTheStudentWhenSearchingByName() {
        String studentName = "Andreas";
        Optional<Student> expected = underTest.findByName(studentName);
        assertThat(expected).isNotPresent();
    }


}