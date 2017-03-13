package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.UnitsService;
import com.hartron.investharyana.domain.Units;
import com.hartron.investharyana.repository.UnitsRepository;
import com.hartron.investharyana.service.dto.UnitsDTO;
import com.hartron.investharyana.service.mapper.UnitsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Units.
 */
@Service
public class UnitsServiceImpl implements UnitsService{

    private final Logger log = LoggerFactory.getLogger(UnitsServiceImpl.class);
    
    private final UnitsRepository unitsRepository;

    private final UnitsMapper unitsMapper;

    public UnitsServiceImpl(UnitsRepository unitsRepository, UnitsMapper unitsMapper) {
        this.unitsRepository = unitsRepository;
        this.unitsMapper = unitsMapper;
    }

    /**
     * Save a units.
     *
     * @param unitsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UnitsDTO save(UnitsDTO unitsDTO) {
        log.debug("Request to save Units : {}", unitsDTO);
        Units units = unitsMapper.unitsDTOToUnits(unitsDTO);
        units = unitsRepository.save(units);
        UnitsDTO result = unitsMapper.unitsToUnitsDTO(units);
        return result;
    }

    /**
     *  Get all the units.
     *  
     *  @return the list of entities
     */
    @Override
    public List<UnitsDTO> findAll() {
        log.debug("Request to get all Units");
        List<UnitsDTO> result = unitsRepository.findAll().stream()
            .map(unitsMapper::unitsToUnitsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one units by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public UnitsDTO findOne(String id) {
        log.debug("Request to get Units : {}", id);
        Units units = unitsRepository.findOne(UUID.fromString(id));
        UnitsDTO unitsDTO = unitsMapper.unitsToUnitsDTO(units);
        return unitsDTO;
    }

    /**
     *  Delete the  units by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Units : {}", id);
        unitsRepository.delete(UUID.fromString(id));
    }
}
