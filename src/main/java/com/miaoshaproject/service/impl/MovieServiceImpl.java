package com.miaoshaproject.service.impl;

import com.miaoshaproject.domain.Movie;
import com.miaoshaproject.repository.MovieRepository;
import com.miaoshaproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void add(Movie movie) {
        movieRepository.add(movie);
    }

    @Override
    public void delete(String id) {
        movieRepository.delete(id);
    }

    @Override
    public Movie findMovie(String id) {
        return movieRepository.findMovie(id);
    }

    @Override
    public Map<Object, Object> findAllMovies() {
        return movieRepository.findAllMovies();
    }
}
