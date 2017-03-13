package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjecttypeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjecttypeDTO;
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
 * REST controller for managing Projecttype.
 */
@RestController
@RequestMapping("/api")
public class ProjecttypeResource {

    private final Logger log = LoggerFactory.getLogger(ProjecttypeResource.class);

    private static final String ENTITY_NAME = "projecttype";
        
    private final ProjecttypeService projecttypeService;

    public ProjecttypeResource(ProjecttypeService projecttypeService) {
        this.projecttypeService = projecttypeService;
    }

    /**
     * POST  /projecttypes : Create a new projecttype.
     *
     * @param projecttypeDTO the projecttypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projecttypeDTO, or with status 400 (Bad Request) if the projecttype has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projecttypes")
    @Timed
    public ResponseEntity<ProjecttypeDTO> createProjecttype(@RequestBody ProjecttypeDTO projecttypeDTO) throws URISyntaxException {
        log.debug("REST request to save Projecttype : {}", projecttypeDTO);
        if (projecttypeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projecttype cannot already have an ID")).body(null);
        }
        ProjecttypeDTO result = projecttypeService.save(projecttypeDTO);
        return ResponseEntity.created(new URI("/api/projecttypes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projecttypes : Updates an existing projecttype.
     *
     * @param projecttypeDTO the projecttypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projecttypeDTO,
     * or with status 400 (Bad Request) if the projecttypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the projecttypeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projecttypes")
    @Timed
    public ResponseEntity<ProjecttypeDTO> updateProjecttype(@RequestBody ProjecttypeDTO projecttypeDTO) throws URISyntaxException {
        log.debug("REST request to update Projecttype : {}", projecttypeDTO);
        if (projecttypeDTO.getId() == null) {
            return createProjecttype(projecttypeDTO);
        }
        ProjecttypeDTO result = projecttypeService.save(projecttypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projecttypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projecttypes : get all the projecttypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projecttypes in body
     */
    @GetMapping("/projecttypes")
    @Timed
    public List<ProjecttypeDTO> getAllProjecttypes() {
        log.debug("REST request to get all Projecttypes");
        return projecttypeService.findAll();
    }

    /**
     * GET  /projecttypes/:id : get the "id" projecttype.
     *
     * @param id the id of the projecttypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projecttypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projecttypes/{id}")
    @Timed
    public ResponseEntity<ProjecttypeDTO> getProjecttype(@PathVariable String id) {
        log.debug("REST request to get Projecttype : {}", id);
        ProjecttypeDTO projecttypeDTO = projecttypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projecttypeDTO));
    }

    /**
     * DELETE  /projecttypes/:id : delete the "id" projecttype.
     *
     * @param id the id of the projecttypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projecttypes/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjecttype(@PathVariable String id) {
        log.debug("REST request to delete Projecttype : {}", id);
        projecttypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
