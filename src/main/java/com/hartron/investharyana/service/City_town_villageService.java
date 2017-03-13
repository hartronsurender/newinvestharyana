package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.City_town_villageDTO;
import java.util.List;

/**
 * Service Interface for managing City_town_village.
 */
public interface City_town_villageService {

    /**
     * Save a city_town_village.
     *
     * @param city_town_villageDTO the entity to save
     * @return the persisted entity
     */
    City_town_villageDTO save(City_town_villageDTO city_town_villageDTO);

    /**
     *  Get all the city_town_villages.
     *  
     *  @return the list of entities
     */
    List<City_town_villageDTO> findAll();

    /**
     *  Get the "id" city_town_village.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    City_town_villageDTO findOne(String id);

    /**
     *  Delete the "id" city_town_village.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
