package com.miaoshaproject.service.impl;

import com.miaoshaproject.domain.Student;
import com.miaoshaproject.repository.StudentRepository;
import com.miaoshaproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student find(Long id) {
        return studentRepository.find(id);
    }
}
