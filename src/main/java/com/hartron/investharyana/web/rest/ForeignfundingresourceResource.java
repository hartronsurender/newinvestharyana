package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ForeignfundingresourceService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ForeignfundingresourceDTO;
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
 * REST controller for managing Foreignfundingresource.
 */
@RestController
@RequestMapping("/api")
public class ForeignfundingresourceResource {

    private final Logger log = LoggerFactory.getLogger(ForeignfundingresourceResource.class);

    private static final String ENTITY_NAME = "foreignfundingresource";
        
    private final ForeignfundingresourceService foreignfundingresourceService;

    public ForeignfundingresourceResource(ForeignfundingresourceService foreignfundingresourceService) {
        this.foreignfundingresourceService = foreignfundingresourceService;
    }

    /**
     * POST  /foreignfundingresources : Create a new foreignfundingresource.
     *
     * @param foreignfundingresourceDTO the foreignfundingresourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new foreignfundingresourceDTO, or with status 400 (Bad Request) if the foreignfundingresource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/foreignfundingresources")
    @Timed
    public ResponseEntity<ForeignfundingresourceDTO> createForeignfundingresource(@RequestBody ForeignfundingresourceDTO foreignfundingresourceDTO) throws URISyntaxException {
        log.debug("REST request to save Foreignfundingresource : {}", foreignfundingresourceDTO);
        if (foreignfundingresourceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new foreignfundingresource cannot already have an ID")).body(null);
        }
        ForeignfundingresourceDTO result = foreignfundingresourceService.save(foreignfundingresourceDTO);
        return ResponseEntity.created(new URI("/api/foreignfundingresources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /foreignfundingresources : Updates an existing foreignfundingresource.
     *
     * @param foreignfundingresourceDTO the foreignfundingresourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated foreignfundingresourceDTO,
     * or with status 400 (Bad Request) if the foreignfundingresourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the foreignfundingresourceDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/foreignfundingresources")
    @Timed
    public ResponseEntity<ForeignfundingresourceDTO> updateForeignfundingresource(@RequestBody ForeignfundingresourceDTO foreignfundingresourceDTO) throws URISyntaxException {
        log.debug("REST request to update Foreignfundingresource : {}", foreignfundingresourceDTO);
        if (foreignfundingresourceDTO.getId() == null) {
            return createForeignfundingresource(foreignfundingresourceDTO);
        }
        ForeignfundingresourceDTO result = foreignfundingresourceService.save(foreignfundingresourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, foreignfundingresourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /foreignfundingresources : get all the foreignfundingresources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of foreignfundingresources in body
     */
    @GetMapping("/foreignfundingresources")
    @Timed
    public List<ForeignfundingresourceDTO> getAllForeignfundingresources() {
        log.debug("REST request to get all Foreignfundingresources");
        return foreignfundingresourceService.findAll();
    }

    /**
     * GET  /foreignfundingresources/:id : get the "id" foreignfundingresource.
     *
     * @param id the id of the foreignfundingresourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the foreignfundingresourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/foreignfundingresources/{id}")
    @Timed
    public ResponseEntity<ForeignfundingresourceDTO> getForeignfundingresource(@PathVariable String id) {
        log.debug("REST request to get Foreignfundingresource : {}", id);
        ForeignfundingresourceDTO foreignfundingresourceDTO = foreignfundingresourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(foreignfundingresourceDTO));
    }

    /**
     * DELETE  /foreignfundingresources/:id : delete the "id" foreignfundingresource.
     *
     * @param id the id of the foreignfundingresourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/foreignfundingresources/{id}")
    @Timed
    public ResponseEntity<Void> deleteForeignfundingresource(@PathVariable String id) {
        log.debug("REST request to delete Foreignfundingresource : {}", id);
        foreignfundingresourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
