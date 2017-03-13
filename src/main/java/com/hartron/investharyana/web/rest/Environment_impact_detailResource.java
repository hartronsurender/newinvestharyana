package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Environment_impact_detailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Environment_impact_detailDTO;
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
 * REST controller for managing Environment_impact_detail.
 */
@RestController
@RequestMapping("/api")
public class Environment_impact_detailResource {

    private final Logger log = LoggerFactory.getLogger(Environment_impact_detailResource.class);

    private static final String ENTITY_NAME = "environment_impact_detail";
        
    private final Environment_impact_detailService environment_impact_detailService;

    public Environment_impact_detailResource(Environment_impact_detailService environment_impact_detailService) {
        this.environment_impact_detailService = environment_impact_detailService;
    }

    /**
     * POST  /environment-impact-details : Create a new environment_impact_detail.
     *
     * @param environment_impact_detailDTO the environment_impact_detailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new environment_impact_detailDTO, or with status 400 (Bad Request) if the environment_impact_detail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/environment-impact-details")
    @Timed
    public ResponseEntity<Environment_impact_detailDTO> createEnvironment_impact_detail(@RequestBody Environment_impact_detailDTO environment_impact_detailDTO) throws URISyntaxException {
        log.debug("REST request to save Environment_impact_detail : {}", environment_impact_detailDTO);
        if (environment_impact_detailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new environment_impact_detail cannot already have an ID")).body(null);
        }
        Environment_impact_detailDTO result = environment_impact_detailService.save(environment_impact_detailDTO);
        return ResponseEntity.created(new URI("/api/environment-impact-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /environment-impact-details : Updates an existing environment_impact_detail.
     *
     * @param environment_impact_detailDTO the environment_impact_detailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated environment_impact_detailDTO,
     * or with status 400 (Bad Request) if the environment_impact_detailDTO is not valid,
     * or with status 500 (Internal Server Error) if the environment_impact_detailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/environment-impact-details")
    @Timed
    public ResponseEntity<Environment_impact_detailDTO> updateEnvironment_impact_detail(@RequestBody Environment_impact_detailDTO environment_impact_detailDTO) throws URISyntaxException {
        log.debug("REST request to update Environment_impact_detail : {}", environment_impact_detailDTO);
        if (environment_impact_detailDTO.getId() == null) {
            return createEnvironment_impact_detail(environment_impact_detailDTO);
        }
        Environment_impact_detailDTO result = environment_impact_detailService.save(environment_impact_detailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, environment_impact_detailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /environment-impact-details : get all the environment_impact_details.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of environment_impact_details in body
     */
    @GetMapping("/environment-impact-details")
    @Timed
    public List<Environment_impact_detailDTO> getAllEnvironment_impact_details() {
        log.debug("REST request to get all Environment_impact_details");
        return environment_impact_detailService.findAll();
    }

    /**
     * GET  /environment-impact-details/:id : get the "id" environment_impact_detail.
     *
     * @param id the id of the environment_impact_detailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the environment_impact_detailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/environment-impact-details/{id}")
    @Timed
    public ResponseEntity<Environment_impact_detailDTO> getEnvironment_impact_detail(@PathVariable String id) {
        log.debug("REST request to get Environment_impact_detail : {}", id);
        Environment_impact_detailDTO environment_impact_detailDTO = environment_impact_detailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(environment_impact_detailDTO));
    }

    /**
     * DELETE  /environment-impact-details/:id : delete the "id" environment_impact_detail.
     *
     * @param id the id of the environment_impact_detailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/environment-impact-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnvironment_impact_detail(@PathVariable String id) {
        log.debug("REST request to delete Environment_impact_detail : {}", id);
        environment_impact_detailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
