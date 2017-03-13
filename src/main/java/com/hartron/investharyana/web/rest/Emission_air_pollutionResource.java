package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Emission_air_pollutionService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Emission_air_pollutionDTO;
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
 * REST controller for managing Emission_air_pollution.
 */
@RestController
@RequestMapping("/api")
public class Emission_air_pollutionResource {

    private final Logger log = LoggerFactory.getLogger(Emission_air_pollutionResource.class);

    private static final String ENTITY_NAME = "emission_air_pollution";
        
    private final Emission_air_pollutionService emission_air_pollutionService;

    public Emission_air_pollutionResource(Emission_air_pollutionService emission_air_pollutionService) {
        this.emission_air_pollutionService = emission_air_pollutionService;
    }

    /**
     * POST  /emission-air-pollutions : Create a new emission_air_pollution.
     *
     * @param emission_air_pollutionDTO the emission_air_pollutionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emission_air_pollutionDTO, or with status 400 (Bad Request) if the emission_air_pollution has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emission-air-pollutions")
    @Timed
    public ResponseEntity<Emission_air_pollutionDTO> createEmission_air_pollution(@RequestBody Emission_air_pollutionDTO emission_air_pollutionDTO) throws URISyntaxException {
        log.debug("REST request to save Emission_air_pollution : {}", emission_air_pollutionDTO);
        if (emission_air_pollutionDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emission_air_pollution cannot already have an ID")).body(null);
        }
        Emission_air_pollutionDTO result = emission_air_pollutionService.save(emission_air_pollutionDTO);
        return ResponseEntity.created(new URI("/api/emission-air-pollutions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emission-air-pollutions : Updates an existing emission_air_pollution.
     *
     * @param emission_air_pollutionDTO the emission_air_pollutionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emission_air_pollutionDTO,
     * or with status 400 (Bad Request) if the emission_air_pollutionDTO is not valid,
     * or with status 500 (Internal Server Error) if the emission_air_pollutionDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emission-air-pollutions")
    @Timed
    public ResponseEntity<Emission_air_pollutionDTO> updateEmission_air_pollution(@RequestBody Emission_air_pollutionDTO emission_air_pollutionDTO) throws URISyntaxException {
        log.debug("REST request to update Emission_air_pollution : {}", emission_air_pollutionDTO);
        if (emission_air_pollutionDTO.getId() == null) {
            return createEmission_air_pollution(emission_air_pollutionDTO);
        }
        Emission_air_pollutionDTO result = emission_air_pollutionService.save(emission_air_pollutionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emission_air_pollutionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emission-air-pollutions : get all the emission_air_pollutions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emission_air_pollutions in body
     */
    @GetMapping("/emission-air-pollutions")
    @Timed
    public List<Emission_air_pollutionDTO> getAllEmission_air_pollutions() {
        log.debug("REST request to get all Emission_air_pollutions");
        return emission_air_pollutionService.findAll();
    }

    /**
     * GET  /emission-air-pollutions/:id : get the "id" emission_air_pollution.
     *
     * @param id the id of the emission_air_pollutionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emission_air_pollutionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emission-air-pollutions/{id}")
    @Timed
    public ResponseEntity<Emission_air_pollutionDTO> getEmission_air_pollution(@PathVariable String id) {
        log.debug("REST request to get Emission_air_pollution : {}", id);
        Emission_air_pollutionDTO emission_air_pollutionDTO = emission_air_pollutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emission_air_pollutionDTO));
    }

    /**
     * DELETE  /emission-air-pollutions/:id : delete the "id" emission_air_pollution.
     *
     * @param id the id of the emission_air_pollutionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emission-air-pollutions/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmission_air_pollution(@PathVariable String id) {
        log.debug("REST request to delete Emission_air_pollution : {}", id);
        emission_air_pollutionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
