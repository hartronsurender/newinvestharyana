package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectphaseService;
import com.hartron.investharyana.domain.Projectphase;
import com.hartron.investharyana.repository.ProjectphaseRepository;
import com.hartron.investharyana.service.dto.ProjectphaseDTO;
import com.hartron.investharyana.service.mapper.ProjectphaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectphase.
 */
@Service
public class ProjectphaseServiceImpl implements ProjectphaseService{

    private final Logger log = LoggerFactory.getLogger(ProjectphaseServiceImpl.class);
    
    private final ProjectphaseRepository projectphaseRepository;

    private final ProjectphaseMapper projectphaseMapper;

    public ProjectphaseServiceImpl(ProjectphaseRepository projectphaseRepository, ProjectphaseMapper projectphaseMapper) {
        this.projectphaseRepository = projectphaseRepository;
        this.projectphaseMapper = projectphaseMapper;
    }

    /**
     * Save a projectphase.
     *
     * @param projectphaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectphaseDTO save(ProjectphaseDTO projectphaseDTO) {
        log.debug("Request to save Projectphase : {}", projectphaseDTO);
        Projectphase projectphase = projectphaseMapper.projectphaseDTOToProjectphase(projectphaseDTO);
        projectphase = projectphaseRepository.save(projectphase);
        ProjectphaseDTO result = projectphaseMapper.projectphaseToProjectphaseDTO(projectphase);
        return result;
    }

    /**
     *  Get all the projectphases.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectphaseDTO> findAll() {
        log.debug("Request to get all Projectphases");
        List<ProjectphaseDTO> result = projectphaseRepository.findAll().stream()
            .map(projectphaseMapper::projectphaseToProjectphaseDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectphase by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectphaseDTO findOne(String id) {
        log.debug("Request to get Projectphase : {}", id);
        Projectphase projectphase = projectphaseRepository.findOne(UUID.fromString(id));
        ProjectphaseDTO projectphaseDTO = projectphaseMapper.projectphaseToProjectphaseDTO(projectphase);
        return projectphaseDTO;
    }

    /**
     *  Delete the  projectphase by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectphase : {}", id);
        projectphaseRepository.delete(UUID.fromString(id));
    }
}
