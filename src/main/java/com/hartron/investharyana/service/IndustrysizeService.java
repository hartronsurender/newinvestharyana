package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.IndustrysizeDTO;
import java.util.List;

/**
 * Service Interface for managing Industrysize.
 */
public interface IndustrysizeService {

    /**
     * Save a industrysize.
     *
     * @param industrysizeDTO the entity to save
     * @return the persisted entity
     */
    IndustrysizeDTO save(IndustrysizeDTO industrysizeDTO);

    /**
     *  Get all the industrysizes.
     *  
     *  @return the list of entities
     */
    List<IndustrysizeDTO> findAll();

    /**
     *  Get the "id" industrysize.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    IndustrysizeDTO findOne(String id);

    /**
     *  Delete the "id" industrysize.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
