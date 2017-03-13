package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.DistrictDTO;
import java.util.List;

/**
 * Service Interface for managing District.
 */
public interface DistrictService {

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    DistrictDTO save(DistrictDTO districtDTO);

    /**
     *  Get all the districts.
     *  
     *  @return the list of entities
     */
    List<DistrictDTO> findAll();

    /**
     *  Get the "id" district.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DistrictDTO findOne(String id);

    /**
     *  Delete the "id" district.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
