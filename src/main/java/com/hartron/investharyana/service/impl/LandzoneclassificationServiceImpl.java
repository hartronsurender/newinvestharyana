package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.LandzoneclassificationService;
import com.hartron.investharyana.domain.Landzoneclassification;
import com.hartron.investharyana.repository.LandzoneclassificationRepository;
import com.hartron.investharyana.service.dto.LandzoneclassificationDTO;
import com.hartron.investharyana.service.mapper.LandzoneclassificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Landzoneclassification.
 */
@Service
public class LandzoneclassificationServiceImpl implements LandzoneclassificationService{

    private final Logger log = LoggerFactory.getLogger(LandzoneclassificationServiceImpl.class);
    
    private final LandzoneclassificationRepository landzoneclassificationRepository;

    private final LandzoneclassificationMapper landzoneclassificationMapper;

    public LandzoneclassificationServiceImpl(LandzoneclassificationRepository landzoneclassificationRepository, LandzoneclassificationMapper landzoneclassificationMapper) {
        this.landzoneclassificationRepository = landzoneclassificationRepository;
        this.landzoneclassificationMapper = landzoneclassificationMapper;
    }

    /**
     * Save a landzoneclassification.
     *
     * @param landzoneclassificationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LandzoneclassificationDTO save(LandzoneclassificationDTO landzoneclassificationDTO) {
        log.debug("Request to save Landzoneclassification : {}", landzoneclassificationDTO);
        Landzoneclassification landzoneclassification = landzoneclassificationMapper.landzoneclassificationDTOToLandzoneclassification(landzoneclassificationDTO);
        landzoneclassification = landzoneclassificationRepository.save(landzoneclassification);
        LandzoneclassificationDTO result = landzoneclassificationMapper.landzoneclassificationToLandzoneclassificationDTO(landzoneclassification);
        return result;
    }

    /**
     *  Get all the landzoneclassifications.
     *  
     *  @return the list of entities
     */
    @Override
    public List<LandzoneclassificationDTO> findAll() {
        log.debug("Request to get all Landzoneclassifications");
        List<LandzoneclassificationDTO> result = landzoneclassificationRepository.findAll().stream()
            .map(landzoneclassificationMapper::landzoneclassificationToLandzoneclassificationDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one landzoneclassification by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public LandzoneclassificationDTO findOne(String id) {
        log.debug("Request to get Landzoneclassification : {}", id);
        Landzoneclassification landzoneclassification = landzoneclassificationRepository.findOne(UUID.fromString(id));
        LandzoneclassificationDTO landzoneclassificationDTO = landzoneclassificationMapper.landzoneclassificationToLandzoneclassificationDTO(landzoneclassification);
        return landzoneclassificationDTO;
    }

    /**
     *  Delete the  landzoneclassification by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Landzoneclassification : {}", id);
        landzoneclassificationRepository.delete(UUID.fromString(id));
    }
}
