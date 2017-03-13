package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Projectprocess_flowstepsService;
import com.hartron.investharyana.domain.Projectprocess_flowsteps;
import com.hartron.investharyana.repository.Projectprocess_flowstepsRepository;
import com.hartron.investharyana.service.dto.Projectprocess_flowstepsDTO;
import com.hartron.investharyana.service.mapper.Projectprocess_flowstepsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectprocess_flowsteps.
 */
@Service
public class Projectprocess_flowstepsServiceImpl implements Projectprocess_flowstepsService{

    private final Logger log = LoggerFactory.getLogger(Projectprocess_flowstepsServiceImpl.class);
    
    private final Projectprocess_flowstepsRepository projectprocess_flowstepsRepository;

    private final Projectprocess_flowstepsMapper projectprocess_flowstepsMapper;

    public Projectprocess_flowstepsServiceImpl(Projectprocess_flowstepsRepository projectprocess_flowstepsRepository, Projectprocess_flowstepsMapper projectprocess_flowstepsMapper) {
        this.projectprocess_flowstepsRepository = projectprocess_flowstepsRepository;
        this.projectprocess_flowstepsMapper = projectprocess_flowstepsMapper;
    }

    /**
     * Save a projectprocess_flowsteps.
     *
     * @param projectprocess_flowstepsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Projectprocess_flowstepsDTO save(Projectprocess_flowstepsDTO projectprocess_flowstepsDTO) {
        log.debug("Request to save Projectprocess_flowsteps : {}", projectprocess_flowstepsDTO);
        Projectprocess_flowsteps projectprocess_flowsteps = projectprocess_flowstepsMapper.projectprocess_flowstepsDTOToProjectprocess_flowsteps(projectprocess_flowstepsDTO);
        projectprocess_flowsteps = projectprocess_flowstepsRepository.save(projectprocess_flowsteps);
        Projectprocess_flowstepsDTO result = projectprocess_flowstepsMapper.projectprocess_flowstepsToProjectprocess_flowstepsDTO(projectprocess_flowsteps);
        return result;
    }

    /**
     *  Get all the projectprocess_flowsteps.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Projectprocess_flowstepsDTO> findAll() {
        log.debug("Request to get all Projectprocess_flowsteps");
        List<Projectprocess_flowstepsDTO> result = projectprocess_flowstepsRepository.findAll().stream()
            .map(projectprocess_flowstepsMapper::projectprocess_flowstepsToProjectprocess_flowstepsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectprocess_flowsteps by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Projectprocess_flowstepsDTO findOne(String id) {
        log.debug("Request to get Projectprocess_flowsteps : {}", id);
        Projectprocess_flowsteps projectprocess_flowsteps = projectprocess_flowstepsRepository.findOne(UUID.fromString(id));
        Projectprocess_flowstepsDTO projectprocess_flowstepsDTO = projectprocess_flowstepsMapper.projectprocess_flowstepsToProjectprocess_flowstepsDTO(projectprocess_flowsteps);
        return projectprocess_flowstepsDTO;
    }

    /**
     *  Delete the  projectprocess_flowsteps by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectprocess_flowsteps : {}", id);
        projectprocess_flowstepsRepository.delete(UUID.fromString(id));
    }
}
