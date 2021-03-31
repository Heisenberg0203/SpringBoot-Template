package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){

//        return Arrays.asList(new Student(1L,"rushi","rushi@gmail.com", LocalDate.of(1998,03,02),22)
//        );
        return  studentRepository.findAll();
    }
}
