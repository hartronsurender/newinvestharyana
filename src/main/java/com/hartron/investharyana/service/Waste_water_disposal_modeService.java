package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Waste_water_disposal_modeDTO;
import java.util.List;

/**
 * Service Interface for managing Waste_water_disposal_mode.
 */
public interface Waste_water_disposal_modeService {

    /**
     * Save a waste_water_disposal_mode.
     *
     * @param waste_water_disposal_modeDTO the entity to save
     * @return the persisted entity
     */
    Waste_water_disposal_modeDTO save(Waste_water_disposal_modeDTO waste_water_disposal_modeDTO);

    /**
     *  Get all the waste_water_disposal_modes.
     *  
     *  @return the list of entities
     */
    List<Waste_water_disposal_modeDTO> findAll();

    /**
     *  Get the "id" waste_water_disposal_mode.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Waste_water_disposal_modeDTO findOne(String id);

    /**
     *  Delete the "id" waste_water_disposal_mode.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
