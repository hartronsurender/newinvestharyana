package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Environment_impact_detailService;
import com.hartron.investharyana.domain.Environment_impact_detail;
import com.hartron.investharyana.repository.Environment_impact_detailRepository;
import com.hartron.investharyana.service.dto.Environment_impact_detailDTO;
import com.hartron.investharyana.service.mapper.Environment_impact_detailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Environment_impact_detail.
 */
@Service
public class Environment_impact_detailServiceImpl implements Environment_impact_detailService{

    private final Logger log = LoggerFactory.getLogger(Environment_impact_detailServiceImpl.class);
    
    private final Environment_impact_detailRepository environment_impact_detailRepository;

    private final Environment_impact_detailMapper environment_impact_detailMapper;

    public Environment_impact_detailServiceImpl(Environment_impact_detailRepository environment_impact_detailRepository, Environment_impact_detailMapper environment_impact_detailMapper) {
        this.environment_impact_detailRepository = environment_impact_detailRepository;
        this.environment_impact_detailMapper = environment_impact_detailMapper;
    }

    /**
     * Save a environment_impact_detail.
     *
     * @param environment_impact_detailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Environment_impact_detailDTO save(Environment_impact_detailDTO environment_impact_detailDTO) {
        log.debug("Request to save Environment_impact_detail : {}", environment_impact_detailDTO);
        Environment_impact_detail environment_impact_detail = environment_impact_detailMapper.environment_impact_detailDTOToEnvironment_impact_detail(environment_impact_detailDTO);
        environment_impact_detail = environment_impact_detailRepository.save(environment_impact_detail);
        Environment_impact_detailDTO result = environment_impact_detailMapper.environment_impact_detailToEnvironment_impact_detailDTO(environment_impact_detail);
        return result;
    }

    /**
     *  Get all the environment_impact_details.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Environment_impact_detailDTO> findAll() {
        log.debug("Request to get all Environment_impact_details");
        List<Environment_impact_detailDTO> result = environment_impact_detailRepository.findAll().stream()
            .map(environment_impact_detailMapper::environment_impact_detailToEnvironment_impact_detailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one environment_impact_detail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Environment_impact_detailDTO findOne(String id) {
        log.debug("Request to get Environment_impact_detail : {}", id);
        Environment_impact_detail environment_impact_detail = environment_impact_detailRepository.findOne(UUID.fromString(id));
        Environment_impact_detailDTO environment_impact_detailDTO = environment_impact_detailMapper.environment_impact_detailToEnvironment_impact_detailDTO(environment_impact_detail);
        return environment_impact_detailDTO;
    }

    /**
     *  Delete the  environment_impact_detail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Environment_impact_detail : {}", id);
        environment_impact_detailRepository.delete(UUID.fromString(id));
    }
}
