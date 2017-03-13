package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Emission_air_pollutionService;
import com.hartron.investharyana.domain.Emission_air_pollution;
import com.hartron.investharyana.repository.Emission_air_pollutionRepository;
import com.hartron.investharyana.service.dto.Emission_air_pollutionDTO;
import com.hartron.investharyana.service.mapper.Emission_air_pollutionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emission_air_pollution.
 */
@Service
public class Emission_air_pollutionServiceImpl implements Emission_air_pollutionService{

    private final Logger log = LoggerFactory.getLogger(Emission_air_pollutionServiceImpl.class);
    
    private final Emission_air_pollutionRepository emission_air_pollutionRepository;

    private final Emission_air_pollutionMapper emission_air_pollutionMapper;

    public Emission_air_pollutionServiceImpl(Emission_air_pollutionRepository emission_air_pollutionRepository, Emission_air_pollutionMapper emission_air_pollutionMapper) {
        this.emission_air_pollutionRepository = emission_air_pollutionRepository;
        this.emission_air_pollutionMapper = emission_air_pollutionMapper;
    }

    /**
     * Save a emission_air_pollution.
     *
     * @param emission_air_pollutionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Emission_air_pollutionDTO save(Emission_air_pollutionDTO emission_air_pollutionDTO) {
        log.debug("Request to save Emission_air_pollution : {}", emission_air_pollutionDTO);
        Emission_air_pollution emission_air_pollution = emission_air_pollutionMapper.emission_air_pollutionDTOToEmission_air_pollution(emission_air_pollutionDTO);
        emission_air_pollution = emission_air_pollutionRepository.save(emission_air_pollution);
        Emission_air_pollutionDTO result = emission_air_pollutionMapper.emission_air_pollutionToEmission_air_pollutionDTO(emission_air_pollution);
        return result;
    }

    /**
     *  Get all the emission_air_pollutions.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Emission_air_pollutionDTO> findAll() {
        log.debug("Request to get all Emission_air_pollutions");
        List<Emission_air_pollutionDTO> result = emission_air_pollutionRepository.findAll().stream()
            .map(emission_air_pollutionMapper::emission_air_pollutionToEmission_air_pollutionDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one emission_air_pollution by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Emission_air_pollutionDTO findOne(String id) {
        log.debug("Request to get Emission_air_pollution : {}", id);
        Emission_air_pollution emission_air_pollution = emission_air_pollutionRepository.findOne(UUID.fromString(id));
        Emission_air_pollutionDTO emission_air_pollutionDTO = emission_air_pollutionMapper.emission_air_pollutionToEmission_air_pollutionDTO(emission_air_pollution);
        return emission_air_pollutionDTO;
    }

    /**
     *  Delete the  emission_air_pollution by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Emission_air_pollution : {}", id);
        emission_air_pollutionRepository.delete(UUID.fromString(id));
    }
}
