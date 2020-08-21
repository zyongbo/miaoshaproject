package com.miaoshaproject.repository;

import com.miaoshaproject.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityReposity extends ElasticsearchRepository<City, Long> {
}
