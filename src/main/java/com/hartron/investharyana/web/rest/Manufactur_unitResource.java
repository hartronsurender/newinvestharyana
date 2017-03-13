package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Manufactur_unitService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Manufactur_unitDTO;
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
 * REST controller for managing Manufactur_unit.
 */
@RestController
@RequestMapping("/api")
public class Manufactur_unitResource {

    private final Logger log = LoggerFactory.getLogger(Manufactur_unitResource.class);

    private static final String ENTITY_NAME = "manufactur_unit";
        
    private final Manufactur_unitService manufactur_unitService;

    public Manufactur_unitResource(Manufactur_unitService manufactur_unitService) {
        this.manufactur_unitService = manufactur_unitService;
    }

    /**
     * POST  /manufactur-units : Create a new manufactur_unit.
     *
     * @param manufactur_unitDTO the manufactur_unitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new manufactur_unitDTO, or with status 400 (Bad Request) if the manufactur_unit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/manufactur-units")
    @Timed
    public ResponseEntity<Manufactur_unitDTO> createManufactur_unit(@RequestBody Manufactur_unitDTO manufactur_unitDTO) throws URISyntaxException {
        log.debug("REST request to save Manufactur_unit : {}", manufactur_unitDTO);
        if (manufactur_unitDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new manufactur_unit cannot already have an ID")).body(null);
        }
        Manufactur_unitDTO result = manufactur_unitService.save(manufactur_unitDTO);
        return ResponseEntity.created(new URI("/api/manufactur-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manufactur-units : Updates an existing manufactur_unit.
     *
     * @param manufactur_unitDTO the manufactur_unitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated manufactur_unitDTO,
     * or with status 400 (Bad Request) if the manufactur_unitDTO is not valid,
     * or with status 500 (Internal Server Error) if the manufactur_unitDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/manufactur-units")
    @Timed
    public ResponseEntity<Manufactur_unitDTO> updateManufactur_unit(@RequestBody Manufactur_unitDTO manufactur_unitDTO) throws URISyntaxException {
        log.debug("REST request to update Manufactur_unit : {}", manufactur_unitDTO);
        if (manufactur_unitDTO.getId() == null) {
            return createManufactur_unit(manufactur_unitDTO);
        }
        Manufactur_unitDTO result = manufactur_unitService.save(manufactur_unitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, manufactur_unitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manufactur-units : get all the manufactur_units.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of manufactur_units in body
     */
    @GetMapping("/manufactur-units")
    @Timed
    public List<Manufactur_unitDTO> getAllManufactur_units() {
        log.debug("REST request to get all Manufactur_units");
        return manufactur_unitService.findAll();
    }

    /**
     * GET  /manufactur-units/:id : get the "id" manufactur_unit.
     *
     * @param id the id of the manufactur_unitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the manufactur_unitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/manufactur-units/{id}")
    @Timed
    public ResponseEntity<Manufactur_unitDTO> getManufactur_unit(@PathVariable String id) {
        log.debug("REST request to get Manufactur_unit : {}", id);
        Manufactur_unitDTO manufactur_unitDTO = manufactur_unitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(manufactur_unitDTO));
    }

    /**
     * DELETE  /manufactur-units/:id : delete the "id" manufactur_unit.
     *
     * @param id the id of the manufactur_unitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/manufactur-units/{id}")
    @Timed
    public ResponseEntity<Void> deleteManufactur_unit(@PathVariable String id) {
        log.debug("REST request to delete Manufactur_unit : {}", id);
        manufactur_unitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
