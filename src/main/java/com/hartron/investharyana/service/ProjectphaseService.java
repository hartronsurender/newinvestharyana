package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectphaseDTO;
import java.util.List;

/**
 * Service Interface for managing Projectphase.
 */
public interface ProjectphaseService {

    /**
     * Save a projectphase.
     *
     * @param projectphaseDTO the entity to save
     * @return the persisted entity
     */
    ProjectphaseDTO save(ProjectphaseDTO projectphaseDTO);

    /**
     *  Get all the projectphases.
     *  
     *  @return the list of entities
     */
    List<ProjectphaseDTO> findAll();

    /**
     *  Get the "id" projectphase.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectphaseDTO findOne(String id);

    /**
     *  Delete the "id" projectphase.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
