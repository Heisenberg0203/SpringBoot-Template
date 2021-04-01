package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return  args -> {
          Student s1 = new Student("rushi","rushi@gmail.com", LocalDate.of(1998,03,02));
            Student s2 = new Student("xyz","xyz@gmail.com", LocalDate.of(1992,03,02));
            studentRepository.saveAll(List.of(s1,s2));
        };
    }

}
