package com.miaoshaproject.controller;

import com.miaoshaproject.domain.Student;
import com.miaoshaproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Student class should changed to VO Student
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void saveStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @Cacheable(key = "#id", value = "students", unless = "#result.id < 1200")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Student fetchStudent(@RequestParam(name = "id") Long id) {
        return studentService.find(id);
    }
}
