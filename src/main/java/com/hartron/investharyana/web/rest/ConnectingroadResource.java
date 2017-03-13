package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.ConnectingroadService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.ConnectingroadDTO;
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
 * REST controller for managing Connectingroad.
 */
@RestController
@RequestMapping("/api")
public class ConnectingroadResource {

    private final Logger log = LoggerFactory.getLogger(ConnectingroadResource.class);

    private static final String ENTITY_NAME = "connectingroad";
        
    private final ConnectingroadService connectingroadService;

    public ConnectingroadResource(ConnectingroadService connectingroadService) {
        this.connectingroadService = connectingroadService;
    }

    /**
     * POST  /connectingroads : Create a new connectingroad.
     *
     * @param connectingroadDTO the connectingroadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new connectingroadDTO, or with status 400 (Bad Request) if the connectingroad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/connectingroads")
    @Timed
    public ResponseEntity<ConnectingroadDTO> createConnectingroad(@RequestBody ConnectingroadDTO connectingroadDTO) throws URISyntaxException {
        log.debug("REST request to save Connectingroad : {}", connectingroadDTO);
        if (connectingroadDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new connectingroad cannot already have an ID")).body(null);
        }
        ConnectingroadDTO result = connectingroadService.save(connectingroadDTO);
        return ResponseEntity.created(new URI("/api/connectingroads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /connectingroads : Updates an existing connectingroad.
     *
     * @param connectingroadDTO the connectingroadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated connectingroadDTO,
     * or with status 400 (Bad Request) if the connectingroadDTO is not valid,
     * or with status 500 (Internal Server Error) if the connectingroadDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/connectingroads")
    @Timed
    public ResponseEntity<ConnectingroadDTO> updateConnectingroad(@RequestBody ConnectingroadDTO connectingroadDTO) throws URISyntaxException {
        log.debug("REST request to update Connectingroad : {}", connectingroadDTO);
        if (connectingroadDTO.getId() == null) {
            return createConnectingroad(connectingroadDTO);
        }
        ConnectingroadDTO result = connectingroadService.save(connectingroadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, connectingroadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /connectingroads : get all the connectingroads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of connectingroads in body
     */
    @GetMapping("/connectingroads")
    @Timed
    public List<ConnectingroadDTO> getAllConnectingroads() {
        log.debug("REST request to get all Connectingroads");
        return connectingroadService.findAll();
    }

    /**
     * GET  /connectingroads/:id : get the "id" connectingroad.
     *
     * @param id the id of the connectingroadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the connectingroadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/connectingroads/{id}")
    @Timed
    public ResponseEntity<ConnectingroadDTO> getConnectingroad(@PathVariable String id) {
        log.debug("REST request to get Connectingroad : {}", id);
        ConnectingroadDTO connectingroadDTO = connectingroadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(connectingroadDTO));
    }

    /**
     * DELETE  /connectingroads/:id : delete the "id" connectingroad.
     *
     * @param id the id of the connectingroadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/connectingroads/{id}")
    @Timed
    public ResponseEntity<Void> deleteConnectingroad(@PathVariable String id) {
        log.debug("REST request to delete Connectingroad : {}", id);
        connectingroadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
