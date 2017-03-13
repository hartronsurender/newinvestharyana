package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Water_supply_sourceDTO;
import java.util.List;

/**
 * Service Interface for managing Water_supply_source.
 */
public interface Water_supply_sourceService {

    /**
     * Save a water_supply_source.
     *
     * @param water_supply_sourceDTO the entity to save
     * @return the persisted entity
     */
    Water_supply_sourceDTO save(Water_supply_sourceDTO water_supply_sourceDTO);

    /**
     *  Get all the water_supply_sources.
     *  
     *  @return the list of entities
     */
    List<Water_supply_sourceDTO> findAll();

    /**
     *  Get the "id" water_supply_source.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Water_supply_sourceDTO findOne(String id);

    /**
     *  Delete the "id" water_supply_source.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
