package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Emission_particularsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Emission_particularsDTO;
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
 * REST controller for managing Emission_particulars.
 */
@RestController
@RequestMapping("/api")
public class Emission_particularsResource {

    private final Logger log = LoggerFactory.getLogger(Emission_particularsResource.class);

    private static final String ENTITY_NAME = "emission_particulars";
        
    private final Emission_particularsService emission_particularsService;

    public Emission_particularsResource(Emission_particularsService emission_particularsService) {
        this.emission_particularsService = emission_particularsService;
    }

    /**
     * POST  /emission-particulars : Create a new emission_particulars.
     *
     * @param emission_particularsDTO the emission_particularsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emission_particularsDTO, or with status 400 (Bad Request) if the emission_particulars has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emission-particulars")
    @Timed
    public ResponseEntity<Emission_particularsDTO> createEmission_particulars(@RequestBody Emission_particularsDTO emission_particularsDTO) throws URISyntaxException {
        log.debug("REST request to save Emission_particulars : {}", emission_particularsDTO);
        if (emission_particularsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emission_particulars cannot already have an ID")).body(null);
        }
        Emission_particularsDTO result = emission_particularsService.save(emission_particularsDTO);
        return ResponseEntity.created(new URI("/api/emission-particulars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emission-particulars : Updates an existing emission_particulars.
     *
     * @param emission_particularsDTO the emission_particularsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emission_particularsDTO,
     * or with status 400 (Bad Request) if the emission_particularsDTO is not valid,
     * or with status 500 (Internal Server Error) if the emission_particularsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emission-particulars")
    @Timed
    public ResponseEntity<Emission_particularsDTO> updateEmission_particulars(@RequestBody Emission_particularsDTO emission_particularsDTO) throws URISyntaxException {
        log.debug("REST request to update Emission_particulars : {}", emission_particularsDTO);
        if (emission_particularsDTO.getId() == null) {
            return createEmission_particulars(emission_particularsDTO);
        }
        Emission_particularsDTO result = emission_particularsService.save(emission_particularsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emission_particularsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emission-particulars : get all the emission_particulars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emission_particulars in body
     */
    @GetMapping("/emission-particulars")
    @Timed
    public List<Emission_particularsDTO> getAllEmission_particulars() {
        log.debug("REST request to get all Emission_particulars");
        return emission_particularsService.findAll();
    }

    /**
     * GET  /emission-particulars/:id : get the "id" emission_particulars.
     *
     * @param id the id of the emission_particularsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emission_particularsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emission-particulars/{id}")
    @Timed
    public ResponseEntity<Emission_particularsDTO> getEmission_particulars(@PathVariable String id) {
        log.debug("REST request to get Emission_particulars : {}", id);
        Emission_particularsDTO emission_particularsDTO = emission_particularsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emission_particularsDTO));
    }

    /**
     * DELETE  /emission-particulars/:id : delete the "id" emission_particulars.
     *
     * @param id the id of the emission_particularsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emission-particulars/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmission_particulars(@PathVariable String id) {
        log.debug("REST request to delete Emission_particulars : {}", id);
        emission_particularsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
