package com.onthego.sugestaocidades.SugestaoDestinos.controller;

import com.onthego.sugestaocidades.SugestaoDestinos.entity.City;
import com.onthego.sugestaocidades.SugestaoDestinos.repository.CityRepository;
import com.onthego.sugestaocidades.SugestaoDestinos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/bulk")
    public List<City> createCity(@RequestBody List<City> cities) {
        return cityService.bulkCities(cities);
    }

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/tags")
    public ResponseEntity<Map<String, List<String>>> getAllTags() {
        List<String> tags = cityService.getAllTags();
        Map<String, List<String>> response = new HashMap<>();
        response.put("tags", tags);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bytags")
    public ResponseEntity<List<City>> getCitiesByTags(@RequestParam List<String> tags) {
        List<City> suggestions = cityService.getCityByTags(tags);
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }
}
