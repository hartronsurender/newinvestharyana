package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.Accept_verificationDTO;
import java.util.List;

/**
 * Service Interface for managing Accept_verification.
 */
public interface Accept_verificationService {

    /**
     * Save a accept_verification.
     *
     * @param accept_verificationDTO the entity to save
     * @return the persisted entity
     */
    Accept_verificationDTO save(Accept_verificationDTO accept_verificationDTO);

    /**
     *  Get all the accept_verifications.
     *  
     *  @return the list of entities
     */
    List<Accept_verificationDTO> findAll();

    /**
     *  Get the "id" accept_verification.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Accept_verificationDTO findOne(String id);

    /**
     *  Delete the "id" accept_verification.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
