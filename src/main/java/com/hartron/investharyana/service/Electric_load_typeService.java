package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Electric_load_typeDTO;
import java.util.List;

/**
 * Service Interface for managing Electric_load_type.
 */
public interface Electric_load_typeService {

    /**
     * Save a electric_load_type.
     *
     * @param electric_load_typeDTO the entity to save
     * @return the persisted entity
     */
    Electric_load_typeDTO save(Electric_load_typeDTO electric_load_typeDTO);

    /**
     *  Get all the electric_load_types.
     *  
     *  @return the list of entities
     */
    List<Electric_load_typeDTO> findAll();

    /**
     *  Get the "id" electric_load_type.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Electric_load_typeDTO findOne(String id);

    /**
     *  Delete the "id" electric_load_type.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
