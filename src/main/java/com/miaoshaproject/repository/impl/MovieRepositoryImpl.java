package com.miaoshaproject.repository.impl;

import com.miaoshaproject.domain.Movie;
import com.miaoshaproject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private static final String KEY = "KEY_Movie";

    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;

    @Autowired
    public MovieRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<Object, Object> findAllMovies() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void add(final Movie movie) {
        hashOperations.put(KEY, movie.getId(), movie.getName());
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public Movie findMovie(String id) {
        return (Movie) hashOperations.get(KEY, id);
    }
}
