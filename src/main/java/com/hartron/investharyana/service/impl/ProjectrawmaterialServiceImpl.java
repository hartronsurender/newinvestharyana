package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectrawmaterialService;
import com.hartron.investharyana.domain.Projectrawmaterial;
import com.hartron.investharyana.repository.ProjectrawmaterialRepository;
import com.hartron.investharyana.service.dto.ProjectrawmaterialDTO;
import com.hartron.investharyana.service.mapper.ProjectrawmaterialMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectrawmaterial.
 */
@Service
public class ProjectrawmaterialServiceImpl implements ProjectrawmaterialService{

    private final Logger log = LoggerFactory.getLogger(ProjectrawmaterialServiceImpl.class);
    
    private final ProjectrawmaterialRepository projectrawmaterialRepository;

    private final ProjectrawmaterialMapper projectrawmaterialMapper;

    public ProjectrawmaterialServiceImpl(ProjectrawmaterialRepository projectrawmaterialRepository, ProjectrawmaterialMapper projectrawmaterialMapper) {
        this.projectrawmaterialRepository = projectrawmaterialRepository;
        this.projectrawmaterialMapper = projectrawmaterialMapper;
    }

    /**
     * Save a projectrawmaterial.
     *
     * @param projectrawmaterialDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectrawmaterialDTO save(ProjectrawmaterialDTO projectrawmaterialDTO) {
        log.debug("Request to save Projectrawmaterial : {}", projectrawmaterialDTO);
        Projectrawmaterial projectrawmaterial = projectrawmaterialMapper.projectrawmaterialDTOToProjectrawmaterial(projectrawmaterialDTO);
        projectrawmaterial = projectrawmaterialRepository.save(projectrawmaterial);
        ProjectrawmaterialDTO result = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(projectrawmaterial);
        return result;
    }

    /**
     *  Get all the projectrawmaterials.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectrawmaterialDTO> findAll() {
        log.debug("Request to get all Projectrawmaterials");
        List<ProjectrawmaterialDTO> result = projectrawmaterialRepository.findAll().stream()
            .map(projectrawmaterialMapper::projectrawmaterialToProjectrawmaterialDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectrawmaterial by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectrawmaterialDTO findOne(String id) {
        log.debug("Request to get Projectrawmaterial : {}", id);
        Projectrawmaterial projectrawmaterial = projectrawmaterialRepository.findOne(UUID.fromString(id));
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialMapper.projectrawmaterialToProjectrawmaterialDTO(projectrawmaterial);
        return projectrawmaterialDTO;
    }

    /**
     *  Delete the  projectrawmaterial by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectrawmaterial : {}", id);
        projectrawmaterialRepository.delete(UUID.fromString(id));
    }
}
