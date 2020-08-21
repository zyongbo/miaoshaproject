package com.miaoshaproject.repository;

import com.miaoshaproject.domain.Movie;

import java.util.Map;

public interface MovieRepository {
    Map<Object, Object> findAllMovies();
    void add(Movie movie);
    void delete(String id);
    Movie findMovie(String id);
}
