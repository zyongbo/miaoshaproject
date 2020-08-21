package com.miaoshaproject.service;

import com.miaoshaproject.domain.Student;

public interface StudentService {
    public void save(Student student);
    public Student find(Long id);
}
