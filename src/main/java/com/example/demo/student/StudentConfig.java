package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentConfig(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            Student thushara = new Student("Thushara","thushara@gmail.com", LocalDate.of(1999, Month.AUGUST,19));
            Student nimal = new Student("Nimal", "nimal@gmail.com",LocalDate.of(2003,Month.APRIL,24));

            studentRepository.saveAll(List.of(thushara,nimal));
        };
    }
}
