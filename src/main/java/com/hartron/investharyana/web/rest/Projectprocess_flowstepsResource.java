package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Projectprocess_flowstepsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Projectprocess_flowstepsDTO;
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
 * REST controller for managing Projectprocess_flowsteps.
 */
@RestController
@RequestMapping("/api")
public class Projectprocess_flowstepsResource {

    private final Logger log = LoggerFactory.getLogger(Projectprocess_flowstepsResource.class);

    private static final String ENTITY_NAME = "projectprocess_flowsteps";
        
    private final Projectprocess_flowstepsService projectprocess_flowstepsService;

    public Projectprocess_flowstepsResource(Projectprocess_flowstepsService projectprocess_flowstepsService) {
        this.projectprocess_flowstepsService = projectprocess_flowstepsService;
    }

    /**
     * POST  /projectprocess-flowsteps : Create a new projectprocess_flowsteps.
     *
     * @param projectprocess_flowstepsDTO the projectprocess_flowstepsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectprocess_flowstepsDTO, or with status 400 (Bad Request) if the projectprocess_flowsteps has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectprocess-flowsteps")
    @Timed
    public ResponseEntity<Projectprocess_flowstepsDTO> createProjectprocess_flowsteps(@RequestBody Projectprocess_flowstepsDTO projectprocess_flowstepsDTO) throws URISyntaxException {
        log.debug("REST request to save Projectprocess_flowsteps : {}", projectprocess_flowstepsDTO);
        if (projectprocess_flowstepsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectprocess_flowsteps cannot already have an ID")).body(null);
        }
        Projectprocess_flowstepsDTO result = projectprocess_flowstepsService.save(projectprocess_flowstepsDTO);
        return ResponseEntity.created(new URI("/api/projectprocess-flowsteps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectprocess-flowsteps : Updates an existing projectprocess_flowsteps.
     *
     * @param projectprocess_flowstepsDTO the projectprocess_flowstepsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectprocess_flowstepsDTO,
     * or with status 400 (Bad Request) if the projectprocess_flowstepsDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectprocess_flowstepsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectprocess-flowsteps")
    @Timed
    public ResponseEntity<Projectprocess_flowstepsDTO> updateProjectprocess_flowsteps(@RequestBody Projectprocess_flowstepsDTO projectprocess_flowstepsDTO) throws URISyntaxException {
        log.debug("REST request to update Projectprocess_flowsteps : {}", projectprocess_flowstepsDTO);
        if (projectprocess_flowstepsDTO.getId() == null) {
            return createProjectprocess_flowsteps(projectprocess_flowstepsDTO);
        }
        Projectprocess_flowstepsDTO result = projectprocess_flowstepsService.save(projectprocess_flowstepsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectprocess_flowstepsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectprocess-flowsteps : get all the projectprocess_flowsteps.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectprocess_flowsteps in body
     */
    @GetMapping("/projectprocess-flowsteps")
    @Timed
    public List<Projectprocess_flowstepsDTO> getAllProjectprocess_flowsteps() {
        log.debug("REST request to get all Projectprocess_flowsteps");
        return projectprocess_flowstepsService.findAll();
    }

    /**
     * GET  /projectprocess-flowsteps/:id : get the "id" projectprocess_flowsteps.
     *
     * @param id the id of the projectprocess_flowstepsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectprocess_flowstepsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectprocess-flowsteps/{id}")
    @Timed
    public ResponseEntity<Projectprocess_flowstepsDTO> getProjectprocess_flowsteps(@PathVariable String id) {
        log.debug("REST request to get Projectprocess_flowsteps : {}", id);
        Projectprocess_flowstepsDTO projectprocess_flowstepsDTO = projectprocess_flowstepsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectprocess_flowstepsDTO));
    }

    /**
     * DELETE  /projectprocess-flowsteps/:id : delete the "id" projectprocess_flowsteps.
     *
     * @param id the id of the projectprocess_flowstepsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectprocess-flowsteps/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectprocess_flowsteps(@PathVariable String id) {
        log.debug("REST request to delete Projectprocess_flowsteps : {}", id);
        projectprocess_flowstepsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
