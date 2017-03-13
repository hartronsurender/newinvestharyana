package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectcategoryDTO;
import java.util.List;

/**
 * Service Interface for managing Projectcategory.
 */
public interface ProjectcategoryService {

    /**
     * Save a projectcategory.
     *
     * @param projectcategoryDTO the entity to save
     * @return the persisted entity
     */
    ProjectcategoryDTO save(ProjectcategoryDTO projectcategoryDTO);

    /**
     *  Get all the projectcategories.
     *  
     *  @return the list of entities
     */
    List<ProjectcategoryDTO> findAll();

    /**
     *  Get the "id" projectcategory.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectcategoryDTO findOne(String id);

    /**
     *  Delete the "id" projectcategory.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
