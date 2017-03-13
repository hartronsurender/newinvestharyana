package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ForeignfundingresourceDTO;
import java.util.List;

/**
 * Service Interface for managing Foreignfundingresource.
 */
public interface ForeignfundingresourceService {

    /**
     * Save a foreignfundingresource.
     *
     * @param foreignfundingresourceDTO the entity to save
     * @return the persisted entity
     */
    ForeignfundingresourceDTO save(ForeignfundingresourceDTO foreignfundingresourceDTO);

    /**
     *  Get all the foreignfundingresources.
     *  
     *  @return the list of entities
     */
    List<ForeignfundingresourceDTO> findAll();

    /**
     *  Get the "id" foreignfundingresource.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ForeignfundingresourceDTO findOne(String id);

    /**
     *  Delete the "id" foreignfundingresource.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
