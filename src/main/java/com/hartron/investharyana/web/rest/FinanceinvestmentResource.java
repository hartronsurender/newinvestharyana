package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.FinanceinvestmentService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.FinanceinvestmentDTO;
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
 * REST controller for managing Financeinvestment.
 */
@RestController
@RequestMapping("/api")
public class FinanceinvestmentResource {

    private final Logger log = LoggerFactory.getLogger(FinanceinvestmentResource.class);

    private static final String ENTITY_NAME = "financeinvestment";
        
    private final FinanceinvestmentService financeinvestmentService;

    public FinanceinvestmentResource(FinanceinvestmentService financeinvestmentService) {
        this.financeinvestmentService = financeinvestmentService;
    }

    /**
     * POST  /financeinvestments : Create a new financeinvestment.
     *
     * @param financeinvestmentDTO the financeinvestmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new financeinvestmentDTO, or with status 400 (Bad Request) if the financeinvestment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/financeinvestments")
    @Timed
    public ResponseEntity<FinanceinvestmentDTO> createFinanceinvestment(@RequestBody FinanceinvestmentDTO financeinvestmentDTO) throws URISyntaxException {
        log.debug("REST request to save Financeinvestment : {}", financeinvestmentDTO);
        if (financeinvestmentDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new financeinvestment cannot already have an ID")).body(null);
        }
        FinanceinvestmentDTO result = financeinvestmentService.save(financeinvestmentDTO);
        return ResponseEntity.created(new URI("/api/financeinvestments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /financeinvestments : Updates an existing financeinvestment.
     *
     * @param financeinvestmentDTO the financeinvestmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated financeinvestmentDTO,
     * or with status 400 (Bad Request) if the financeinvestmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the financeinvestmentDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/financeinvestments")
    @Timed
    public ResponseEntity<FinanceinvestmentDTO> updateFinanceinvestment(@RequestBody FinanceinvestmentDTO financeinvestmentDTO) throws URISyntaxException {
        log.debug("REST request to update Financeinvestment : {}", financeinvestmentDTO);
        if (financeinvestmentDTO.getId() == null) {
            return createFinanceinvestment(financeinvestmentDTO);
        }
        FinanceinvestmentDTO result = financeinvestmentService.save(financeinvestmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, financeinvestmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /financeinvestments : get all the financeinvestments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of financeinvestments in body
     */
    @GetMapping("/financeinvestments")
    @Timed
    public List<FinanceinvestmentDTO> getAllFinanceinvestments() {
        log.debug("REST request to get all Financeinvestments");
        return financeinvestmentService.findAll();
    }

    /**
     * GET  /financeinvestments/:id : get the "id" financeinvestment.
     *
     * @param id the id of the financeinvestmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the financeinvestmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/financeinvestments/{id}")
    @Timed
    public ResponseEntity<FinanceinvestmentDTO> getFinanceinvestment(@PathVariable String id) {
        log.debug("REST request to get Financeinvestment : {}", id);
        FinanceinvestmentDTO financeinvestmentDTO = financeinvestmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(financeinvestmentDTO));
    }

    /**
     * DELETE  /financeinvestments/:id : delete the "id" financeinvestment.
     *
     * @param id the id of the financeinvestmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/financeinvestments/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinanceinvestment(@PathVariable String id) {
        log.debug("REST request to delete Financeinvestment : {}", id);
        financeinvestmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
