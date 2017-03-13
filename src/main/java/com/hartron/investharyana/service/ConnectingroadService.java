package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ConnectingroadDTO;
import java.util.List;

/**
 * Service Interface for managing Connectingroad.
 */
public interface ConnectingroadService {

    /**
     * Save a connectingroad.
     *
     * @param connectingroadDTO the entity to save
     * @return the persisted entity
     */
    ConnectingroadDTO save(ConnectingroadDTO connectingroadDTO);

    /**
     *  Get all the connectingroads.
     *  
     *  @return the list of entities
     */
    List<ConnectingroadDTO> findAll();

    /**
     *  Get the "id" connectingroad.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ConnectingroadDTO findOne(String id);

    /**
     *  Delete the "id" connectingroad.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
