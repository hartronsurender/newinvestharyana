package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Environment_impact_detailDTO;
import java.util.List;

/**
 * Service Interface for managing Environment_impact_detail.
 */
public interface Environment_impact_detailService {

    /**
     * Save a environment_impact_detail.
     *
     * @param environment_impact_detailDTO the entity to save
     * @return the persisted entity
     */
    Environment_impact_detailDTO save(Environment_impact_detailDTO environment_impact_detailDTO);

    /**
     *  Get all the environment_impact_details.
     *  
     *  @return the list of entities
     */
    List<Environment_impact_detailDTO> findAll();

    /**
     *  Get the "id" environment_impact_detail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Environment_impact_detailDTO findOne(String id);

    /**
     *  Delete the "id" environment_impact_detail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
