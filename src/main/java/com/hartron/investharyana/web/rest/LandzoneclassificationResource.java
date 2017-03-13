package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.LandzoneclassificationService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.LandzoneclassificationDTO;
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
 * REST controller for managing Landzoneclassification.
 */
@RestController
@RequestMapping("/api")
public class LandzoneclassificationResource {

    private final Logger log = LoggerFactory.getLogger(LandzoneclassificationResource.class);

    private static final String ENTITY_NAME = "landzoneclassification";
        
    private final LandzoneclassificationService landzoneclassificationService;

    public LandzoneclassificationResource(LandzoneclassificationService landzoneclassificationService) {
        this.landzoneclassificationService = landzoneclassificationService;
    }

    /**
     * POST  /landzoneclassifications : Create a new landzoneclassification.
     *
     * @param landzoneclassificationDTO the landzoneclassificationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new landzoneclassificationDTO, or with status 400 (Bad Request) if the landzoneclassification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/landzoneclassifications")
    @Timed
    public ResponseEntity<LandzoneclassificationDTO> createLandzoneclassification(@RequestBody LandzoneclassificationDTO landzoneclassificationDTO) throws URISyntaxException {
        log.debug("REST request to save Landzoneclassification : {}", landzoneclassificationDTO);
        if (landzoneclassificationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new landzoneclassification cannot already have an ID")).body(null);
        }
        LandzoneclassificationDTO result = landzoneclassificationService.save(landzoneclassificationDTO);
        return ResponseEntity.created(new URI("/api/landzoneclassifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /landzoneclassifications : Updates an existing landzoneclassification.
     *
     * @param landzoneclassificationDTO the landzoneclassificationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated landzoneclassificationDTO,
     * or with status 400 (Bad Request) if the landzoneclassificationDTO is not valid,
     * or with status 500 (Internal Server Error) if the landzoneclassificationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/landzoneclassifications")
    @Timed
    public ResponseEntity<LandzoneclassificationDTO> updateLandzoneclassification(@RequestBody LandzoneclassificationDTO landzoneclassificationDTO) throws URISyntaxException {
        log.debug("REST request to update Landzoneclassification : {}", landzoneclassificationDTO);
        if (landzoneclassificationDTO.getId() == null) {
            return createLandzoneclassification(landzoneclassificationDTO);
        }
        LandzoneclassificationDTO result = landzoneclassificationService.save(landzoneclassificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, landzoneclassificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /landzoneclassifications : get all the landzoneclassifications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of landzoneclassifications in body
     */
    @GetMapping("/landzoneclassifications")
    @Timed
    public List<LandzoneclassificationDTO> getAllLandzoneclassifications() {
        log.debug("REST request to get all Landzoneclassifications");
        return landzoneclassificationService.findAll();
    }

    /**
     * GET  /landzoneclassifications/:id : get the "id" landzoneclassification.
     *
     * @param id the id of the landzoneclassificationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the landzoneclassificationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/landzoneclassifications/{id}")
    @Timed
    public ResponseEntity<LandzoneclassificationDTO> getLandzoneclassification(@PathVariable String id) {
        log.debug("REST request to get Landzoneclassification : {}", id);
        LandzoneclassificationDTO landzoneclassificationDTO = landzoneclassificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(landzoneclassificationDTO));
    }

    /**
     * DELETE  /landzoneclassifications/:id : delete the "id" landzoneclassification.
     *
     * @param id the id of the landzoneclassificationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/landzoneclassifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteLandzoneclassification(@PathVariable String id) {
        log.debug("REST request to delete Landzoneclassification : {}", id);
        landzoneclassificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
