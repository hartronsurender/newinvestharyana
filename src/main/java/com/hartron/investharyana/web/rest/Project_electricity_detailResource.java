package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Project_electricity_detailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Project_electricity_detailDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller for managing Project_electricity_detail.
 */
@RestController
@RequestMapping("/api")
public class Project_electricity_detailResource {

    private final Logger log = LoggerFactory.getLogger(Project_electricity_detailResource.class);

    private static final String ENTITY_NAME = "project_electricity_detail";
        
    private final Project_electricity_detailService project_electricity_detailService;

    public Project_electricity_detailResource(Project_electricity_detailService project_electricity_detailService) {
        this.project_electricity_detailService = project_electricity_detailService;
    }

    /**
     * POST  /project-electricity-details : Create a new project_electricity_detail.
     *
     * @param project_electricity_detailDTO the project_electricity_detailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new project_electricity_detailDTO, or with status 400 (Bad Request) if the project_electricity_detail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-electricity-details")
    @Timed
    public ResponseEntity<Project_electricity_detailDTO> createProject_electricity_detail(@RequestBody Project_electricity_detailDTO project_electricity_detailDTO) throws URISyntaxException {
        log.debug("REST request to save Project_electricity_detail : {}", project_electricity_detailDTO);
        if (project_electricity_detailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project_electricity_detail cannot already have an ID")).body(null);
        }
        Project_electricity_detailDTO result = project_electricity_detailService.save(project_electricity_detailDTO);
        return ResponseEntity.created(new URI("/api/project-electricity-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-electricity-details : Updates an existing project_electricity_detail.
     *
     * @param project_electricity_detailDTO the project_electricity_detailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated project_electricity_detailDTO,
     * or with status 400 (Bad Request) if the project_electricity_detailDTO is not valid,
     * or with status 500 (Internal Server Error) if the project_electricity_detailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-electricity-details")
    @Timed
    public ResponseEntity<Project_electricity_detailDTO> updateProject_electricity_detail(@RequestBody Project_electricity_detailDTO project_electricity_detailDTO) throws URISyntaxException {
        log.debug("REST request to update Project_electricity_detail : {}", project_electricity_detailDTO);
        if (project_electricity_detailDTO.getId() == null) {
            return createProject_electricity_detail(project_electricity_detailDTO);
        }
        Project_electricity_detailDTO result = project_electricity_detailService.save(project_electricity_detailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, project_electricity_detailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-electricity-details : get all the project_electricity_details.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of project_electricity_details in body
     */
    @GetMapping("/project-electricity-details")
    @Timed
    public List<Project_electricity_detailDTO> getAllProject_electricity_details() {
        log.debug("REST request to get all Project_electricity_details");
        return project_electricity_detailService.findAll();
    }

    /**
     * GET  /project-electricity-details/:id : get the "id" project_electricity_detail.
     *
     * @param id the id of the project_electricity_detailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the project_electricity_detailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-electricity-details/{id}")
    @Timed
    public ResponseEntity<Project_electricity_detailDTO> getProject_electricity_detail(@PathVariable String id) {
        log.debug("REST request to get Project_electricity_detail : {}", id);
        Project_electricity_detailDTO project_electricity_detailDTO = project_electricity_detailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(project_electricity_detailDTO));
    }

    /**
     * DELETE  /project-electricity-details/:id : delete the "id" project_electricity_detail.
     *
     * @param id the id of the project_electricity_detailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-electricity-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteProject_electricity_detail(@PathVariable String id) {
        log.debug("REST request to delete Project_electricity_detail : {}", id);
        project_electricity_detailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
