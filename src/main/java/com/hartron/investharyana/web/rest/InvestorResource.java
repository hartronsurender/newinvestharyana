package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.InvestorService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.InvestorDTO;
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
 * REST controller for managing Investor.
 */
@RestController
@RequestMapping("/api")
public class InvestorResource {

    private final Logger log = LoggerFactory.getLogger(InvestorResource.class);

    private static final String ENTITY_NAME = "investor";
        
    private final InvestorService investorService;

    public InvestorResource(InvestorService investorService) {
        this.investorService = investorService;
    }

    /**
     * POST  /investors : Create a new investor.
     *
     * @param investorDTO the investorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new investorDTO, or with status 400 (Bad Request) if the investor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/investors")
    @Timed
    public ResponseEntity<InvestorDTO> createInvestor(@RequestBody InvestorDTO investorDTO) throws URISyntaxException {
        log.debug("REST request to save Investor : {}", investorDTO);
        if (investorDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new investor cannot already have an ID")).body(null);
        }
        InvestorDTO result = investorService.save(investorDTO);
        return ResponseEntity.created(new URI("/api/investors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /investors : Updates an existing investor.
     *
     * @param investorDTO the investorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated investorDTO,
     * or with status 400 (Bad Request) if the investorDTO is not valid,
     * or with status 500 (Internal Server Error) if the investorDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/investors")
    @Timed
    public ResponseEntity<InvestorDTO> updateInvestor(@RequestBody InvestorDTO investorDTO) throws URISyntaxException {
        log.debug("REST request to update Investor : {}", investorDTO);
        if (investorDTO.getId() == null) {
            return createInvestor(investorDTO);
        }
        InvestorDTO result = investorService.save(investorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /investors : get all the investors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of investors in body
     */
    @GetMapping("/investors")
    @Timed
    public List<InvestorDTO> getAllInvestors() {
        log.debug("REST request to get all Investors");
        return investorService.findAll();
    }

    /**
     * GET  /investors/:id : get the "id" investor.
     *
     * @param id the id of the investorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the investorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/investors/{id}")
    @Timed
    public ResponseEntity<InvestorDTO> getInvestor(@PathVariable String id) {
        log.debug("REST request to get Investor : {}", id);
        InvestorDTO investorDTO = investorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(investorDTO));
    }

    /**
     * DELETE  /investors/:id : delete the "id" investor.
     *
     * @param id the id of the investorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/investors/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvestor(@PathVariable String id) {
        log.debug("REST request to delete Investor : {}", id);
        investorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
