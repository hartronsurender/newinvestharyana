package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ProjectrawmaterialService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ProjectrawmaterialDTO;
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
 * REST controller for managing Projectrawmaterial.
 */
@RestController
@RequestMapping("/api")
public class ProjectrawmaterialResource {

    private final Logger log = LoggerFactory.getLogger(ProjectrawmaterialResource.class);

    private static final String ENTITY_NAME = "projectrawmaterial";
        
    private final ProjectrawmaterialService projectrawmaterialService;

    public ProjectrawmaterialResource(ProjectrawmaterialService projectrawmaterialService) {
        this.projectrawmaterialService = projectrawmaterialService;
    }

    /**
     * POST  /projectrawmaterials : Create a new projectrawmaterial.
     *
     * @param projectrawmaterialDTO the projectrawmaterialDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectrawmaterialDTO, or with status 400 (Bad Request) if the projectrawmaterial has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projectrawmaterials")
    @Timed
    public ResponseEntity<ProjectrawmaterialDTO> createProjectrawmaterial(@RequestBody ProjectrawmaterialDTO projectrawmaterialDTO) throws URISyntaxException {
        log.debug("REST request to save Projectrawmaterial : {}", projectrawmaterialDTO);
        if (projectrawmaterialDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new projectrawmaterial cannot already have an ID")).body(null);
        }
        ProjectrawmaterialDTO result = projectrawmaterialService.save(projectrawmaterialDTO);
        return ResponseEntity.created(new URI("/api/projectrawmaterials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projectrawmaterials : Updates an existing projectrawmaterial.
     *
     * @param projectrawmaterialDTO the projectrawmaterialDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectrawmaterialDTO,
     * or with status 400 (Bad Request) if the projectrawmaterialDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectrawmaterialDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projectrawmaterials")
    @Timed
    public ResponseEntity<ProjectrawmaterialDTO> updateProjectrawmaterial(@RequestBody ProjectrawmaterialDTO projectrawmaterialDTO) throws URISyntaxException {
        log.debug("REST request to update Projectrawmaterial : {}", projectrawmaterialDTO);
        if (projectrawmaterialDTO.getId() == null) {
            return createProjectrawmaterial(projectrawmaterialDTO);
        }
        ProjectrawmaterialDTO result = projectrawmaterialService.save(projectrawmaterialDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectrawmaterialDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projectrawmaterials : get all the projectrawmaterials.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projectrawmaterials in body
     */
    @GetMapping("/projectrawmaterials")
    @Timed
    public List<ProjectrawmaterialDTO> getAllProjectrawmaterials() {
        log.debug("REST request to get all Projectrawmaterials");
        return projectrawmaterialService.findAll();
    }

    /**
     * GET  /projectrawmaterials/:id : get the "id" projectrawmaterial.
     *
     * @param id the id of the projectrawmaterialDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectrawmaterialDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projectrawmaterials/{id}")
    @Timed
    public ResponseEntity<ProjectrawmaterialDTO> getProjectrawmaterial(@PathVariable String id) {
        log.debug("REST request to get Projectrawmaterial : {}", id);
        ProjectrawmaterialDTO projectrawmaterialDTO = projectrawmaterialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectrawmaterialDTO));
    }

    /**
     * DELETE  /projectrawmaterials/:id : delete the "id" projectrawmaterial.
     *
     * @param id the id of the projectrawmaterialDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projectrawmaterials/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectrawmaterial(@PathVariable String id) {
        log.debug("REST request to delete Projectrawmaterial : {}", id);
        projectrawmaterialService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
