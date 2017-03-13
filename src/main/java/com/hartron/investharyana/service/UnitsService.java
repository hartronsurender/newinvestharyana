package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.UnitsDTO;
import java.util.List;

/**
 * Service Interface for managing Units.
 */
public interface UnitsService {

    /**
     * Save a units.
     *
     * @param unitsDTO the entity to save
     * @return the persisted entity
     */
    UnitsDTO save(UnitsDTO unitsDTO);

    /**
     *  Get all the units.
     *  
     *  @return the list of entities
     */
    List<UnitsDTO> findAll();

    /**
     *  Get the "id" units.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    UnitsDTO findOne(String id);

    /**
     *  Delete the "id" units.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
