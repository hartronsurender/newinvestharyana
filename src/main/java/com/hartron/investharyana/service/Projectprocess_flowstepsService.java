package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Projectprocess_flowstepsDTO;
import java.util.List;

/**
 * Service Interface for managing Projectprocess_flowsteps.
 */
public interface Projectprocess_flowstepsService {

    /**
     * Save a projectprocess_flowsteps.
     *
     * @param projectprocess_flowstepsDTO the entity to save
     * @return the persisted entity
     */
    Projectprocess_flowstepsDTO save(Projectprocess_flowstepsDTO projectprocess_flowstepsDTO);

    /**
     *  Get all the projectprocess_flowsteps.
     *  
     *  @return the list of entities
     */
    List<Projectprocess_flowstepsDTO> findAll();

    /**
     *  Get the "id" projectprocess_flowsteps.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Projectprocess_flowstepsDTO findOne(String id);

    /**
     *  Delete the "id" projectprocess_flowsteps.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
