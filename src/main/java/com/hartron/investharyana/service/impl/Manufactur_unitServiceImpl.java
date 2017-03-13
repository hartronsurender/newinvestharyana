package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Manufactur_unitService;
import com.hartron.investharyana.domain.Manufactur_unit;
import com.hartron.investharyana.repository.Manufactur_unitRepository;
import com.hartron.investharyana.service.dto.Manufactur_unitDTO;
import com.hartron.investharyana.service.mapper.Manufactur_unitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Manufactur_unit.
 */
@Service
public class Manufactur_unitServiceImpl implements Manufactur_unitService{

    private final Logger log = LoggerFactory.getLogger(Manufactur_unitServiceImpl.class);
    
    private final Manufactur_unitRepository manufactur_unitRepository;

    private final Manufactur_unitMapper manufactur_unitMapper;

    public Manufactur_unitServiceImpl(Manufactur_unitRepository manufactur_unitRepository, Manufactur_unitMapper manufactur_unitMapper) {
        this.manufactur_unitRepository = manufactur_unitRepository;
        this.manufactur_unitMapper = manufactur_unitMapper;
    }

    /**
     * Save a manufactur_unit.
     *
     * @param manufactur_unitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Manufactur_unitDTO save(Manufactur_unitDTO manufactur_unitDTO) {
        log.debug("Request to save Manufactur_unit : {}", manufactur_unitDTO);
        Manufactur_unit manufactur_unit = manufactur_unitMapper.manufactur_unitDTOToManufactur_unit(manufactur_unitDTO);
        manufactur_unit = manufactur_unitRepository.save(manufactur_unit);
        Manufactur_unitDTO result = manufactur_unitMapper.manufactur_unitToManufactur_unitDTO(manufactur_unit);
        return result;
    }

    /**
     *  Get all the manufactur_units.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Manufactur_unitDTO> findAll() {
        log.debug("Request to get all Manufactur_units");
        List<Manufactur_unitDTO> result = manufactur_unitRepository.findAll().stream()
            .map(manufactur_unitMapper::manufactur_unitToManufactur_unitDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one manufactur_unit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Manufactur_unitDTO findOne(String id) {
        log.debug("Request to get Manufactur_unit : {}", id);
        Manufactur_unit manufactur_unit = manufactur_unitRepository.findOne(UUID.fromString(id));
        Manufactur_unitDTO manufactur_unitDTO = manufactur_unitMapper.manufactur_unitToManufactur_unitDTO(manufactur_unit);
        return manufactur_unitDTO;
    }

    /**
     *  Delete the  manufactur_unit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Manufactur_unit : {}", id);
        manufactur_unitRepository.delete(UUID.fromString(id));
    }
}
