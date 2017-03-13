package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectphaseService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectphaseDTO;
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
 * REST controller for managing Projectphase.
 */
@RestController
@RequestMapping("/api")
public class ProjectphaseResource {

    private final Logger log = LoggerFactory.getLogger(ProjectphaseResource.class);

    private static final String ENTITY_NAME = "projectphase";
        
    private final ProjectphaseService projectphaseService;

    public ProjectphaseResource(ProjectphaseService projectphaseService) {
        this.projectphaseService = projectphaseService;
    }

    /**
     * POST  /projectphases : Create a new projectphase.
     *
     * @param projectphaseDTO the projectphaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectphaseDTO, or with status 400 (Bad Request) if the projectphase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectphases")
    @Timed
    public ResponseEntity<ProjectphaseDTO> createProjectphase(@RequestBody ProjectphaseDTO projectphaseDTO) throws URISyntaxException {
        log.debug("REST request to save Projectphase : {}", projectphaseDTO);
        if (projectphaseDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectphase cannot already have an ID")).body(null);
        }
        ProjectphaseDTO result = projectphaseService.save(projectphaseDTO);
        return ResponseEntity.created(new URI("/api/projectphases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectphases : Updates an existing projectphase.
     *
     * @param projectphaseDTO the projectphaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectphaseDTO,
     * or with status 400 (Bad Request) if the projectphaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectphaseDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectphases")
    @Timed
    public ResponseEntity<ProjectphaseDTO> updateProjectphase(@RequestBody ProjectphaseDTO projectphaseDTO) throws URISyntaxException {
        log.debug("REST request to update Projectphase : {}", projectphaseDTO);
        if (projectphaseDTO.getId() == null) {
            return createProjectphase(projectphaseDTO);
        }
        ProjectphaseDTO result = projectphaseService.save(projectphaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectphaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectphases : get all the projectphases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectphases in body
     */
    @GetMapping("/projectphases")
    @Timed
    public List<ProjectphaseDTO> getAllProjectphases() {
        log.debug("REST request to get all Projectphases");
        return projectphaseService.findAll();
    }

    /**
     * GET  /projectphases/:id : get the "id" projectphase.
     *
     * @param id the id of the projectphaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectphaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectphases/{id}")
    @Timed
    public ResponseEntity<ProjectphaseDTO> getProjectphase(@PathVariable String id) {
        log.debug("REST request to get Projectphase : {}", id);
        ProjectphaseDTO projectphaseDTO = projectphaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectphaseDTO));
    }

    /**
     * DELETE  /projectphases/:id : delete the "id" projectphase.
     *
     * @param id the id of the projectphaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectphases/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectphase(@PathVariable String id) {
        log.debug("REST request to delete Projectphase : {}", id);
        projectphaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
