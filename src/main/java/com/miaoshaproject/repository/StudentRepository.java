package com.miaoshaproject.repository;

import com.miaoshaproject.domain.Student;

public interface StudentRepository {

    void save(Student student);

    Student find(Long id);
}
