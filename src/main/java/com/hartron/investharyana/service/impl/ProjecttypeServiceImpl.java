package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjecttypeService;
import com.hartron.investharyana.domain.Projecttype;
import com.hartron.investharyana.repository.ProjecttypeRepository;
import com.hartron.investharyana.service.dto.ProjecttypeDTO;
import com.hartron.investharyana.service.mapper.ProjecttypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projecttype.
 */
@Service
public class ProjecttypeServiceImpl implements ProjecttypeService{

    private final Logger log = LoggerFactory.getLogger(ProjecttypeServiceImpl.class);
    
    private final ProjecttypeRepository projecttypeRepository;

    private final ProjecttypeMapper projecttypeMapper;

    public ProjecttypeServiceImpl(ProjecttypeRepository projecttypeRepository, ProjecttypeMapper projecttypeMapper) {
        this.projecttypeRepository = projecttypeRepository;
        this.projecttypeMapper = projecttypeMapper;
    }

    /**
     * Save a projecttype.
     *
     * @param projecttypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjecttypeDTO save(ProjecttypeDTO projecttypeDTO) {
        log.debug("Request to save Projecttype : {}", projecttypeDTO);
        Projecttype projecttype = projecttypeMapper.projecttypeDTOToProjecttype(projecttypeDTO);
        projecttype = projecttypeRepository.save(projecttype);
        ProjecttypeDTO result = projecttypeMapper.projecttypeToProjecttypeDTO(projecttype);
        return result;
    }

    /**
     *  Get all the projecttypes.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjecttypeDTO> findAll() {
        log.debug("Request to get all Projecttypes");
        List<ProjecttypeDTO> result = projecttypeRepository.findAll().stream()
            .map(projecttypeMapper::projecttypeToProjecttypeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projecttype by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjecttypeDTO findOne(String id) {
        log.debug("Request to get Projecttype : {}", id);
        Projecttype projecttype = projecttypeRepository.findOne(UUID.fromString(id));
        ProjecttypeDTO projecttypeDTO = projecttypeMapper.projecttypeToProjecttypeDTO(projecttype);
        return projecttypeDTO;
    }

    /**
     *  Delete the  projecttype by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projecttype : {}", id);
        projecttypeRepository.delete(UUID.fromString(id));
    }
}
