package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Accept_verificationService;
import com.hartron.investharyana.domain.Accept_verification;
import com.hartron.investharyana.repository.Accept_verificationRepository;
import com.hartron.investharyana.service.dto.Accept_verificationDTO;
import com.hartron.investharyana.service.mapper.Accept_verificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Accept_verification.
 */
@Service
public class Accept_verificationServiceImpl implements Accept_verificationService{

    private final Logger log = LoggerFactory.getLogger(Accept_verificationServiceImpl.class);
    
    private final Accept_verificationRepository accept_verificationRepository;

    private final Accept_verificationMapper accept_verificationMapper;

    public Accept_verificationServiceImpl(Accept_verificationRepository accept_verificationRepository, Accept_verificationMapper accept_verificationMapper) {
        this.accept_verificationRepository = accept_verificationRepository;
        this.accept_verificationMapper = accept_verificationMapper;
    }

    /**
     * Save a accept_verification.
     *
     * @param accept_verificationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Accept_verificationDTO save(Accept_verificationDTO accept_verificationDTO) {
        log.debug("Request to save Accept_verification : {}", accept_verificationDTO);
        Accept_verification accept_verification = accept_verificationMapper.accept_verificationDTOToAccept_verification(accept_verificationDTO);
        accept_verification = accept_verificationRepository.save(accept_verification);
        Accept_verificationDTO result = accept_verificationMapper.accept_verificationToAccept_verificationDTO(accept_verification);
        return result;
    }

    /**
     *  Get all the accept_verifications.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Accept_verificationDTO> findAll() {
        log.debug("Request to get all Accept_verifications");
        List<Accept_verificationDTO> result = accept_verificationRepository.findAll().stream()
            .map(accept_verificationMapper::accept_verificationToAccept_verificationDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one accept_verification by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Accept_verificationDTO findOne(String id) {
        log.debug("Request to get Accept_verification : {}", id);
        Accept_verification accept_verification = accept_verificationRepository.findOne(UUID.fromString(id));
        Accept_verificationDTO accept_verificationDTO = accept_verificationMapper.accept_verificationToAccept_verificationDTO(accept_verification);
        return accept_verificationDTO;
    }

    /**
     *  Delete the  accept_verification by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Accept_verification : {}", id);
        accept_verificationRepository.delete(UUID.fromString(id));
    }
}
