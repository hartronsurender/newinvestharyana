package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Tehsil_subtehsilDTO;
import java.util.List;

/**
 * Service Interface for managing Tehsil_subtehsil.
 */
public interface Tehsil_subtehsilService {

    /**
     * Save a tehsil_subtehsil.
     *
     * @param tehsil_subtehsilDTO the entity to save
     * @return the persisted entity
     */
    Tehsil_subtehsilDTO save(Tehsil_subtehsilDTO tehsil_subtehsilDTO);

    /**
     *  Get all the tehsil_subtehsils.
     *  
     *  @return the list of entities
     */
    List<Tehsil_subtehsilDTO> findAll();

    /**
     *  Get the "id" tehsil_subtehsil.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Tehsil_subtehsilDTO findOne(String id);

    /**
     *  Delete the "id" tehsil_subtehsil.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
