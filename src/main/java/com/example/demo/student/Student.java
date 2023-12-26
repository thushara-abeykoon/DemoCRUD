package com.example.demo.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import java.time.LocalDate;
import java.time.Period;

@Setter
@Entity
@Table
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "sequence_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence_generator", allocationSize = 1)
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private LocalDate dob;
    @Transient
    private Integer age;

    public Student(Long id, String name, String email, LocalDate dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
    public Student(String name, String email, LocalDate dob){
        this.name = name;
        this.email = email;
        this.dob = dob;
    }


    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }


}
