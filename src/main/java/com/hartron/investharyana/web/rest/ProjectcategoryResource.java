package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectcategoryService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectcategoryDTO;
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
 * REST controller for managing Projectcategory.
 */
@RestController
@RequestMapping("/api")
public class ProjectcategoryResource {

    private final Logger log = LoggerFactory.getLogger(ProjectcategoryResource.class);

    private static final String ENTITY_NAME = "projectcategory";
        
    private final ProjectcategoryService projectcategoryService;

    public ProjectcategoryResource(ProjectcategoryService projectcategoryService) {
        this.projectcategoryService = projectcategoryService;
    }

    /**
     * POST  /projectcategories : Create a new projectcategory.
     *
     * @param projectcategoryDTO the projectcategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectcategoryDTO, or with status 400 (Bad Request) if the projectcategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectcategories")
    @Timed
    public ResponseEntity<ProjectcategoryDTO> createProjectcategory(@RequestBody ProjectcategoryDTO projectcategoryDTO) throws URISyntaxException {
        log.debug("REST request to save Projectcategory : {}", projectcategoryDTO);
        if (projectcategoryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectcategory cannot already have an ID")).body(null);
        }
        ProjectcategoryDTO result = projectcategoryService.save(projectcategoryDTO);
        return ResponseEntity.created(new URI("/api/projectcategories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectcategories : Updates an existing projectcategory.
     *
     * @param projectcategoryDTO the projectcategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectcategoryDTO,
     * or with status 400 (Bad Request) if the projectcategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectcategoryDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectcategories")
    @Timed
    public ResponseEntity<ProjectcategoryDTO> updateProjectcategory(@RequestBody ProjectcategoryDTO projectcategoryDTO) throws URISyntaxException {
        log.debug("REST request to update Projectcategory : {}", projectcategoryDTO);
        if (projectcategoryDTO.getId() == null) {
            return createProjectcategory(projectcategoryDTO);
        }
        ProjectcategoryDTO result = projectcategoryService.save(projectcategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectcategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectcategories : get all the projectcategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectcategories in body
     */
    @GetMapping("/projectcategories")
    @Timed
    public List<ProjectcategoryDTO> getAllProjectcategories() {
        log.debug("REST request to get all Projectcategories");
        return projectcategoryService.findAll();
    }

    /**
     * GET  /projectcategories/:id : get the "id" projectcategory.
     *
     * @param id the id of the projectcategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectcategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectcategories/{id}")
    @Timed
    public ResponseEntity<ProjectcategoryDTO> getProjectcategory(@PathVariable String id) {
        log.debug("REST request to get Projectcategory : {}", id);
        ProjectcategoryDTO projectcategoryDTO = projectcategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectcategoryDTO));
    }

    /**
     * DELETE  /projectcategories/:id : delete the "id" projectcategory.
     *
     * @param id the id of the projectcategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectcategories/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectcategory(@PathVariable String id) {
        log.debug("REST request to delete Projectcategory : {}", id);
        projectcategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
