package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProjectdetailDTO;
import java.util.List;

/**
 * Service Interface for managing Projectdetail.
 */
public interface ProjectdetailService {

    /**
     * Save a projectdetail.
     *
     * @param projectdetailDTO the entity to save
     * @return the persisted entity
     */
    ProjectdetailDTO save(ProjectdetailDTO projectdetailDTO);

    /**
     *  Get all the projectdetails.
     *  
     *  @return the list of entities
     */
    List<ProjectdetailDTO> findAll();

    /**
     *  Get the "id" projectdetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProjectdetailDTO findOne(String id);

    /**
     *  Delete the "id" projectdetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
