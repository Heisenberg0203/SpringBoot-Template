package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent=studentRepository.findStudentByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new IllegalArgumentException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw  new IllegalArgumentException("Student with"+studentId+"does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student;
        if(!optionalStudent.isPresent()){
            throw new IllegalStateException("no student exists with id"+studentId);
        }
        else
            student=optionalStudent.get();
        if(name!=null&&name.length()>0&&!student.getName().equals(name)){
            student.setName(name);
        }

        if(email!=null&&email.length()>0&&!student.getEmail().equals(email)){
            if(studentRepository.findStudentByEmail(email).isPresent()){
                throw new IllegalArgumentException(("email taken"));
            }
            student.setEmail(email);
        }
    }
}
