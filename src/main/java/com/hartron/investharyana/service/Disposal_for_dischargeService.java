package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Disposal_for_dischargeDTO;
import java.util.List;

/**
 * Service Interface for managing Disposal_for_discharge.
 */
public interface Disposal_for_dischargeService {

    /**
     * Save a disposal_for_discharge.
     *
     * @param disposal_for_dischargeDTO the entity to save
     * @return the persisted entity
     */
    Disposal_for_dischargeDTO save(Disposal_for_dischargeDTO disposal_for_dischargeDTO);

    /**
     *  Get all the disposal_for_discharges.
     *  
     *  @return the list of entities
     */
    List<Disposal_for_dischargeDTO> findAll();

    /**
     *  Get the "id" disposal_for_discharge.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Disposal_for_dischargeDTO findOne(String id);

    /**
     *  Delete the "id" disposal_for_discharge.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
