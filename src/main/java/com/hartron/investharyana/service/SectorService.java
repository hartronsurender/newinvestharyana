package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.SectorDTO;
import java.util.List;

/**
 * Service Interface for managing Sector.
 */
public interface SectorService {

    /**
     * Save a sector.
     *
     * @param sectorDTO the entity to save
     * @return the persisted entity
     */
    SectorDTO save(SectorDTO sectorDTO);

    /**
     *  Get all the sectors.
     *  
     *  @return the list of entities
     */
    List<SectorDTO> findAll();

    /**
     *  Get the "id" sector.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SectorDTO findOne(String id);

    /**
     *  Delete the "id" sector.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
