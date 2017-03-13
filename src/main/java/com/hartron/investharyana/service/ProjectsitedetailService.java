package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectsitedetailDTO;
import java.util.List;

/**
 * Service Interface for managing Projectsitedetail.
 */
public interface ProjectsitedetailService {

    /**
     * Save a projectsitedetail.
     *
     * @param projectsitedetailDTO the entity to save
     * @return the persisted entity
     */
    ProjectsitedetailDTO save(ProjectsitedetailDTO projectsitedetailDTO);

    /**
     *  Get all the projectsitedetails.
     *  
     *  @return the list of entities
     */
    List<ProjectsitedetailDTO> findAll();

    /**
     *  Get the "id" projectsitedetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectsitedetailDTO findOne(String id);

    /**
     *  Delete the "id" projectsitedetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
