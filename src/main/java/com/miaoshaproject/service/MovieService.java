package com.miaoshaproject.service;

import com.miaoshaproject.domain.Movie;

import java.util.Map;

public interface MovieService {
    public void add(final Movie movie);
    public void delete(final String id);
    public Movie findMovie(final String id);
    public Map<Object, Object> findAllMovies();
}
