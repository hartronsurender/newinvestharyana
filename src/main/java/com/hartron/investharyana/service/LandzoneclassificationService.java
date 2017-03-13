package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.LandzoneclassificationDTO;
import java.util.List;

/**
 * Service Interface for managing Landzoneclassification.
 */
public interface LandzoneclassificationService {

    /**
     * Save a landzoneclassification.
     *
     * @param landzoneclassificationDTO the entity to save
     * @return the persisted entity
     */
    LandzoneclassificationDTO save(LandzoneclassificationDTO landzoneclassificationDTO);

    /**
     *  Get all the landzoneclassifications.
     *  
     *  @return the list of entities
     */
    List<LandzoneclassificationDTO> findAll();

    /**
     *  Get the "id" landzoneclassification.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    LandzoneclassificationDTO findOne(String id);

    /**
     *  Delete the "id" landzoneclassification.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
