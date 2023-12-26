package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
            throw new IllegalStateException("email is taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty())
            throw new IllegalStateException("student id: "+studentId+" isn't valid");
        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("student id: "+studentId+" isn't valid"));

        if (name!=null && !name.isEmpty() && !Objects.equals(student.getName(), name))
            student.setName(name);

        if (email!=null && !email.isEmpty()) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent())
                throw new IllegalStateException("email is taken");

            student.setEmail(email);
        }
    }
}
