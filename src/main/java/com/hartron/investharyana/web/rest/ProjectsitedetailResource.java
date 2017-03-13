package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectsitedetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectsitedetailDTO;
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
 * REST controller for managing Projectsitedetail.
 */
@RestController
@RequestMapping("/api")
public class ProjectsitedetailResource {

    private final Logger log = LoggerFactory.getLogger(ProjectsitedetailResource.class);

    private static final String ENTITY_NAME = "projectsitedetail";
        
    private final ProjectsitedetailService projectsitedetailService;

    public ProjectsitedetailResource(ProjectsitedetailService projectsitedetailService) {
        this.projectsitedetailService = projectsitedetailService;
    }

    /**
     * POST  /projectsitedetails : Create a new projectsitedetail.
     *
     * @param projectsitedetailDTO the projectsitedetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectsitedetailDTO, or with status 400 (Bad Request) if the projectsitedetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectsitedetails")
    @Timed
    public ResponseEntity<ProjectsitedetailDTO> createProjectsitedetail(@RequestBody ProjectsitedetailDTO projectsitedetailDTO) throws URISyntaxException {
        log.debug("REST request to save Projectsitedetail : {}", projectsitedetailDTO);
        if (projectsitedetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectsitedetail cannot already have an ID")).body(null);
        }
        ProjectsitedetailDTO result = projectsitedetailService.save(projectsitedetailDTO);
        return ResponseEntity.created(new URI("/api/projectsitedetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectsitedetails : Updates an existing projectsitedetail.
     *
     * @param projectsitedetailDTO the projectsitedetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectsitedetailDTO,
     * or with status 400 (Bad Request) if the projectsitedetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectsitedetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectsitedetails")
    @Timed
    public ResponseEntity<ProjectsitedetailDTO> updateProjectsitedetail(@RequestBody ProjectsitedetailDTO projectsitedetailDTO) throws URISyntaxException {
        log.debug("REST request to update Projectsitedetail : {}", projectsitedetailDTO);
        if (projectsitedetailDTO.getId() == null) {
            return createProjectsitedetail(projectsitedetailDTO);
        }
        ProjectsitedetailDTO result = projectsitedetailService.save(projectsitedetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectsitedetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectsitedetails : get all the projectsitedetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectsitedetails in body
     */
    @GetMapping("/projectsitedetails")
    @Timed
    public List<ProjectsitedetailDTO> getAllProjectsitedetails() {
        log.debug("REST request to get all Projectsitedetails");
        return projectsitedetailService.findAll();
    }

    /**
     * GET  /projectsitedetails/:id : get the "id" projectsitedetail.
     *
     * @param id the id of the projectsitedetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectsitedetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectsitedetails/{id}")
    @Timed
    public ResponseEntity<ProjectsitedetailDTO> getProjectsitedetail(@PathVariable String id) {
        log.debug("REST request to get Projectsitedetail : {}", id);
        ProjectsitedetailDTO projectsitedetailDTO = projectsitedetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectsitedetailDTO));
    }

    /**
     * DELETE  /projectsitedetails/:id : delete the "id" projectsitedetail.
     *
     * @param id the id of the projectsitedetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectsitedetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectsitedetail(@PathVariable String id) {
        log.debug("REST request to delete Projectsitedetail : {}", id);
        projectsitedetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
