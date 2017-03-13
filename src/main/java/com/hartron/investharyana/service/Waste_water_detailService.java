package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Waste_water_detailDTO;
import java.util.List;

/**
 * Service Interface for managing Waste_water_detail.
 */
public interface Waste_water_detailService {

    /**
     * Save a waste_water_detail.
     *
     * @param waste_water_detailDTO the entity to save
     * @return the persisted entity
     */
    Waste_water_detailDTO save(Waste_water_detailDTO waste_water_detailDTO);

    /**
     *  Get all the waste_water_details.
     *  
     *  @return the list of entities
     */
    List<Waste_water_detailDTO> findAll();

    /**
     *  Get the "id" waste_water_detail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Waste_water_detailDTO findOne(String id);

    /**
     *  Delete the "id" waste_water_detail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
