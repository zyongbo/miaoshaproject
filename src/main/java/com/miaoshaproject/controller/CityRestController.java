package com.miaoshaproject.controller;

import com.miaoshaproject.domain.City;
import com.miaoshaproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("city")
@RequestMapping("/city")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class CityRestController {

    @Autowired
    private CityService cityService;

//    public Long createCity(@RequestBody City city) {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public Long createCity(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "provinceId") Long provinceId) {
        City city = new City();
        city.setId(id);
        city.setProvinceId(provinceId);
        city.setCityName("Hongkong");
        city.setDescription("This is a very beautiful city");
        return cityService.saveCity(city);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<City> searchCity(@RequestParam(name = "pageNumber") Integer pageNumber,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(name = "searchContent") String searchContent) {
        return cityService.searchCity(pageNumber, pageSize, searchContent);
    }
}
