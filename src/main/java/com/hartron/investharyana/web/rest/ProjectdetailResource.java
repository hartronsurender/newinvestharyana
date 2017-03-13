package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectdetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectdetailDTO;
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
 * REST controller for managing Projectdetail.
 */
@RestController
@RequestMapping("/api")
public class ProjectdetailResource {

    private final Logger log = LoggerFactory.getLogger(ProjectdetailResource.class);

    private static final String ENTITY_NAME = "projectdetail";
        
    private final ProjectdetailService projectdetailService;

    public ProjectdetailResource(ProjectdetailService projectdetailService) {
        this.projectdetailService = projectdetailService;
    }

    /**
     * POST  /projectdetails : Create a new projectdetail.
     *
     * @param projectdetailDTO the projectdetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectdetailDTO, or with status 400 (Bad Request) if the projectdetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectdetails")
    @Timed
    public ResponseEntity<ProjectdetailDTO> createProjectdetail(@RequestBody ProjectdetailDTO projectdetailDTO) throws URISyntaxException {
        log.debug("REST request to save Projectdetail : {}", projectdetailDTO);
        if (projectdetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectdetail cannot already have an ID")).body(null);
        }
        ProjectdetailDTO result = projectdetailService.save(projectdetailDTO);
        return ResponseEntity.created(new URI("/api/projectdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectdetails : Updates an existing projectdetail.
     *
     * @param projectdetailDTO the projectdetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectdetailDTO,
     * or with status 400 (Bad Request) if the projectdetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectdetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectdetails")
    @Timed
    public ResponseEntity<ProjectdetailDTO> updateProjectdetail(@RequestBody ProjectdetailDTO projectdetailDTO) throws URISyntaxException {
        log.debug("REST request to update Projectdetail : {}", projectdetailDTO);
        if (projectdetailDTO.getId() == null) {
            return createProjectdetail(projectdetailDTO);
        }
        ProjectdetailDTO result = projectdetailService.save(projectdetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectdetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectdetails : get all the projectdetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectdetails in body
     */
    @GetMapping("/projectdetails")
    @Timed
    public List<ProjectdetailDTO> getAllProjectdetails() {
        log.debug("REST request to get all Projectdetails");
        return projectdetailService.findAll();
    }

    /**
     * GET  /projectdetails/:id : get the "id" projectdetail.
     *
     * @param id the id of the projectdetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectdetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectdetails/{id}")
    @Timed
    public ResponseEntity<ProjectdetailDTO> getProjectdetail(@PathVariable String id) {
        log.debug("REST request to get Projectdetail : {}", id);
        ProjectdetailDTO projectdetailDTO = projectdetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectdetailDTO));
    }

    /**
     * DELETE  /projectdetails/:id : delete the "id" projectdetail.
     *
     * @param id the id of the projectdetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectdetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectdetail(@PathVariable String id) {
        log.debug("REST request to delete Projectdetail : {}", id);
        projectdetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
