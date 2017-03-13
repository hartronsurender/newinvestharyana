package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.Electric_load_typeService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.Electric_load_typeDTO;
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
 * REST controller for managing Electric_load_type.
 */
@RestController
@RequestMapping("/api")
public class Electric_load_typeResource {

    private final Logger log = LoggerFactory.getLogger(Electric_load_typeResource.class);

    private static final String ENTITY_NAME = "electric_load_type";
        
    private final Electric_load_typeService electric_load_typeService;

    public Electric_load_typeResource(Electric_load_typeService electric_load_typeService) {
        this.electric_load_typeService = electric_load_typeService;
    }

    /**
     * POST  /electric-load-types : Create a new electric_load_type.
     *
     * @param electric_load_typeDTO the electric_load_typeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new electric_load_typeDTO, or with status 400 (Bad Request) if the electric_load_type has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/electric-load-types")
    @Timed
    public ResponseEntity<Electric_load_typeDTO> createElectric_load_type(@RequestBody Electric_load_typeDTO electric_load_typeDTO) throws URISyntaxException {
        log.debug("REST request to save Electric_load_type : {}", electric_load_typeDTO);
        if (electric_load_typeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new electric_load_type cannot already have an ID")).body(null);
        }
        Electric_load_typeDTO result = electric_load_typeService.save(electric_load_typeDTO);
        return ResponseEntity.created(new URI("/api/electric-load-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /electric-load-types : Updates an existing electric_load_type.
     *
     * @param electric_load_typeDTO the electric_load_typeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated electric_load_typeDTO,
     * or with status 400 (Bad Request) if the electric_load_typeDTO is not valid,
     * or with status 500 (Internal Server Error) if the electric_load_typeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/electric-load-types")
    @Timed
    public ResponseEntity<Electric_load_typeDTO> updateElectric_load_type(@RequestBody Electric_load_typeDTO electric_load_typeDTO) throws URISyntaxException {
        log.debug("REST request to update Electric_load_type : {}", electric_load_typeDTO);
        if (electric_load_typeDTO.getId() == null) {
            return createElectric_load_type(electric_load_typeDTO);
        }
        Electric_load_typeDTO result = electric_load_typeService.save(electric_load_typeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, electric_load_typeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /electric-load-types : get all the electric_load_types.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of electric_load_types in body
     */
    @GetMapping("/electric-load-types")
    @Timed
    public List<Electric_load_typeDTO> getAllElectric_load_types() {
        log.debug("REST request to get all Electric_load_types");
        return electric_load_typeService.findAll();
    }

    /**
     * GET  /electric-load-types/:id : get the "id" electric_load_type.
     *
     * @param id the id of the electric_load_typeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the electric_load_typeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/electric-load-types/{id}")
    @Timed
    public ResponseEntity<Electric_load_typeDTO> getElectric_load_type(@PathVariable String id) {
        log.debug("REST request to get Electric_load_type : {}", id);
        Electric_load_typeDTO electric_load_typeDTO = electric_load_typeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(electric_load_typeDTO));
    }

    /**
     * DELETE  /electric-load-types/:id : delete the "id" electric_load_type.
     *
     * @param id the id of the electric_load_typeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/electric-load-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteElectric_load_type(@PathVariable String id) {
        log.debug("REST request to delete Electric_load_type : {}", id);
        electric_load_typeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
