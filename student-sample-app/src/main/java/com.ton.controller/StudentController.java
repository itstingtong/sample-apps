package com.ton.controller;

import com.ton.repository.StudentRepository;
import com.ton.domain.Student;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {
    
    private final StudentRepository studentRepository;
    
    Counter greetingCounter;
    Timer timer;
    
    public StudentController(StudentRepository studentRepository, MeterRegistry meterRegistry){
        
     this.studentRepository = studentRepository;
      greetingCounter = Counter.builder("api.greeting").register(meterRegistry);
     
    }
 
    @RequestMapping("greeting")
    public String getGreeting(){
        greetingCounter.increment();
        return "Hello World";
    }
 
    @RequestMapping("students")
    @Timed(value = "api.getStudents")
    public List<Student> getStudents(){
        
        List<Student> students = new ArrayList<>();
        this.studentRepository.findAll().forEach(students::add);
        return students;

    }

}
