package com.miaoshaproject.repository.impl;

import com.miaoshaproject.domain.Student;
import com.miaoshaproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StudentRepositoryImpl implements StudentRepository {

    private static final String KEY_NAME = "KEY_Student";

    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, Student> hashOperations;

    @Autowired
    public StudentRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void intialize() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Student student) {
        hashOperations.put(KEY_NAME, student.getId(), student);
    }

    @Override
    public Student find(Long id) {
        return hashOperations.get(KEY_NAME, id);
    }
}
