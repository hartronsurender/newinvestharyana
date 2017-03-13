package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Waste_water_detailService;
import com.hartron.investharyana.domain.Waste_water_detail;
import com.hartron.investharyana.repository.Waste_water_detailRepository;
import com.hartron.investharyana.service.dto.Waste_water_detailDTO;
import com.hartron.investharyana.service.mapper.Waste_water_detailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Waste_water_detail.
 */
@Service
public class Waste_water_detailServiceImpl implements Waste_water_detailService{

    private final Logger log = LoggerFactory.getLogger(Waste_water_detailServiceImpl.class);
    
    private final Waste_water_detailRepository waste_water_detailRepository;

    private final Waste_water_detailMapper waste_water_detailMapper;

    public Waste_water_detailServiceImpl(Waste_water_detailRepository waste_water_detailRepository, Waste_water_detailMapper waste_water_detailMapper) {
        this.waste_water_detailRepository = waste_water_detailRepository;
        this.waste_water_detailMapper = waste_water_detailMapper;
    }

    /**
     * Save a waste_water_detail.
     *
     * @param waste_water_detailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Waste_water_detailDTO save(Waste_water_detailDTO waste_water_detailDTO) {
        log.debug("Request to save Waste_water_detail : {}", waste_water_detailDTO);
        Waste_water_detail waste_water_detail = waste_water_detailMapper.waste_water_detailDTOToWaste_water_detail(waste_water_detailDTO);
        waste_water_detail = waste_water_detailRepository.save(waste_water_detail);
        Waste_water_detailDTO result = waste_water_detailMapper.waste_water_detailToWaste_water_detailDTO(waste_water_detail);
        return result;
    }

    /**
     *  Get all the waste_water_details.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Waste_water_detailDTO> findAll() {
        log.debug("Request to get all Waste_water_details");
        List<Waste_water_detailDTO> result = waste_water_detailRepository.findAll().stream()
            .map(waste_water_detailMapper::waste_water_detailToWaste_water_detailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one waste_water_detail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Waste_water_detailDTO findOne(String id) {
        log.debug("Request to get Waste_water_detail : {}", id);
        Waste_water_detail waste_water_detail = waste_water_detailRepository.findOne(UUID.fromString(id));
        Waste_water_detailDTO waste_water_detailDTO = waste_water_detailMapper.waste_water_detailToWaste_water_detailDTO(waste_water_detail);
        return waste_water_detailDTO;
    }

    /**
     *  Delete the  waste_water_detail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Waste_water_detail : {}", id);
        waste_water_detailRepository.delete(UUID.fromString(id));
    }
}
