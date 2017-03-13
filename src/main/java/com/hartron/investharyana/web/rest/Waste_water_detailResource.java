package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Waste_water_detailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Waste_water_detailDTO;
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
 * REST controller for managing Waste_water_detail.
 */
@RestController
@RequestMapping("/api")
public class Waste_water_detailResource {

    private final Logger log = LoggerFactory.getLogger(Waste_water_detailResource.class);

    private static final String ENTITY_NAME = "waste_water_detail";
        
    private final Waste_water_detailService waste_water_detailService;

    public Waste_water_detailResource(Waste_water_detailService waste_water_detailService) {
        this.waste_water_detailService = waste_water_detailService;
    }

    /**
     * POST  /waste-water-details : Create a new waste_water_detail.
     *
     * @param waste_water_detailDTO the waste_water_detailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new waste_water_detailDTO, or with status 400 (Bad Request) if the waste_water_detail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/waste-water-details")
    @Timed
    public ResponseEntity<Waste_water_detailDTO> createWaste_water_detail(@RequestBody Waste_water_detailDTO waste_water_detailDTO) throws URISyntaxException {
        log.debug("REST request to save Waste_water_detail : {}", waste_water_detailDTO);
        if (waste_water_detailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new waste_water_detail cannot already have an ID")).body(null);
        }
        Waste_water_detailDTO result = waste_water_detailService.save(waste_water_detailDTO);
        return ResponseEntity.created(new URI("/api/waste-water-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /waste-water-details : Updates an existing waste_water_detail.
     *
     * @param waste_water_detailDTO the waste_water_detailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated waste_water_detailDTO,
     * or with status 400 (Bad Request) if the waste_water_detailDTO is not valid,
     * or with status 500 (Internal Server Error) if the waste_water_detailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/waste-water-details")
    @Timed
    public ResponseEntity<Waste_water_detailDTO> updateWaste_water_detail(@RequestBody Waste_water_detailDTO waste_water_detailDTO) throws URISyntaxException {
        log.debug("REST request to update Waste_water_detail : {}", waste_water_detailDTO);
        if (waste_water_detailDTO.getId() == null) {
            return createWaste_water_detail(waste_water_detailDTO);
        }
        Waste_water_detailDTO result = waste_water_detailService.save(waste_water_detailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, waste_water_detailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /waste-water-details : get all the waste_water_details.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of waste_water_details in body
     */
    @GetMapping("/waste-water-details")
    @Timed
    public List<Waste_water_detailDTO> getAllWaste_water_details() {
        log.debug("REST request to get all Waste_water_details");
        return waste_water_detailService.findAll();
    }

    /**
     * GET  /waste-water-details/:id : get the "id" waste_water_detail.
     *
     * @param id the id of the waste_water_detailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the waste_water_detailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/waste-water-details/{id}")
    @Timed
    public ResponseEntity<Waste_water_detailDTO> getWaste_water_detail(@PathVariable String id) {
        log.debug("REST request to get Waste_water_detail : {}", id);
        Waste_water_detailDTO waste_water_detailDTO = waste_water_detailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(waste_water_detailDTO));
    }

    /**
     * DELETE  /waste-water-details/:id : delete the "id" waste_water_detail.
     *
     * @param id the id of the waste_water_detailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/waste-water-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteWaste_water_detail(@PathVariable String id) {
        log.debug("REST request to delete Waste_water_detail : {}", id);
        waste_water_detailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
