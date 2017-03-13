package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.EmissiondetailService;
import com.hartron.investharyana.domain.Emissiondetail;
import com.hartron.investharyana.repository.EmissiondetailRepository;
import com.hartron.investharyana.service.dto.EmissiondetailDTO;
import com.hartron.investharyana.service.mapper.EmissiondetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emissiondetail.
 */
@Service
public class EmissiondetailServiceImpl implements EmissiondetailService{

    private final Logger log = LoggerFactory.getLogger(EmissiondetailServiceImpl.class);
    
    private final EmissiondetailRepository emissiondetailRepository;

    private final EmissiondetailMapper emissiondetailMapper;

    public EmissiondetailServiceImpl(EmissiondetailRepository emissiondetailRepository, EmissiondetailMapper emissiondetailMapper) {
        this.emissiondetailRepository = emissiondetailRepository;
        this.emissiondetailMapper = emissiondetailMapper;
    }

    /**
     * Save a emissiondetail.
     *
     * @param emissiondetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmissiondetailDTO save(EmissiondetailDTO emissiondetailDTO) {
        log.debug("Request to save Emissiondetail : {}", emissiondetailDTO);
        Emissiondetail emissiondetail = emissiondetailMapper.emissiondetailDTOToEmissiondetail(emissiondetailDTO);
        emissiondetail = emissiondetailRepository.save(emissiondetail);
        EmissiondetailDTO result = emissiondetailMapper.emissiondetailToEmissiondetailDTO(emissiondetail);
        return result;
    }

    /**
     *  Get all the emissiondetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<EmissiondetailDTO> findAll() {
        log.debug("Request to get all Emissiondetails");
        List<EmissiondetailDTO> result = emissiondetailRepository.findAll().stream()
            .map(emissiondetailMapper::emissiondetailToEmissiondetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one emissiondetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public EmissiondetailDTO findOne(String id) {
        log.debug("Request to get Emissiondetail : {}", id);
        Emissiondetail emissiondetail = emissiondetailRepository.findOne(UUID.fromString(id));
        EmissiondetailDTO emissiondetailDTO = emissiondetailMapper.emissiondetailToEmissiondetailDTO(emissiondetail);
        return emissiondetailDTO;
    }

    /**
     *  Delete the  emissiondetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Emissiondetail : {}", id);
        emissiondetailRepository.delete(UUID.fromString(id));
    }
}
