package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.EmissiondetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.EmissiondetailDTO;
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
 * REST controller for managing Emissiondetail.
 */
@RestController
@RequestMapping("/api")
public class EmissiondetailResource {

    private final Logger log = LoggerFactory.getLogger(EmissiondetailResource.class);

    private static final String ENTITY_NAME = "emissiondetail";
        
    private final EmissiondetailService emissiondetailService;

    public EmissiondetailResource(EmissiondetailService emissiondetailService) {
        this.emissiondetailService = emissiondetailService;
    }

    /**
     * POST  /emissiondetails : Create a new emissiondetail.
     *
     * @param emissiondetailDTO the emissiondetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emissiondetailDTO, or with status 400 (Bad Request) if the emissiondetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emissiondetails")
    @Timed
    public ResponseEntity<EmissiondetailDTO> createEmissiondetail(@RequestBody EmissiondetailDTO emissiondetailDTO) throws URISyntaxException {
        log.debug("REST request to save Emissiondetail : {}", emissiondetailDTO);
        if (emissiondetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new emissiondetail cannot already have an ID")).body(null);
        }
        EmissiondetailDTO result = emissiondetailService.save(emissiondetailDTO);
        return ResponseEntity.created(new URI("/api/emissiondetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emissiondetails : Updates an existing emissiondetail.
     *
     * @param emissiondetailDTO the emissiondetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emissiondetailDTO,
     * or with status 400 (Bad Request) if the emissiondetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the emissiondetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emissiondetails")
    @Timed
    public ResponseEntity<EmissiondetailDTO> updateEmissiondetail(@RequestBody EmissiondetailDTO emissiondetailDTO) throws URISyntaxException {
        log.debug("REST request to update Emissiondetail : {}", emissiondetailDTO);
        if (emissiondetailDTO.getId() == null) {
            return createEmissiondetail(emissiondetailDTO);
        }
        EmissiondetailDTO result = emissiondetailService.save(emissiondetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emissiondetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emissiondetails : get all the emissiondetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emissiondetails in body
     */
    @GetMapping("/emissiondetails")
    @Timed
    public List<EmissiondetailDTO> getAllEmissiondetails() {
        log.debug("REST request to get all Emissiondetails");
        return emissiondetailService.findAll();
    }

    /**
     * GET  /emissiondetails/:id : get the "id" emissiondetail.
     *
     * @param id the id of the emissiondetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emissiondetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emissiondetails/{id}")
    @Timed
    public ResponseEntity<EmissiondetailDTO> getEmissiondetail(@PathVariable String id) {
        log.debug("REST request to get Emissiondetail : {}", id);
        EmissiondetailDTO emissiondetailDTO = emissiondetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emissiondetailDTO));
    }

    /**
     * DELETE  /emissiondetails/:id : delete the "id" emissiondetail.
     *
     * @param id the id of the emissiondetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emissiondetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmissiondetail(@PathVariable String id) {
        log.debug("REST request to delete Emissiondetail : {}", id);
        emissiondetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
