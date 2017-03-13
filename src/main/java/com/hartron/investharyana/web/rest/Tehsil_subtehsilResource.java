package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Tehsil_subtehsilService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Tehsil_subtehsilDTO;
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
 * REST controller for managing Tehsil_subtehsil.
 */
@RestController
@RequestMapping("/api")
public class Tehsil_subtehsilResource {

    private final Logger log = LoggerFactory.getLogger(Tehsil_subtehsilResource.class);

    private static final String ENTITY_NAME = "tehsil_subtehsil";
        
    private final Tehsil_subtehsilService tehsil_subtehsilService;

    public Tehsil_subtehsilResource(Tehsil_subtehsilService tehsil_subtehsilService) {
        this.tehsil_subtehsilService = tehsil_subtehsilService;
    }

    /**
     * POST  /tehsil-subtehsils : Create a new tehsil_subtehsil.
     *
     * @param tehsil_subtehsilDTO the tehsil_subtehsilDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tehsil_subtehsilDTO, or with status 400 (Bad Request) if the tehsil_subtehsil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tehsil-subtehsils")
    @Timed
    public ResponseEntity<Tehsil_subtehsilDTO> createTehsil_subtehsil(@RequestBody Tehsil_subtehsilDTO tehsil_subtehsilDTO) throws URISyntaxException {
        log.debug("REST request to save Tehsil_subtehsil : {}", tehsil_subtehsilDTO);
        if (tehsil_subtehsilDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tehsil_subtehsil cannot already have an ID")).body(null);
        }
        Tehsil_subtehsilDTO result = tehsil_subtehsilService.save(tehsil_subtehsilDTO);
        return ResponseEntity.created(new URI("/api/tehsil-subtehsils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tehsil-subtehsils : Updates an existing tehsil_subtehsil.
     *
     * @param tehsil_subtehsilDTO the tehsil_subtehsilDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tehsil_subtehsilDTO,
     * or with status 400 (Bad Request) if the tehsil_subtehsilDTO is not valid,
     * or with status 500 (Internal Server Error) if the tehsil_subtehsilDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tehsil-subtehsils")
    @Timed
    public ResponseEntity<Tehsil_subtehsilDTO> updateTehsil_subtehsil(@RequestBody Tehsil_subtehsilDTO tehsil_subtehsilDTO) throws URISyntaxException {
        log.debug("REST request to update Tehsil_subtehsil : {}", tehsil_subtehsilDTO);
        if (tehsil_subtehsilDTO.getId() == null) {
            return createTehsil_subtehsil(tehsil_subtehsilDTO);
        }
        Tehsil_subtehsilDTO result = tehsil_subtehsilService.save(tehsil_subtehsilDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tehsil_subtehsilDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tehsil-subtehsils : get all the tehsil_subtehsils.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tehsil_subtehsils in body
     */
    @GetMapping("/tehsil-subtehsils")
    @Timed
    public List<Tehsil_subtehsilDTO> getAllTehsil_subtehsils() {
        log.debug("REST request to get all Tehsil_subtehsils");
        return tehsil_subtehsilService.findAll();
    }

    /**
     * GET  /tehsil-subtehsils/:id : get the "id" tehsil_subtehsil.
     *
     * @param id the id of the tehsil_subtehsilDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tehsil_subtehsilDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tehsil-subtehsils/{id}")
    @Timed
    public ResponseEntity<Tehsil_subtehsilDTO> getTehsil_subtehsil(@PathVariable String id) {
        log.debug("REST request to get Tehsil_subtehsil : {}", id);
        Tehsil_subtehsilDTO tehsil_subtehsilDTO = tehsil_subtehsilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tehsil_subtehsilDTO));
    }

    /**
     * DELETE  /tehsil-subtehsils/:id : delete the "id" tehsil_subtehsil.
     *
     * @param id the id of the tehsil_subtehsilDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tehsil-subtehsils/{id}")
    @Timed
    public ResponseEntity<Void> deleteTehsil_subtehsil(@PathVariable String id) {
        log.debug("REST request to delete Tehsil_subtehsil : {}", id);
        tehsil_subtehsilService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
