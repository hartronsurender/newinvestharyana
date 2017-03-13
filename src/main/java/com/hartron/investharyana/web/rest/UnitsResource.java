package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.UnitsService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.UnitsDTO;
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
 * REST controller for managing Units.
 */
@RestController
@RequestMapping("/api")
public class UnitsResource {

    private final Logger log = LoggerFactory.getLogger(UnitsResource.class);

    private static final String ENTITY_NAME = "units";
        
    private final UnitsService unitsService;

    public UnitsResource(UnitsService unitsService) {
        this.unitsService = unitsService;
    }

    /**
     * POST  /units : Create a new units.
     *
     * @param unitsDTO the unitsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new unitsDTO, or with status 400 (Bad Request) if the units has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/units")
    @Timed
    public ResponseEntity<UnitsDTO> createUnits(@RequestBody UnitsDTO unitsDTO) throws URISyntaxException {
        log.debug("REST request to save Units : {}", unitsDTO);
        if (unitsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new units cannot already have an ID")).body(null);
        }
        UnitsDTO result = unitsService.save(unitsDTO);
        return ResponseEntity.created(new URI("/api/units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /units : Updates an existing units.
     *
     * @param unitsDTO the unitsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated unitsDTO,
     * or with status 400 (Bad Request) if the unitsDTO is not valid,
     * or with status 500 (Internal Server Error) if the unitsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/units")
    @Timed
    public ResponseEntity<UnitsDTO> updateUnits(@RequestBody UnitsDTO unitsDTO) throws URISyntaxException {
        log.debug("REST request to update Units : {}", unitsDTO);
        if (unitsDTO.getId() == null) {
            return createUnits(unitsDTO);
        }
        UnitsDTO result = unitsService.save(unitsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, unitsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /units : get all the units.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of units in body
     */
    @GetMapping("/units")
    @Timed
    public List<UnitsDTO> getAllUnits() {
        log.debug("REST request to get all Units");
        return unitsService.findAll();
    }

    /**
     * GET  /units/:id : get the "id" units.
     *
     * @param id the id of the unitsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the unitsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/units/{id}")
    @Timed
    public ResponseEntity<UnitsDTO> getUnits(@PathVariable String id) {
        log.debug("REST request to get Units : {}", id);
        UnitsDTO unitsDTO = unitsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(unitsDTO));
    }

    /**
     * DELETE  /units/:id : delete the "id" units.
     *
     * @param id the id of the unitsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/units/{id}")
    @Timed
    public ResponseEntity<Void> deleteUnits(@PathVariable String id) {
        log.debug("REST request to delete Units : {}", id);
        unitsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
