package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Emission_particularsDTO;
import java.util.List;

/**
 * Service Interface for managing Emission_particulars.
 */
public interface Emission_particularsService {

    /**
     * Save a emission_particulars.
     *
     * @param emission_particularsDTO the entity to save
     * @return the persisted entity
     */
    Emission_particularsDTO save(Emission_particularsDTO emission_particularsDTO);

    /**
     *  Get all the emission_particulars.
     *  
     *  @return the list of entities
     */
    List<Emission_particularsDTO> findAll();

    /**
     *  Get the "id" emission_particulars.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Emission_particularsDTO findOne(String id);

    /**
     *  Delete the "id" emission_particulars.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
