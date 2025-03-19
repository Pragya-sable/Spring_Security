package com.demo.springsecurityexample.controller;

import com.demo.springsecurityexample.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(Arrays.asList(new Student(101,"Pragya",94), new Student(102,"Harsh",93)));

    @GetMapping("/students")
    public List<Student> getStudent(){
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students/save")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

}
