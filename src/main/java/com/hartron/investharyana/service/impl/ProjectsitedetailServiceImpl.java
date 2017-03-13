package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectsitedetailService;
import com.hartron.investharyana.domain.Projectsitedetail;
import com.hartron.investharyana.repository.ProjectsitedetailRepository;
import com.hartron.investharyana.service.dto.ProjectsitedetailDTO;
import com.hartron.investharyana.service.mapper.ProjectsitedetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectsitedetail.
 */
@Service
public class ProjectsitedetailServiceImpl implements ProjectsitedetailService{

    private final Logger log = LoggerFactory.getLogger(ProjectsitedetailServiceImpl.class);
    
    private final ProjectsitedetailRepository projectsitedetailRepository;

    private final ProjectsitedetailMapper projectsitedetailMapper;

    public ProjectsitedetailServiceImpl(ProjectsitedetailRepository projectsitedetailRepository, ProjectsitedetailMapper projectsitedetailMapper) {
        this.projectsitedetailRepository = projectsitedetailRepository;
        this.projectsitedetailMapper = projectsitedetailMapper;
    }

    /**
     * Save a projectsitedetail.
     *
     * @param projectsitedetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectsitedetailDTO save(ProjectsitedetailDTO projectsitedetailDTO) {
        log.debug("Request to save Projectsitedetail : {}", projectsitedetailDTO);
        Projectsitedetail projectsitedetail = projectsitedetailMapper.projectsitedetailDTOToProjectsitedetail(projectsitedetailDTO);
        projectsitedetail = projectsitedetailRepository.save(projectsitedetail);
        ProjectsitedetailDTO result = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(projectsitedetail);
        return result;
    }

    /**
     *  Get all the projectsitedetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectsitedetailDTO> findAll() {
        log.debug("Request to get all Projectsitedetails");
        List<ProjectsitedetailDTO> result = projectsitedetailRepository.findAll().stream()
            .map(projectsitedetailMapper::projectsitedetailToProjectsitedetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectsitedetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectsitedetailDTO findOne(String id) {
        log.debug("Request to get Projectsitedetail : {}", id);
        Projectsitedetail projectsitedetail = projectsitedetailRepository.findOne(UUID.fromString(id));
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailMapper.projectsitedetailToProjectsitedetailDTO(projectsitedetail);
        return projectsitedetailDTO;
    }

    /**
     *  Delete the  projectsitedetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectsitedetail : {}", id);
        projectsitedetailRepository.delete(UUID.fromString(id));
    }
}
