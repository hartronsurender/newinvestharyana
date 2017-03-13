package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProjectdetailService;
import com.hartron.investharyana.domain.Projectdetail;
import com.hartron.investharyana.repository.ProjectdetailRepository;
import com.hartron.investharyana.service.dto.ProjectdetailDTO;
import com.hartron.investharyana.service.mapper.ProjectdetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Projectdetail.
 */
@Service
public class ProjectdetailServiceImpl implements ProjectdetailService{

    private final Logger log = LoggerFactory.getLogger(ProjectdetailServiceImpl.class);
    
    private final ProjectdetailRepository projectdetailRepository;

    private final ProjectdetailMapper projectdetailMapper;

    public ProjectdetailServiceImpl(ProjectdetailRepository projectdetailRepository, ProjectdetailMapper projectdetailMapper) {
        this.projectdetailRepository = projectdetailRepository;
        this.projectdetailMapper = projectdetailMapper;
    }

    /**
     * Save a projectdetail.
     *
     * @param projectdetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectdetailDTO save(ProjectdetailDTO projectdetailDTO) {
        log.debug("Request to save Projectdetail : {}", projectdetailDTO);
        Projectdetail projectdetail = projectdetailMapper.projectdetailDTOToProjectdetail(projectdetailDTO);
        projectdetail = projectdetailRepository.save(projectdetail);
        ProjectdetailDTO result = projectdetailMapper.projectdetailToProjectdetailDTO(projectdetail);
        return result;
    }

    /**
     *  Get all the projectdetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProjectdetailDTO> findAll() {
        log.debug("Request to get all Projectdetails");
        List<ProjectdetailDTO> result = projectdetailRepository.findAll().stream()
            .map(projectdetailMapper::projectdetailToProjectdetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one projectdetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProjectdetailDTO findOne(String id) {
        log.debug("Request to get Projectdetail : {}", id);
        Projectdetail projectdetail = projectdetailRepository.findOne(UUID.fromString(id));
        ProjectdetailDTO projectdetailDTO = projectdetailMapper.projectdetailToProjectdetailDTO(projectdetail);
        return projectdetailDTO;
    }

    /**
     *  Delete the  projectdetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Projectdetail : {}", id);
        projectdetailRepository.delete(UUID.fromString(id));
    }
}
