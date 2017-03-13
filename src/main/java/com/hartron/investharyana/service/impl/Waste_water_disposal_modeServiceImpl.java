package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Waste_water_disposal_modeService;
import com.hartron.investharyana.domain.Waste_water_disposal_mode;
import com.hartron.investharyana.repository.Waste_water_disposal_modeRepository;
import com.hartron.investharyana.service.dto.Waste_water_disposal_modeDTO;
import com.hartron.investharyana.service.mapper.Waste_water_disposal_modeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Waste_water_disposal_mode.
 */
@Service
public class Waste_water_disposal_modeServiceImpl implements Waste_water_disposal_modeService{

    private final Logger log = LoggerFactory.getLogger(Waste_water_disposal_modeServiceImpl.class);
    
    private final Waste_water_disposal_modeRepository waste_water_disposal_modeRepository;

    private final Waste_water_disposal_modeMapper waste_water_disposal_modeMapper;

    public Waste_water_disposal_modeServiceImpl(Waste_water_disposal_modeRepository waste_water_disposal_modeRepository, Waste_water_disposal_modeMapper waste_water_disposal_modeMapper) {
        this.waste_water_disposal_modeRepository = waste_water_disposal_modeRepository;
        this.waste_water_disposal_modeMapper = waste_water_disposal_modeMapper;
    }

    /**
     * Save a waste_water_disposal_mode.
     *
     * @param waste_water_disposal_modeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Waste_water_disposal_modeDTO save(Waste_water_disposal_modeDTO waste_water_disposal_modeDTO) {
        log.debug("Request to save Waste_water_disposal_mode : {}", waste_water_disposal_modeDTO);
        Waste_water_disposal_mode waste_water_disposal_mode = waste_water_disposal_modeMapper.waste_water_disposal_modeDTOToWaste_water_disposal_mode(waste_water_disposal_modeDTO);
        waste_water_disposal_mode = waste_water_disposal_modeRepository.save(waste_water_disposal_mode);
        Waste_water_disposal_modeDTO result = waste_water_disposal_modeMapper.waste_water_disposal_modeToWaste_water_disposal_modeDTO(waste_water_disposal_mode);
        return result;
    }

    /**
     *  Get all the waste_water_disposal_modes.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Waste_water_disposal_modeDTO> findAll() {
        log.debug("Request to get all Waste_water_disposal_modes");
        List<Waste_water_disposal_modeDTO> result = waste_water_disposal_modeRepository.findAll().stream()
            .map(waste_water_disposal_modeMapper::waste_water_disposal_modeToWaste_water_disposal_modeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one waste_water_disposal_mode by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Waste_water_disposal_modeDTO findOne(String id) {
        log.debug("Request to get Waste_water_disposal_mode : {}", id);
        Waste_water_disposal_mode waste_water_disposal_mode = waste_water_disposal_modeRepository.findOne(UUID.fromString(id));
        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = waste_water_disposal_modeMapper.waste_water_disposal_modeToWaste_water_disposal_modeDTO(waste_water_disposal_mode);
        return waste_water_disposal_modeDTO;
    }

    /**
     *  Delete the  waste_water_disposal_mode by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Waste_water_disposal_mode : {}", id);
        waste_water_disposal_modeRepository.delete(UUID.fromString(id));
    }
}
