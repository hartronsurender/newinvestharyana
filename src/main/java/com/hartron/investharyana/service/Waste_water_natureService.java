package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Waste_water_natureDTO;
import java.util.List;

/**
 * Service Interface for managing Waste_water_nature.
 */
public interface Waste_water_natureService {

    /**
     * Save a waste_water_nature.
     *
     * @param waste_water_natureDTO the entity to save
     * @return the persisted entity
     */
    Waste_water_natureDTO save(Waste_water_natureDTO waste_water_natureDTO);

    /**
     *  Get all the waste_water_natures.
     *  
     *  @return the list of entities
     */
    List<Waste_water_natureDTO> findAll();

    /**
     *  Get the "id" waste_water_nature.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Waste_water_natureDTO findOne(String id);

    /**
     *  Delete the "id" waste_water_nature.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
