package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Disposal_for_dischargeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Disposal_for_dischargeDTO;
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
 * REST controller for managing Disposal_for_discharge.
 */
@RestController
@RequestMapping("/api")
public class Disposal_for_dischargeResource {

    private final Logger log = LoggerFactory.getLogger(Disposal_for_dischargeResource.class);

    private static final String ENTITY_NAME = "disposal_for_discharge";
        
    private final Disposal_for_dischargeService disposal_for_dischargeService;

    public Disposal_for_dischargeResource(Disposal_for_dischargeService disposal_for_dischargeService) {
        this.disposal_for_dischargeService = disposal_for_dischargeService;
    }

    /**
     * POST  /disposal-for-discharges : Create a new disposal_for_discharge.
     *
     * @param disposal_for_dischargeDTO the disposal_for_dischargeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disposal_for_dischargeDTO, or with status 400 (Bad Request) if the disposal_for_discharge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disposal-for-discharges")
    @Timed
    public ResponseEntity<Disposal_for_dischargeDTO> createDisposal_for_discharge(@RequestBody Disposal_for_dischargeDTO disposal_for_dischargeDTO) throws URISyntaxException {
        log.debug("REST request to save Disposal_for_discharge : {}", disposal_for_dischargeDTO);
        if (disposal_for_dischargeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new disposal_for_discharge cannot already have an ID")).body(null);
        }
        Disposal_for_dischargeDTO result = disposal_for_dischargeService.save(disposal_for_dischargeDTO);
        return ResponseEntity.created(new URI("/api/disposal-for-discharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disposal-for-discharges : Updates an existing disposal_for_discharge.
     *
     * @param disposal_for_dischargeDTO the disposal_for_dischargeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disposal_for_dischargeDTO,
     * or with status 400 (Bad Request) if the disposal_for_dischargeDTO is not valid,
     * or with status 500 (Internal Server Error) if the disposal_for_dischargeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disposal-for-discharges")
    @Timed
    public ResponseEntity<Disposal_for_dischargeDTO> updateDisposal_for_discharge(@RequestBody Disposal_for_dischargeDTO disposal_for_dischargeDTO) throws URISyntaxException {
        log.debug("REST request to update Disposal_for_discharge : {}", disposal_for_dischargeDTO);
        if (disposal_for_dischargeDTO.getId() == null) {
            return createDisposal_for_discharge(disposal_for_dischargeDTO);
        }
        Disposal_for_dischargeDTO result = disposal_for_dischargeService.save(disposal_for_dischargeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disposal_for_dischargeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disposal-for-discharges : get all the disposal_for_discharges.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disposal_for_discharges in body
     */
    @GetMapping("/disposal-for-discharges")
    @Timed
    public List<Disposal_for_dischargeDTO> getAllDisposal_for_discharges() {
        log.debug("REST request to get all Disposal_for_discharges");
        return disposal_for_dischargeService.findAll();
    }

    /**
     * GET  /disposal-for-discharges/:id : get the "id" disposal_for_discharge.
     *
     * @param id the id of the disposal_for_dischargeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disposal_for_dischargeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/disposal-for-discharges/{id}")
    @Timed
    public ResponseEntity<Disposal_for_dischargeDTO> getDisposal_for_discharge(@PathVariable String id) {
        log.debug("REST request to get Disposal_for_discharge : {}", id);
        Disposal_for_dischargeDTO disposal_for_dischargeDTO = disposal_for_dischargeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(disposal_for_dischargeDTO));
    }

    /**
     * DELETE  /disposal-for-discharges/:id : delete the "id" disposal_for_discharge.
     *
     * @param id the id of the disposal_for_dischargeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disposal-for-discharges/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisposal_for_discharge(@PathVariable String id) {
        log.debug("REST request to delete Disposal_for_discharge : {}", id);
        disposal_for_dischargeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
