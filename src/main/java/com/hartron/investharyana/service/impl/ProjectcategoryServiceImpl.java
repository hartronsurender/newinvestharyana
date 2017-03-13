package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectcategoryService;
import com.hartron.investharyana.domain.Projectcategory;
import com.hartron.investharyana.repository.ProjectcategoryRepository;
import com.hartron.investharyana.service.dto.ProjectcategoryDTO;
import com.hartron.investharyana.service.mapper.ProjectcategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectcategory.
 */
@Service
public class ProjectcategoryServiceImpl implements ProjectcategoryService{

    private final Logger log = LoggerFactory.getLogger(ProjectcategoryServiceImpl.class);
    
    private final ProjectcategoryRepository projectcategoryRepository;

    private final ProjectcategoryMapper projectcategoryMapper;

    public ProjectcategoryServiceImpl(ProjectcategoryRepository projectcategoryRepository, ProjectcategoryMapper projectcategoryMapper) {
        this.projectcategoryRepository = projectcategoryRepository;
        this.projectcategoryMapper = projectcategoryMapper;
    }

    /**
     * Save a projectcategory.
     *
     * @param projectcategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectcategoryDTO save(ProjectcategoryDTO projectcategoryDTO) {
        log.debug("Request to save Projectcategory : {}", projectcategoryDTO);
        Projectcategory projectcategory = projectcategoryMapper.projectcategoryDTOToProjectcategory(projectcategoryDTO);
        projectcategory = projectcategoryRepository.save(projectcategory);
        ProjectcategoryDTO result = projectcategoryMapper.projectcategoryToProjectcategoryDTO(projectcategory);
        return result;
    }

    /**
     *  Get all the projectcategories.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectcategoryDTO> findAll() {
        log.debug("Request to get all Projectcategories");
        List<ProjectcategoryDTO> result = projectcategoryRepository.findAll().stream()
            .map(projectcategoryMapper::projectcategoryToProjectcategoryDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectcategory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectcategoryDTO findOne(String id) {
        log.debug("Request to get Projectcategory : {}", id);
        Projectcategory projectcategory = projectcategoryRepository.findOne(UUID.fromString(id));
        ProjectcategoryDTO projectcategoryDTO = projectcategoryMapper.projectcategoryToProjectcategoryDTO(projectcategory);
        return projectcategoryDTO;
    }

    /**
     *  Delete the  projectcategory by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectcategory : {}", id);
        projectcategoryRepository.delete(UUID.fromString(id));
    }
}
