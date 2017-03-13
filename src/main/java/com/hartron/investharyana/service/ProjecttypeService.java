package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjecttypeDTO;
import java.util.List;

/**
 * Service Interface for managing Projecttype.
 */
public interface ProjecttypeService {

    /**
     * Save a projecttype.
     *
     * @param projecttypeDTO the entity to save
     * @return the persisted entity
     */
    ProjecttypeDTO save(ProjecttypeDTO projecttypeDTO);

    /**
     *  Get all the projecttypes.
     *  
     *  @return the list of entities
     */
    List<ProjecttypeDTO> findAll();

    /**
     *  Get the "id" projecttype.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjecttypeDTO findOne(String id);

    /**
     *  Delete the "id" projecttype.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
