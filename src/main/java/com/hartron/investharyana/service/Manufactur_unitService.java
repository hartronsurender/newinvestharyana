package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Manufactur_unitDTO;
import java.util.List;

/**
 * Service Interface for managing Manufactur_unit.
 */
public interface Manufactur_unitService {

    /**
     * Save a manufactur_unit.
     *
     * @param manufactur_unitDTO the entity to save
     * @return the persisted entity
     */
    Manufactur_unitDTO save(Manufactur_unitDTO manufactur_unitDTO);

    /**
     *  Get all the manufactur_units.
     *  
     *  @return the list of entities
     */
    List<Manufactur_unitDTO> findAll();

    /**
     *  Get the "id" manufactur_unit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Manufactur_unitDTO findOne(String id);

    /**
     *  Delete the "id" manufactur_unit.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
