package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Water_supply_sourceService;
import com.hartron.investharyana.domain.Water_supply_source;
import com.hartron.investharyana.repository.Water_supply_sourceRepository;
import com.hartron.investharyana.service.dto.Water_supply_sourceDTO;
import com.hartron.investharyana.service.mapper.Water_supply_sourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Water_supply_source.
 */
@Service
public class Water_supply_sourceServiceImpl implements Water_supply_sourceService{

    private final Logger log = LoggerFactory.getLogger(Water_supply_sourceServiceImpl.class);
    
    private final Water_supply_sourceRepository water_supply_sourceRepository;

    private final Water_supply_sourceMapper water_supply_sourceMapper;

    public Water_supply_sourceServiceImpl(Water_supply_sourceRepository water_supply_sourceRepository, Water_supply_sourceMapper water_supply_sourceMapper) {
        this.water_supply_sourceRepository = water_supply_sourceRepository;
        this.water_supply_sourceMapper = water_supply_sourceMapper;
    }

    /**
     * Save a water_supply_source.
     *
     * @param water_supply_sourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Water_supply_sourceDTO save(Water_supply_sourceDTO water_supply_sourceDTO) {
        log.debug("Request to save Water_supply_source : {}", water_supply_sourceDTO);
        Water_supply_source water_supply_source = water_supply_sourceMapper.water_supply_sourceDTOToWater_supply_source(water_supply_sourceDTO);
        water_supply_source = water_supply_sourceRepository.save(water_supply_source);
        Water_supply_sourceDTO result = water_supply_sourceMapper.water_supply_sourceToWater_supply_sourceDTO(water_supply_source);
        return result;
    }

    /**
     *  Get all the water_supply_sources.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Water_supply_sourceDTO> findAll() {
        log.debug("Request to get all Water_supply_sources");
        List<Water_supply_sourceDTO> result = water_supply_sourceRepository.findAll().stream()
            .map(water_supply_sourceMapper::water_supply_sourceToWater_supply_sourceDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one water_supply_source by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Water_supply_sourceDTO findOne(String id) {
        log.debug("Request to get Water_supply_source : {}", id);
        Water_supply_source water_supply_source = water_supply_sourceRepository.findOne(UUID.fromString(id));
        Water_supply_sourceDTO water_supply_sourceDTO = water_supply_sourceMapper.water_supply_sourceToWater_supply_sourceDTO(water_supply_source);
        return water_supply_sourceDTO;
    }

    /**
     *  Delete the  water_supply_source by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Water_supply_source : {}", id);
        water_supply_sourceRepository.delete(UUID.fromString(id));
    }
}
