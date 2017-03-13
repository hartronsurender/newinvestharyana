package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.FinanceinvestmentDTO;
import java.util.List;

/**
 * Service Interface for managing Financeinvestment.
 */
public interface FinanceinvestmentService {

    /**
     * Save a financeinvestment.
     *
     * @param financeinvestmentDTO the entity to save
     * @return the persisted entity
     */
    FinanceinvestmentDTO save(FinanceinvestmentDTO financeinvestmentDTO);

    /**
     *  Get all the financeinvestments.
     *  
     *  @return the list of entities
     */
    List<FinanceinvestmentDTO> findAll();

    /**
     *  Get the "id" financeinvestment.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FinanceinvestmentDTO findOne(String id);

    /**
     *  Delete the "id" financeinvestment.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
