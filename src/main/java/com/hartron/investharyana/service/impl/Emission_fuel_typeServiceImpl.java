package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Emission_fuel_typeService;
import com.hartron.investharyana.domain.Emission_fuel_type;
import com.hartron.investharyana.repository.Emission_fuel_typeRepository;
import com.hartron.investharyana.service.dto.Emission_fuel_typeDTO;
import com.hartron.investharyana.service.mapper.Emission_fuel_typeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Emission_fuel_type.
 */
@Service
public class Emission_fuel_typeServiceImpl implements Emission_fuel_typeService{

    private final Logger log = LoggerFactory.getLogger(Emission_fuel_typeServiceImpl.class);
    
    private final Emission_fuel_typeRepository emission_fuel_typeRepository;

    private final Emission_fuel_typeMapper emission_fuel_typeMapper;

    public Emission_fuel_typeServiceImpl(Emission_fuel_typeRepository emission_fuel_typeRepository, Emission_fuel_typeMapper emission_fuel_typeMapper) {
        this.emission_fuel_typeRepository = emission_fuel_typeRepository;
        this.emission_fuel_typeMapper = emission_fuel_typeMapper;
    }

    /**
     * Save a emission_fuel_type.
     *
     * @param emission_fuel_typeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Emission_fuel_typeDTO save(Emission_fuel_typeDTO emission_fuel_typeDTO) {
        log.debug("Request to save Emission_fuel_type : {}", emission_fuel_typeDTO);
        Emission_fuel_type emission_fuel_type = emission_fuel_typeMapper.emission_fuel_typeDTOToEmission_fuel_type(emission_fuel_typeDTO);
        emission_fuel_type = emission_fuel_typeRepository.save(emission_fuel_type);
        Emission_fuel_typeDTO result = emission_fuel_typeMapper.emission_fuel_typeToEmission_fuel_typeDTO(emission_fuel_type);
        return result;
    }

    /**
     *  Get all the emission_fuel_types.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Emission_fuel_typeDTO> findAll() {
        log.debug("Request to get all Emission_fuel_types");
        List<Emission_fuel_typeDTO> result = emission_fuel_typeRepository.findAll().stream()
            .map(emission_fuel_typeMapper::emission_fuel_typeToEmission_fuel_typeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one emission_fuel_type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Emission_fuel_typeDTO findOne(String id) {
        log.debug("Request to get Emission_fuel_type : {}", id);
        Emission_fuel_type emission_fuel_type = emission_fuel_typeRepository.findOne(UUID.fromString(id));
        Emission_fuel_typeDTO emission_fuel_typeDTO = emission_fuel_typeMapper.emission_fuel_typeToEmission_fuel_typeDTO(emission_fuel_type);
        return emission_fuel_typeDTO;
    }

    /**
     *  Delete the  emission_fuel_type by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Emission_fuel_type : {}", id);
        emission_fuel_typeRepository.delete(UUID.fromString(id));
    }
}
