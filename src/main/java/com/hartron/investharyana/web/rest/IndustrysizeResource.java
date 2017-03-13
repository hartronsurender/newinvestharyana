package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.IndustrysizeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.IndustrysizeDTO;
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
 * REST controller for managing Industrysize.
 */
@RestController
@RequestMapping("/api")
public class IndustrysizeResource {

    private final Logger log = LoggerFactory.getLogger(IndustrysizeResource.class);

    private static final String ENTITY_NAME = "industrysize";
        
    private final IndustrysizeService industrysizeService;

    public IndustrysizeResource(IndustrysizeService industrysizeService) {
        this.industrysizeService = industrysizeService;
    }

    /**
     * POST  /industrysizes : Create a new industrysize.
     *
     * @param industrysizeDTO the industrysizeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new industrysizeDTO, or with status 400 (Bad Request) if the industrysize has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/industrysizes")
    @Timed
    public ResponseEntity<IndustrysizeDTO> createIndustrysize(@RequestBody IndustrysizeDTO industrysizeDTO) throws URISyntaxException {
        log.debug("REST request to save Industrysize : {}", industrysizeDTO);
        if (industrysizeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new industrysize cannot already have an ID")).body(null);
        }
        IndustrysizeDTO result = industrysizeService.save(industrysizeDTO);
        return ResponseEntity.created(new URI("/api/industrysizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /industrysizes : Updates an existing industrysize.
     *
     * @param industrysizeDTO the industrysizeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated industrysizeDTO,
     * or with status 400 (Bad Request) if the industrysizeDTO is not valid,
     * or with status 500 (Internal Server Error) if the industrysizeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/industrysizes")
    @Timed
    public ResponseEntity<IndustrysizeDTO> updateIndustrysize(@RequestBody IndustrysizeDTO industrysizeDTO) throws URISyntaxException {
        log.debug("REST request to update Industrysize : {}", industrysizeDTO);
        if (industrysizeDTO.getId() == null) {
            return createIndustrysize(industrysizeDTO);
        }
        IndustrysizeDTO result = industrysizeService.save(industrysizeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, industrysizeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /industrysizes : get all the industrysizes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of industrysizes in body
     */
    @GetMapping("/industrysizes")
    @Timed
    public List<IndustrysizeDTO> getAllIndustrysizes() {
        log.debug("REST request to get all Industrysizes");
        return industrysizeService.findAll();
    }

    /**
     * GET  /industrysizes/:id : get the "id" industrysize.
     *
     * @param id the id of the industrysizeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the industrysizeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/industrysizes/{id}")
    @Timed
    public ResponseEntity<IndustrysizeDTO> getIndustrysize(@PathVariable String id) {
        log.debug("REST request to get Industrysize : {}", id);
        IndustrysizeDTO industrysizeDTO = industrysizeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(industrysizeDTO));
    }

    /**
     * DELETE  /industrysizes/:id : delete the "id" industrysize.
     *
     * @param id the id of the industrysizeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/industrysizes/{id}")
    @Timed
    public ResponseEntity<Void> deleteIndustrysize(@PathVariable String id) {
        log.debug("REST request to delete Industrysize : {}", id);
        industrysizeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
