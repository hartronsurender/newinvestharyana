package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectrawmaterialDTO;
import java.util.List;

/**
 * Service Interface for managing Projectrawmaterial.
 */
public interface ProjectrawmaterialService {

    /**
     * Save a projectrawmaterial.
     *
     * @param projectrawmaterialDTO the entity to save
     * @return the persisted entity
     */
    ProjectrawmaterialDTO save(ProjectrawmaterialDTO projectrawmaterialDTO);

    /**
     *  Get all the projectrawmaterials.
     *  
     *  @return the list of entities
     */
    List<ProjectrawmaterialDTO> findAll();

    /**
     *  Get the "id" projectrawmaterial.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectrawmaterialDTO findOne(String id);

    /**
     *  Delete the "id" projectrawmaterial.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
