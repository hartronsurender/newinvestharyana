package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Waste_water_natureService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Waste_water_natureDTO;
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
 * REST controller for managing Waste_water_nature.
 */
@RestController
@RequestMapping("/api")
public class Waste_water_natureResource {

    private final Logger log = LoggerFactory.getLogger(Waste_water_natureResource.class);

    private static final String ENTITY_NAME = "waste_water_nature";
        
    private final Waste_water_natureService waste_water_natureService;

    public Waste_water_natureResource(Waste_water_natureService waste_water_natureService) {
        this.waste_water_natureService = waste_water_natureService;
    }

    /**
     * POST  /waste-water-natures : Create a new waste_water_nature.
     *
     * @param waste_water_natureDTO the waste_water_natureDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new waste_water_natureDTO, or with status 400 (Bad Request) if the waste_water_nature has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/waste-water-natures")
    @Timed
    public ResponseEntity<Waste_water_natureDTO> createWaste_water_nature(@RequestBody Waste_water_natureDTO waste_water_natureDTO) throws URISyntaxException {
        log.debug("REST request to save Waste_water_nature : {}", waste_water_natureDTO);
        if (waste_water_natureDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new waste_water_nature cannot already have an ID")).body(null);
        }
        Waste_water_natureDTO result = waste_water_natureService.save(waste_water_natureDTO);
        return ResponseEntity.created(new URI("/api/waste-water-natures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /waste-water-natures : Updates an existing waste_water_nature.
     *
     * @param waste_water_natureDTO the waste_water_natureDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated waste_water_natureDTO,
     * or with status 400 (Bad Request) if the waste_water_natureDTO is not valid,
     * or with status 500 (Internal Server Error) if the waste_water_natureDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/waste-water-natures")
    @Timed
    public ResponseEntity<Waste_water_natureDTO> updateWaste_water_nature(@RequestBody Waste_water_natureDTO waste_water_natureDTO) throws URISyntaxException {
        log.debug("REST request to update Waste_water_nature : {}", waste_water_natureDTO);
        if (waste_water_natureDTO.getId() == null) {
            return createWaste_water_nature(waste_water_natureDTO);
        }
        Waste_water_natureDTO result = waste_water_natureService.save(waste_water_natureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, waste_water_natureDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /waste-water-natures : get all the waste_water_natures.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of waste_water_natures in body
     */
    @GetMapping("/waste-water-natures")
    @Timed
    public List<Waste_water_natureDTO> getAllWaste_water_natures() {
        log.debug("REST request to get all Waste_water_natures");
        return waste_water_natureService.findAll();
    }

    /**
     * GET  /waste-water-natures/:id : get the "id" waste_water_nature.
     *
     * @param id the id of the waste_water_natureDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the waste_water_natureDTO, or with status 404 (Not Found)
     */
    @GetMapping("/waste-water-natures/{id}")
    @Timed
    public ResponseEntity<Waste_water_natureDTO> getWaste_water_nature(@PathVariable String id) {
        log.debug("REST request to get Waste_water_nature : {}", id);
        Waste_water_natureDTO waste_water_natureDTO = waste_water_natureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(waste_water_natureDTO));
    }

    /**
     * DELETE  /waste-water-natures/:id : delete the "id" waste_water_nature.
     *
     * @param id the id of the waste_water_natureDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/waste-water-natures/{id}")
    @Timed
    public ResponseEntity<Void> deleteWaste_water_nature(@PathVariable String id) {
        log.debug("REST request to delete Waste_water_nature : {}", id);
        waste_water_natureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
