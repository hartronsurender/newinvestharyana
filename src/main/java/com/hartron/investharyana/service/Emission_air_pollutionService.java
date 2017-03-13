package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Emission_air_pollutionDTO;
import java.util.List;

/**
 * Service Interface for managing Emission_air_pollution.
 */
public interface Emission_air_pollutionService {

    /**
     * Save a emission_air_pollution.
     *
     * @param emission_air_pollutionDTO the entity to save
     * @return the persisted entity
     */
    Emission_air_pollutionDTO save(Emission_air_pollutionDTO emission_air_pollutionDTO);

    /**
     *  Get all the emission_air_pollutions.
     *  
     *  @return the list of entities
     */
    List<Emission_air_pollutionDTO> findAll();

    /**
     *  Get the "id" emission_air_pollution.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Emission_air_pollutionDTO findOne(String id);

    /**
     *  Delete the "id" emission_air_pollution.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
