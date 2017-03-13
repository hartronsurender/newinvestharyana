package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Project_electricity_detailDTO;
import java.util.List;

/**
 * Service Interface for managing Project_electricity_detail.
 */
public interface Project_electricity_detailService {

    /**
     * Save a project_electricity_detail.
     *
     * @param project_electricity_detailDTO the entity to save
     * @return the persisted entity
     */
    Project_electricity_detailDTO save(Project_electricity_detailDTO project_electricity_detailDTO);

    /**
     *  Get all the project_electricity_details.
     *  
     *  @return the list of entities
     */
    List<Project_electricity_detailDTO> findAll();

    /**
     *  Get the "id" project_electricity_detail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Project_electricity_detailDTO findOne(String id);

    /**
     *  Delete the "id" project_electricity_detail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
