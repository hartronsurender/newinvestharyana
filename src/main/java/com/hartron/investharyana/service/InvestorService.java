package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.InvestorDTO;
import java.util.List;

/**
 * Service Interface for managing Investor.
 */
public interface InvestorService {

    /**
     * Save a investor.
     *
     * @param investorDTO the entity to save
     * @return the persisted entity
     */
    InvestorDTO save(InvestorDTO investorDTO);

    /**
     *  Get all the investors.
     *  
     *  @return the list of entities
     */
    List<InvestorDTO> findAll();

    /**
     *  Get the "id" investor.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    InvestorDTO findOne(String id);

    /**
     *  Delete the "id" investor.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
