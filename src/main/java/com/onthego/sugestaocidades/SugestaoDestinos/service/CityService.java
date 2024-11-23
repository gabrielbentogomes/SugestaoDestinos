package com.onthego.sugestaocidades.SugestaoDestinos.service;

import com.onthego.sugestaocidades.SugestaoDestinos.entity.City;
import com.onthego.sugestaocidades.SugestaoDestinos.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }
    public List<City> bulkCities(List<City> cities) {
        return cityRepository.saveAll(cities);
    }

    public List<City> getCityByTags(List<String> selectedTags) {
        // Busca todas as cidades
        List<City> allCities = cityRepository.findAll();

        // Ordena as cidades com base no número de tags em comum
        List<City> sortedCities = allCities.stream()
                .sorted((city1, city2) -> {
                    // Conta o número de tags em comum para cada cidade
                    long commonTagsCity1 = city1.getTags().stream().filter(selectedTags::contains).count();
                    long commonTagsCity2 = city2.getTags().stream().filter(selectedTags::contains).count();

                    // Ordena em ordem decrescente (mais tags iguais primeiro)
                    return Long.compare(commonTagsCity2, commonTagsCity1);
                })
                .toList();

        // Retorna as 3 cidades com mais tags correspondentes
        return sortedCities.stream()
                .filter(city -> city.getTags().stream().anyMatch(selectedTags::contains)) // Apenas cidades com pelo menos 1 tag em comum
                .limit(3) // Limita a 3 resultados
                .toList();
    }

    public List<String> getAllTags() {
        return cityRepository.findAll().stream()
                .flatMap(city -> city.getTags().stream()) // Extrai todas as tags das cidades
                .distinct() // Remove duplicatas
                .sorted() // Ordena alfabeticamente (opcional)
                .collect(Collectors.toList()); // Coleta em uma List
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }
}
