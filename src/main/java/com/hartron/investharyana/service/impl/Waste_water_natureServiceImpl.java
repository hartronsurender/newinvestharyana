package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Waste_water_natureService;
import com.hartron.investharyana.domain.Waste_water_nature;
import com.hartron.investharyana.repository.Waste_water_natureRepository;
import com.hartron.investharyana.service.dto.Waste_water_natureDTO;
import com.hartron.investharyana.service.mapper.Waste_water_natureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Waste_water_nature.
 */
@Service
public class Waste_water_natureServiceImpl implements Waste_water_natureService{

    private final Logger log = LoggerFactory.getLogger(Waste_water_natureServiceImpl.class);
    
    private final Waste_water_natureRepository waste_water_natureRepository;

    private final Waste_water_natureMapper waste_water_natureMapper;

    public Waste_water_natureServiceImpl(Waste_water_natureRepository waste_water_natureRepository, Waste_water_natureMapper waste_water_natureMapper) {
        this.waste_water_natureRepository = waste_water_natureRepository;
        this.waste_water_natureMapper = waste_water_natureMapper;
    }

    /**
     * Save a waste_water_nature.
     *
     * @param waste_water_natureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Waste_water_natureDTO save(Waste_water_natureDTO waste_water_natureDTO) {
        log.debug("Request to save Waste_water_nature : {}", waste_water_natureDTO);
        Waste_water_nature waste_water_nature = waste_water_natureMapper.waste_water_natureDTOToWaste_water_nature(waste_water_natureDTO);
        waste_water_nature = waste_water_natureRepository.save(waste_water_nature);
        Waste_water_natureDTO result = waste_water_natureMapper.waste_water_natureToWaste_water_natureDTO(waste_water_nature);
        return result;
    }

    /**
     *  Get all the waste_water_natures.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Waste_water_natureDTO> findAll() {
        log.debug("Request to get all Waste_water_natures");
        List<Waste_water_natureDTO> result = waste_water_natureRepository.findAll().stream()
            .map(waste_water_natureMapper::waste_water_natureToWaste_water_natureDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one waste_water_nature by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Waste_water_natureDTO findOne(String id) {
        log.debug("Request to get Waste_water_nature : {}", id);
        Waste_water_nature waste_water_nature = waste_water_natureRepository.findOne(UUID.fromString(id));
        Waste_water_natureDTO waste_water_natureDTO = waste_water_natureMapper.waste_water_natureToWaste_water_natureDTO(waste_water_nature);
        return waste_water_natureDTO;
    }

    /**
     *  Delete the  waste_water_nature by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Waste_water_nature : {}", id);
        waste_water_natureRepository.delete(UUID.fromString(id));
    }
}
