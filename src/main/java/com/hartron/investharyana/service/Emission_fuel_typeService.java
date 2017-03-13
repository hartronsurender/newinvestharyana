package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Emission_fuel_typeDTO;
import java.util.List;

/**
 * Service Interface for managing Emission_fuel_type.
 */
public interface Emission_fuel_typeService {

    /**
     * Save a emission_fuel_type.
     *
     * @param emission_fuel_typeDTO the entity to save
     * @return the persisted entity
     */
    Emission_fuel_typeDTO save(Emission_fuel_typeDTO emission_fuel_typeDTO);

    /**
     *  Get all the emission_fuel_types.
     *  
     *  @return the list of entities
     */
    List<Emission_fuel_typeDTO> findAll();

    /**
     *  Get the "id" emission_fuel_type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Emission_fuel_typeDTO findOne(String id);

    /**
     *  Delete the "id" emission_fuel_type.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
