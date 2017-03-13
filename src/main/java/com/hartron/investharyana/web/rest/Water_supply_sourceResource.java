package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Water_supply_sourceService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Water_supply_sourceDTO;
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
 * REST controller for managing Water_supply_source.
 */
@RestController
@RequestMapping("/api")
public class Water_supply_sourceResource {

    private final Logger log = LoggerFactory.getLogger(Water_supply_sourceResource.class);

    private static final String ENTITY_NAME = "water_supply_source";
        
    private final Water_supply_sourceService water_supply_sourceService;

    public Water_supply_sourceResource(Water_supply_sourceService water_supply_sourceService) {
        this.water_supply_sourceService = water_supply_sourceService;
    }

    /**
     * POST  /water-supply-sources : Create a new water_supply_source.
     *
     * @param water_supply_sourceDTO the water_supply_sourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new water_supply_sourceDTO, or with status 400 (Bad Request) if the water_supply_source has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/water-supply-sources")
    @Timed
    public ResponseEntity<Water_supply_sourceDTO> createWater_supply_source(@RequestBody Water_supply_sourceDTO water_supply_sourceDTO) throws URISyntaxException {
        log.debug("REST request to save Water_supply_source : {}", water_supply_sourceDTO);
        if (water_supply_sourceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new water_supply_source cannot already have an ID")).body(null);
        }
        Water_supply_sourceDTO result = water_supply_sourceService.save(water_supply_sourceDTO);
        return ResponseEntity.created(new URI("/api/water-supply-sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /water-supply-sources : Updates an existing water_supply_source.
     *
     * @param water_supply_sourceDTO the water_supply_sourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated water_supply_sourceDTO,
     * or with status 400 (Bad Request) if the water_supply_sourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the water_supply_sourceDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/water-supply-sources")
    @Timed
    public ResponseEntity<Water_supply_sourceDTO> updateWater_supply_source(@RequestBody Water_supply_sourceDTO water_supply_sourceDTO) throws URISyntaxException {
        log.debug("REST request to update Water_supply_source : {}", water_supply_sourceDTO);
        if (water_supply_sourceDTO.getId() == null) {
            return createWater_supply_source(water_supply_sourceDTO);
        }
        Water_supply_sourceDTO result = water_supply_sourceService.save(water_supply_sourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, water_supply_sourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /water-supply-sources : get all the water_supply_sources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of water_supply_sources in body
     */
    @GetMapping("/water-supply-sources")
    @Timed
    public List<Water_supply_sourceDTO> getAllWater_supply_sources() {
        log.debug("REST request to get all Water_supply_sources");
        return water_supply_sourceService.findAll();
    }

    /**
     * GET  /water-supply-sources/:id : get the "id" water_supply_source.
     *
     * @param id the id of the water_supply_sourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the water_supply_sourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/water-supply-sources/{id}")
    @Timed
    public ResponseEntity<Water_supply_sourceDTO> getWater_supply_source(@PathVariable String id) {
        log.debug("REST request to get Water_supply_source : {}", id);
        Water_supply_sourceDTO water_supply_sourceDTO = water_supply_sourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(water_supply_sourceDTO));
    }

    /**
     * DELETE  /water-supply-sources/:id : delete the "id" water_supply_source.
     *
     * @param id the id of the water_supply_sourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/water-supply-sources/{id}")
    @Timed
    public ResponseEntity<Void> deleteWater_supply_source(@PathVariable String id) {
        log.debug("REST request to delete Water_supply_source : {}", id);
        water_supply_sourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
