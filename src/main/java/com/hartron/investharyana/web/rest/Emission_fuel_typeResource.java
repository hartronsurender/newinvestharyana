package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Emission_fuel_typeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Emission_fuel_typeDTO;
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
 * REST controller for managing Emission_fuel_type.
 */
@RestController
@RequestMapping("/api")
public class Emission_fuel_typeResource {

    private final Logger log = LoggerFactory.getLogger(Emission_fuel_typeResource.class);

    private static final String ENTITY_NAME = "emission_fuel_type";
        
    private final Emission_fuel_typeService emission_fuel_typeService;

    public Emission_fuel_typeResource(Emission_fuel_typeService emission_fuel_typeService) {
        this.emission_fuel_typeService = emission_fuel_typeService;
    }

    /**
     * POST  /emission-fuel-types : Create a new emission_fuel_type.
     *
     * @param emission_fuel_typeDTO the emission_fuel_typeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emission_fuel_typeDTO, or with status 400 (Bad Request) if the emission_fuel_type has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emission-fuel-types")
    @Timed
    public ResponseEntity<Emission_fuel_typeDTO> createEmission_fuel_type(@RequestBody Emission_fuel_typeDTO emission_fuel_typeDTO) throws URISyntaxException {
        log.debug("REST request to save Emission_fuel_type : {}", emission_fuel_typeDTO);
        if (emission_fuel_typeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emission_fuel_type cannot already have an ID")).body(null);
        }
        Emission_fuel_typeDTO result = emission_fuel_typeService.save(emission_fuel_typeDTO);
        return ResponseEntity.created(new URI("/api/emission-fuel-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emission-fuel-types : Updates an existing emission_fuel_type.
     *
     * @param emission_fuel_typeDTO the emission_fuel_typeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emission_fuel_typeDTO,
     * or with status 400 (Bad Request) if the emission_fuel_typeDTO is not valid,
     * or with status 500 (Internal Server Error) if the emission_fuel_typeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emission-fuel-types")
    @Timed
    public ResponseEntity<Emission_fuel_typeDTO> updateEmission_fuel_type(@RequestBody Emission_fuel_typeDTO emission_fuel_typeDTO) throws URISyntaxException {
        log.debug("REST request to update Emission_fuel_type : {}", emission_fuel_typeDTO);
        if (emission_fuel_typeDTO.getId() == null) {
            return createEmission_fuel_type(emission_fuel_typeDTO);
        }
        Emission_fuel_typeDTO result = emission_fuel_typeService.save(emission_fuel_typeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emission_fuel_typeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emission-fuel-types : get all the emission_fuel_types.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emission_fuel_types in body
     */
    @GetMapping("/emission-fuel-types")
    @Timed
    public List<Emission_fuel_typeDTO> getAllEmission_fuel_types() {
        log.debug("REST request to get all Emission_fuel_types");
        return emission_fuel_typeService.findAll();
    }

    /**
     * GET  /emission-fuel-types/:id : get the "id" emission_fuel_type.
     *
     * @param id the id of the emission_fuel_typeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emission_fuel_typeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emission-fuel-types/{id}")
    @Timed
    public ResponseEntity<Emission_fuel_typeDTO> getEmission_fuel_type(@PathVariable String id) {
        log.debug("REST request to get Emission_fuel_type : {}", id);
        Emission_fuel_typeDTO emission_fuel_typeDTO = emission_fuel_typeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emission_fuel_typeDTO));
    }

    /**
     * DELETE  /emission-fuel-types/:id : delete the "id" emission_fuel_type.
     *
     * @param id the id of the emission_fuel_typeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emission-fuel-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmission_fuel_type(@PathVariable String id) {
        log.debug("REST request to delete Emission_fuel_type : {}", id);
        emission_fuel_typeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
