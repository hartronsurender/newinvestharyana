package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.CompanydetailService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.CompanydetailDTO;
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
 * REST controller for managing Companydetail.
 */
@RestController
@RequestMapping("/api")
public class CompanydetailResource {

    private final Logger log = LoggerFactory.getLogger(CompanydetailResource.class);

    private static final String ENTITY_NAME = "companydetail";
        
    private final CompanydetailService companydetailService;

    public CompanydetailResource(CompanydetailService companydetailService) {
        this.companydetailService = companydetailService;
    }

    /**
     * POST  /companydetails : Create a new companydetail.
     *
     * @param companydetailDTO the companydetailDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new companydetailDTO, or with status 400 (Bad Request) if the companydetail has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/companydetails")
    @Timed
    public ResponseEntity<CompanydetailDTO> createCompanydetail(@RequestBody CompanydetailDTO companydetailDTO) throws URISyntaxException {
        log.debug("REST request to save Companydetail : {}", companydetailDTO);
        if (companydetailDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new companydetail cannot already have an ID")).body(null);
        }
        CompanydetailDTO result = companydetailService.save(companydetailDTO);
        return ResponseEntity.created(new URI("/api/companydetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /companydetails : Updates an existing companydetail.
     *
     * @param companydetailDTO the companydetailDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated companydetailDTO,
     * or with status 400 (Bad Request) if the companydetailDTO is not valid,
     * or with status 500 (Internal Server Error) if the companydetailDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/companydetails")
    @Timed
    public ResponseEntity<CompanydetailDTO> updateCompanydetail(@RequestBody CompanydetailDTO companydetailDTO) throws URISyntaxException {
        log.debug("REST request to update Companydetail : {}", companydetailDTO);
        if (companydetailDTO.getId() == null) {
            return createCompanydetail(companydetailDTO);
        }
        CompanydetailDTO result = companydetailService.save(companydetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, companydetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /companydetails : get all the companydetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of companydetails in body
     */
    @GetMapping("/companydetails")
    @Timed
    public List<CompanydetailDTO> getAllCompanydetails() {
        log.debug("REST request to get all Companydetails");
        return companydetailService.findAll();
    }

    /**
     * GET  /companydetails/:id : get the "id" companydetail.
     *
     * @param id the id of the companydetailDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companydetailDTO, or with status 404 (Not Found)
     */
    @GetMapping("/companydetails/{id}")
    @Timed
    public ResponseEntity<CompanydetailDTO> getCompanydetail(@PathVariable String id) {
        log.debug("REST request to get Companydetail : {}", id);
        CompanydetailDTO companydetailDTO = companydetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(companydetailDTO));
    }

    /**
     * DELETE  /companydetails/:id : delete the "id" companydetail.
     *
     * @param id the id of the companydetailDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/companydetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompanydetail(@PathVariable String id) {
        log.debug("REST request to delete Companydetail : {}", id);
        companydetailService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
