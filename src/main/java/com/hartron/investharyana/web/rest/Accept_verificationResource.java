package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Accept_verificationService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Accept_verificationDTO;
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
 * REST controller for managing Accept_verification.
 */
@RestController
@RequestMapping("/api")
public class Accept_verificationResource {

    private final Logger log = LoggerFactory.getLogger(Accept_verificationResource.class);

    private static final String ENTITY_NAME = "accept_verification";
        
    private final Accept_verificationService accept_verificationService;

    public Accept_verificationResource(Accept_verificationService accept_verificationService) {
        this.accept_verificationService = accept_verificationService;
    }

    /**
     * POST  /accept-verifications : Create a new accept_verification.
     *
     * @param accept_verificationDTO the accept_verificationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accept_verificationDTO, or with status 400 (Bad Request) if the accept_verification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/accept-verifications")
    @Timed
    public ResponseEntity<Accept_verificationDTO> createAccept_verification(@RequestBody Accept_verificationDTO accept_verificationDTO) throws URISyntaxException {
        log.debug("REST request to save Accept_verification : {}", accept_verificationDTO);
        if (accept_verificationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new accept_verification cannot already have an ID")).body(null);
        }
        Accept_verificationDTO result = accept_verificationService.save(accept_verificationDTO);
        return ResponseEntity.created(new URI("/api/accept-verifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /accept-verifications : Updates an existing accept_verification.
     *
     * @param accept_verificationDTO the accept_verificationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accept_verificationDTO,
     * or with status 400 (Bad Request) if the accept_verificationDTO is not valid,
     * or with status 500 (Internal Server Error) if the accept_verificationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/accept-verifications")
    @Timed
    public ResponseEntity<Accept_verificationDTO> updateAccept_verification(@RequestBody Accept_verificationDTO accept_verificationDTO) throws URISyntaxException {
        log.debug("REST request to update Accept_verification : {}", accept_verificationDTO);
        if (accept_verificationDTO.getId() == null) {
            return createAccept_verification(accept_verificationDTO);
        }
        Accept_verificationDTO result = accept_verificationService.save(accept_verificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accept_verificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /accept-verifications : get all the accept_verifications.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of accept_verifications in body
     */
    @GetMapping("/accept-verifications")
    @Timed
    public List<Accept_verificationDTO> getAllAccept_verifications() {
        log.debug("REST request to get all Accept_verifications");
        return accept_verificationService.findAll();
    }

    /**
     * GET  /accept-verifications/:id : get the "id" accept_verification.
     *
     * @param id the id of the accept_verificationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accept_verificationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/accept-verifications/{id}")
    @Timed
    public ResponseEntity<Accept_verificationDTO> getAccept_verification(@PathVariable String id) {
        log.debug("REST request to get Accept_verification : {}", id);
        Accept_verificationDTO accept_verificationDTO = accept_verificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(accept_verificationDTO));
    }

    /**
     * DELETE  /accept-verifications/:id : delete the "id" accept_verification.
     *
     * @param id the id of the accept_verificationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/accept-verifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccept_verification(@PathVariable String id) {
        log.debug("REST request to delete Accept_verification : {}", id);
        accept_verificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
