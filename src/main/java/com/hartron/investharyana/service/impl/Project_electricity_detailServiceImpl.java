package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Project_electricity_detailService;
import com.hartron.investharyana.domain.Project_electricity_detail;
import com.hartron.investharyana.repository.Project_electricity_detailRepository;
import com.hartron.investharyana.service.dto.Project_electricity_detailDTO;
import com.hartron.investharyana.service.mapper.Project_electricity_detailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Project_electricity_detail.
 */
@Service
public class Project_electricity_detailServiceImpl implements Project_electricity_detailService{

    private final Logger log = LoggerFactory.getLogger(Project_electricity_detailServiceImpl.class);
    
    private final Project_electricity_detailRepository project_electricity_detailRepository;

    private final Project_electricity_detailMapper project_electricity_detailMapper;

    public Project_electricity_detailServiceImpl(Project_electricity_detailRepository project_electricity_detailRepository, Project_electricity_detailMapper project_electricity_detailMapper) {
        this.project_electricity_detailRepository = project_electricity_detailRepository;
        this.project_electricity_detailMapper = project_electricity_detailMapper;
    }

    /**
     * Save a project_electricity_detail.
     *
     * @param project_electricity_detailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Project_electricity_detailDTO save(Project_electricity_detailDTO project_electricity_detailDTO) {
        log.debug("Request to save Project_electricity_detail : {}", project_electricity_detailDTO);
        Project_electricity_detail project_electricity_detail = project_electricity_detailMapper.project_electricity_detailDTOToProject_electricity_detail(project_electricity_detailDTO);
        project_electricity_detail = project_electricity_detailRepository.save(project_electricity_detail);
        Project_electricity_detailDTO result = project_electricity_detailMapper.project_electricity_detailToProject_electricity_detailDTO(project_electricity_detail);
        return result;
    }

    /**
     *  Get all the project_electricity_details.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Project_electricity_detailDTO> findAll() {
        log.debug("Request to get all Project_electricity_details");
        List<Project_electricity_detailDTO> result = project_electricity_detailRepository.findAll().stream()
            .map(project_electricity_detailMapper::project_electricity_detailToProject_electricity_detailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one project_electricity_detail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Project_electricity_detailDTO findOne(String id) {
        log.debug("Request to get Project_electricity_detail : {}", id);
        Project_electricity_detail project_electricity_detail = project_electricity_detailRepository.findOne(UUID.fromString(id));
        Project_electricity_detailDTO project_electricity_detailDTO = project_electricity_detailMapper.project_electricity_detailToProject_electricity_detailDTO(project_electricity_detail);
        return project_electricity_detailDTO;
    }

    /**
     *  Delete the  project_electricity_detail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Project_electricity_detail : {}", id);
        project_electricity_detailRepository.delete(UUID.fromString(id));
    }
}
