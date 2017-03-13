package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Emission_particularsService;
import com.hartron.investharyana.domain.Emission_particulars;
import com.hartron.investharyana.repository.Emission_particularsRepository;
import com.hartron.investharyana.service.dto.Emission_particularsDTO;
import com.hartron.investharyana.service.mapper.Emission_particularsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emission_particulars.
 */
@Service
public class Emission_particularsServiceImpl implements Emission_particularsService{

    private final Logger log = LoggerFactory.getLogger(Emission_particularsServiceImpl.class);
    
    private final Emission_particularsRepository emission_particularsRepository;

    private final Emission_particularsMapper emission_particularsMapper;

    public Emission_particularsServiceImpl(Emission_particularsRepository emission_particularsRepository, Emission_particularsMapper emission_particularsMapper) {
        this.emission_particularsRepository = emission_particularsRepository;
        this.emission_particularsMapper = emission_particularsMapper;
    }

    /**
     * Save a emission_particulars.
     *
     * @param emission_particularsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Emission_particularsDTO save(Emission_particularsDTO emission_particularsDTO) {
        log.debug("Request to save Emission_particulars : {}", emission_particularsDTO);
        Emission_particulars emission_particulars = emission_particularsMapper.emission_particularsDTOToEmission_particulars(emission_particularsDTO);
        emission_particulars = emission_particularsRepository.save(emission_particulars);
        Emission_particularsDTO result = emission_particularsMapper.emission_particularsToEmission_particularsDTO(emission_particulars);
        return result;
    }

    /**
     *  Get all the emission_particulars.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Emission_particularsDTO> findAll() {
        log.debug("Request to get all Emission_particulars");
        List<Emission_particularsDTO> result = emission_particularsRepository.findAll().stream()
            .map(emission_particularsMapper::emission_particularsToEmission_particularsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one emission_particulars by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Emission_particularsDTO findOne(String id) {
        log.debug("Request to get Emission_particulars : {}", id);
        Emission_particulars emission_particulars = emission_particularsRepository.findOne(UUID.fromString(id));
        Emission_particularsDTO emission_particularsDTO = emission_particularsMapper.emission_particularsToEmission_particularsDTO(emission_particulars);
        return emission_particularsDTO;
    }

    /**
     *  Delete the  emission_particulars by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Emission_particulars : {}", id);
        emission_particularsRepository.delete(UUID.fromString(id));
    }
}
