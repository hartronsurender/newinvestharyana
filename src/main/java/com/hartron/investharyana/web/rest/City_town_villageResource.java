package com.hartron.investharyana.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hartron.investharyana.service.City_town_villageService;
import com.hartron.investharyana.web.rest.util.HeaderUtil;
import com.hartron.investharyana.service.dto.City_town_villageDTO;
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
 * REST controller for managing City_town_village.
 */
@RestController
@RequestMapping("/api")
public class City_town_villageResource {

    private final Logger log = LoggerFactory.getLogger(City_town_villageResource.class);

    private static final String ENTITY_NAME = "city_town_village";
        
    private final City_town_villageService city_town_villageService;

    public City_town_villageResource(City_town_villageService city_town_villageService) {
        this.city_town_villageService = city_town_villageService;
    }

    /**
     * POST  /city-town-villages : Create a new city_town_village.
     *
     * @param city_town_villageDTO the city_town_villageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new city_town_villageDTO, or with status 400 (Bad Request) if the city_town_village has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/city-town-villages")
    @Timed
    public ResponseEntity<City_town_villageDTO> createCity_town_village(@RequestBody City_town_villageDTO city_town_villageDTO) throws URISyntaxException {
        log.debug("REST request to save City_town_village : {}", city_town_villageDTO);
        if (city_town_villageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new city_town_village cannot already have an ID")).body(null);
        }
        City_town_villageDTO result = city_town_villageService.save(city_town_villageDTO);
        return ResponseEntity.created(new URI("/api/city-town-villages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /city-town-villages : Updates an existing city_town_village.
     *
     * @param city_town_villageDTO the city_town_villageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated city_town_villageDTO,
     * or with status 400 (Bad Request) if the city_town_villageDTO is not valid,
     * or with status 500 (Internal Server Error) if the city_town_villageDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/city-town-villages")
    @Timed
    public ResponseEntity<City_town_villageDTO> updateCity_town_village(@RequestBody City_town_villageDTO city_town_villageDTO) throws URISyntaxException {
        log.debug("REST request to update City_town_village : {}", city_town_villageDTO);
        if (city_town_villageDTO.getId() == null) {
            return createCity_town_village(city_town_villageDTO);
        }
        City_town_villageDTO result = city_town_villageService.save(city_town_villageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, city_town_villageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /city-town-villages : get all the city_town_villages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of city_town_villages in body
     */
    @GetMapping("/city-town-villages")
    @Timed
    public List<City_town_villageDTO> getAllCity_town_villages() {
        log.debug("REST request to get all City_town_villages");
        return city_town_villageService.findAll();
    }

    /**
     * GET  /city-town-villages/:id : get the "id" city_town_village.
     *
     * @param id the id of the city_town_villageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the city_town_villageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/city-town-villages/{id}")
    @Timed
    public ResponseEntity<City_town_villageDTO> getCity_town_village(@PathVariable String id) {
        log.debug("REST request to get City_town_village : {}", id);
        City_town_villageDTO city_town_villageDTO = city_town_villageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(city_town_villageDTO));
    }

    /**
     * DELETE  /city-town-villages/:id : delete the "id" city_town_village.
     *
     * @param id the id of the city_town_villageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/city-town-villages/{id}")
    @Timed
    public ResponseEntity<Void> deleteCity_town_village(@PathVariable String id) {
        log.debug("REST request to delete City_town_village : {}", id);
        city_town_villageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
