package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.CompanydetailDTO;
import java.util.List;

/**
 * Service Interface for managing Companydetail.
 */
public interface CompanydetailService {

    /**
     * Save a companydetail.
     *
     * @param companydetailDTO the entity to save
     * @return the persisted entity
     */
    CompanydetailDTO save(CompanydetailDTO companydetailDTO);

    /**
     *  Get all the companydetails.
     *  
     *  @return the list of entities
     */
    List<CompanydetailDTO> findAll();

    /**
     *  Get the "id" companydetail.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CompanydetailDTO findOne(String id);

    /**
     *  Delete the "id" companydetail.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
