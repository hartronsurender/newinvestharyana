package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Waste_water_disposal_modeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Waste_water_disposal_modeDTO;
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
 * REST controller for managing Waste_water_disposal_mode.
 */
@RestController
@RequestMapping("/api")
public class Waste_water_disposal_modeResource {

    private final Logger log = LoggerFactory.getLogger(Waste_water_disposal_modeResource.class);

    private static final String ENTITY_NAME = "waste_water_disposal_mode";
        
    private final Waste_water_disposal_modeService waste_water_disposal_modeService;

    public Waste_water_disposal_modeResource(Waste_water_disposal_modeService waste_water_disposal_modeService) {
        this.waste_water_disposal_modeService = waste_water_disposal_modeService;
    }

    /**
     * POST  /waste-water-disposal-modes : Create a new waste_water_disposal_mode.
     *
     * @param waste_water_disposal_modeDTO the waste_water_disposal_modeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new waste_water_disposal_modeDTO, or with status 400 (Bad Request) if the waste_water_disposal_mode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/waste-water-disposal-modes")
    @Timed
    public ResponseEntity<Waste_water_disposal_modeDTO> createWaste_water_disposal_mode(@RequestBody Waste_water_disposal_modeDTO waste_water_disposal_modeDTO) throws URISyntaxException {
        log.debug("REST request to save Waste_water_disposal_mode : {}", waste_water_disposal_modeDTO);
        if (waste_water_disposal_modeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new waste_water_disposal_mode cannot already have an ID")).body(null);
        }
        Waste_water_disposal_modeDTO result = waste_water_disposal_modeService.save(waste_water_disposal_modeDTO);
        return ResponseEntity.created(new URI("/api/waste-water-disposal-modes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /waste-water-disposal-modes : Updates an existing waste_water_disposal_mode.
     *
     * @param waste_water_disposal_modeDTO the waste_water_disposal_modeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated waste_water_disposal_modeDTO,
     * or with status 400 (Bad Request) if the waste_water_disposal_modeDTO is not valid,
     * or with status 500 (Internal Server Error) if the waste_water_disposal_modeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/waste-water-disposal-modes")
    @Timed
    public ResponseEntity<Waste_water_disposal_modeDTO> updateWaste_water_disposal_mode(@RequestBody Waste_water_disposal_modeDTO waste_water_disposal_modeDTO) throws URISyntaxException {
        log.debug("REST request to update Waste_water_disposal_mode : {}", waste_water_disposal_modeDTO);
        if (waste_water_disposal_modeDTO.getId() == null) {
            return createWaste_water_disposal_mode(waste_water_disposal_modeDTO);
        }
        Waste_water_disposal_modeDTO result = waste_water_disposal_modeService.save(waste_water_disposal_modeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, waste_water_disposal_modeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /waste-water-disposal-modes : get all the waste_water_disposal_modes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of waste_water_disposal_modes in body
     */
    @GetMapping("/waste-water-disposal-modes")
    @Timed
    public List<Waste_water_disposal_modeDTO> getAllWaste_water_disposal_modes() {
        log.debug("REST request to get all Waste_water_disposal_modes");
        return waste_water_disposal_modeService.findAll();
    }

    /**
     * GET  /waste-water-disposal-modes/:id : get the "id" waste_water_disposal_mode.
     *
     * @param id the id of the waste_water_disposal_modeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the waste_water_disposal_modeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/waste-water-disposal-modes/{id}")
    @Timed
    public ResponseEntity<Waste_water_disposal_modeDTO> getWaste_water_disposal_mode(@PathVariable String id) {
        log.debug("REST request to get Waste_water_disposal_mode : {}", id);
        Waste_water_disposal_modeDTO waste_water_disposal_modeDTO = waste_water_disposal_modeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(waste_water_disposal_modeDTO));
    }

    /**
     * DELETE  /waste-water-disposal-modes/:id : delete the "id" waste_water_disposal_mode.
     *
     * @param id the id of the waste_water_disposal_modeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/waste-water-disposal-modes/{id}")
    @Timed
    public ResponseEntity<Void> deleteWaste_water_disposal_mode(@PathVariable String id) {
        log.debug("REST request to delete Waste_water_disposal_mode : {}", id);
        waste_water_disposal_modeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
