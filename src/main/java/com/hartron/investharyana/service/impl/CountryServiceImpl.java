package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.CountryService;
import com.hartron.investharyana.domain.Country;
import com.hartron.investharyana.repository.CountryRepository;
import com.hartron.investharyana.service.dto.CountryDTO;
import com.hartron.investharyana.service.mapper.CountryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Country.
 */
@Service
public class CountryServiceImpl implements CountryService{

    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);
    
    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    /**
     * Save a country.
     *
     * @param countryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        log.debug("Request to save Country : {}", countryDTO);
        Country country = countryMapper.countryDTOToCountry(countryDTO);
        country = countryRepository.save(country);
        CountryDTO result = countryMapper.countryToCountryDTO(country);
        return result;
    }

    /**
     *  Get all the countries.
     *  
     *  @return the list of entities
     */
    @Override
    public List<CountryDTO> findAll() {
        log.debug("Request to get all Countries");
        List<CountryDTO> result = countryRepository.findAll().stream()
            .map(countryMapper::countryToCountryDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one country by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public CountryDTO findOne(String id) {
        log.debug("Request to get Country : {}", id);
        Country country = countryRepository.findOne(UUID.fromString(id));
        CountryDTO countryDTO = countryMapper.countryToCountryDTO(country);
        return countryDTO;
    }

    /**
     *  Delete the  country by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Country : {}", id);
        countryRepository.delete(UUID.fromString(id));
    }
}
