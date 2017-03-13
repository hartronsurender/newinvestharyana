package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.City_town_villageService;
import com.hartron.investharyana.domain.City_town_village;
import com.hartron.investharyana.repository.City_town_villageRepository;
import com.hartron.investharyana.service.dto.City_town_villageDTO;
import com.hartron.investharyana.service.mapper.City_town_villageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing City_town_village.
 */
@Service
public class City_town_villageServiceImpl implements City_town_villageService{

    private final Logger log = LoggerFactory.getLogger(City_town_villageServiceImpl.class);
    
    private final City_town_villageRepository city_town_villageRepository;

    private final City_town_villageMapper city_town_villageMapper;

    public City_town_villageServiceImpl(City_town_villageRepository city_town_villageRepository, City_town_villageMapper city_town_villageMapper) {
        this.city_town_villageRepository = city_town_villageRepository;
        this.city_town_villageMapper = city_town_villageMapper;
    }

    /**
     * Save a city_town_village.
     *
     * @param city_town_villageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public City_town_villageDTO save(City_town_villageDTO city_town_villageDTO) {
        log.debug("Request to save City_town_village : {}", city_town_villageDTO);
        City_town_village city_town_village = city_town_villageMapper.city_town_villageDTOToCity_town_village(city_town_villageDTO);
        city_town_village = city_town_villageRepository.save(city_town_village);
        City_town_villageDTO result = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);
        return result;
    }

    /**
     *  Get all the city_town_villages.
     *  
     *  @return the list of entities
     */
    @Override
    public List<City_town_villageDTO> findAll() {
        log.debug("Request to get all City_town_villages");
        List<City_town_villageDTO> result = city_town_villageRepository.findAll().stream()
            .map(city_town_villageMapper::city_town_villageToCity_town_villageDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one city_town_village by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public City_town_villageDTO findOne(String id) {
        log.debug("Request to get City_town_village : {}", id);
        City_town_village city_town_village = city_town_villageRepository.findOne(UUID.fromString(id));
        City_town_villageDTO city_town_villageDTO = city_town_villageMapper.city_town_villageToCity_town_villageDTO(city_town_village);
        return city_town_villageDTO;
    }

    /**
     *  Delete the  city_town_village by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete City_town_village : {}", id);
        city_town_villageRepository.delete(UUID.fromString(id));
    }
}
