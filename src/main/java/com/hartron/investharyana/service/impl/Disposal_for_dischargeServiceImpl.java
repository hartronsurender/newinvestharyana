package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Disposal_for_dischargeService;
import com.hartron.investharyana.domain.Disposal_for_discharge;
import com.hartron.investharyana.repository.Disposal_for_dischargeRepository;
import com.hartron.investharyana.service.dto.Disposal_for_dischargeDTO;
import com.hartron.investharyana.service.mapper.Disposal_for_dischargeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Disposal_for_discharge.
 */
@Service
public class Disposal_for_dischargeServiceImpl implements Disposal_for_dischargeService{

    private final Logger log = LoggerFactory.getLogger(Disposal_for_dischargeServiceImpl.class);
    
    private final Disposal_for_dischargeRepository disposal_for_dischargeRepository;

    private final Disposal_for_dischargeMapper disposal_for_dischargeMapper;

    public Disposal_for_dischargeServiceImpl(Disposal_for_dischargeRepository disposal_for_dischargeRepository, Disposal_for_dischargeMapper disposal_for_dischargeMapper) {
        this.disposal_for_dischargeRepository = disposal_for_dischargeRepository;
        this.disposal_for_dischargeMapper = disposal_for_dischargeMapper;
    }

    /**
     * Save a disposal_for_discharge.
     *
     * @param disposal_for_dischargeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Disposal_for_dischargeDTO save(Disposal_for_dischargeDTO disposal_for_dischargeDTO) {
        log.debug("Request to save Disposal_for_discharge : {}", disposal_for_dischargeDTO);
        Disposal_for_discharge disposal_for_discharge = disposal_for_dischargeMapper.disposal_for_dischargeDTOToDisposal_for_discharge(disposal_for_dischargeDTO);
        disposal_for_discharge = disposal_for_dischargeRepository.save(disposal_for_discharge);
        Disposal_for_dischargeDTO result = disposal_for_dischargeMapper.disposal_for_dischargeToDisposal_for_dischargeDTO(disposal_for_discharge);
        return result;
    }

    /**
     *  Get all the disposal_for_discharges.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Disposal_for_dischargeDTO> findAll() {
        log.debug("Request to get all Disposal_for_discharges");
        List<Disposal_for_dischargeDTO> result = disposal_for_dischargeRepository.findAll().stream()
            .map(disposal_for_dischargeMapper::disposal_for_dischargeToDisposal_for_dischargeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one disposal_for_discharge by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Disposal_for_dischargeDTO findOne(String id) {
        log.debug("Request to get Disposal_for_discharge : {}", id);
        Disposal_for_discharge disposal_for_discharge = disposal_for_dischargeRepository.findOne(UUID.fromString(id));
        Disposal_for_dischargeDTO disposal_for_dischargeDTO = disposal_for_dischargeMapper.disposal_for_dischargeToDisposal_for_dischargeDTO(disposal_for_discharge);
        return disposal_for_dischargeDTO;
    }

    /**
     *  Delete the  disposal_for_discharge by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Disposal_for_discharge : {}", id);
        disposal_for_dischargeRepository.delete(UUID.fromString(id));
    }
}
