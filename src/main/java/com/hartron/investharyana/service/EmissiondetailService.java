package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.EmissiondetailDTO;
import java.util.List;

/**
 * Service Interface for managing Emissiondetail.
 */
public interface EmissiondetailService {

    /**
     * Save a emissiondetail.
     *
     * @param emissiondetailDTO the entity to save
     * @return the persisted entity
     */
    EmissiondetailDTO save(EmissiondetailDTO emissiondetailDTO);

    /**
     *  Get all the emissiondetails.
     *  
     *  @return the list of entities
     */
    List<EmissiondetailDTO> findAll();

    /**
     *  Get the "id" emissiondetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EmissiondetailDTO findOne(String id);

    /**
     *  Delete the "id" emissiondetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
