package com.miaoshaproject.controller;

import com.miaoshaproject.domain.Movie;
import com.miaoshaproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller("movie")
@RequestMapping("/movie")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/keys")
    @ResponseBody
    public Map<Object, Object> keys() {
        return movieService.findAllMovies();

    }

    @RequestMapping("/values")
    @ResponseBody
    public Map<String, String> findAll() {
        Map<Object, Object> movies = movieService.findAllMovies();
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : movies.entrySet()) {
            String key = (String) entry.getKey();
            map.put(key, movies.get(key).toString());
        }
        return map;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam(name = "key") String key,
                                      @RequestParam(name = "value") String value) {
        Movie movie = new Movie(key, value);
        movieService.add(movie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam(name = "key") String key) {
        movieService.delete(key);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
