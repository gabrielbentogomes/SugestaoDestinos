package com.onthego.sugestaocidades.SugestaoDestinos.repository;

import com.onthego.sugestaocidades.SugestaoDestinos.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long>{

    List<City> findCitiesByTags(List<String> tags);
}
