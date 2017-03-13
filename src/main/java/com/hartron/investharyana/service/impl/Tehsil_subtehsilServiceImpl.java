package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Tehsil_subtehsilService;
import com.hartron.investharyana.domain.Tehsil_subtehsil;
import com.hartron.investharyana.repository.Tehsil_subtehsilRepository;
import com.hartron.investharyana.service.dto.Tehsil_subtehsilDTO;
import com.hartron.investharyana.service.mapper.Tehsil_subtehsilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Tehsil_subtehsil.
 */
@Service
public class Tehsil_subtehsilServiceImpl implements Tehsil_subtehsilService{

    private final Logger log = LoggerFactory.getLogger(Tehsil_subtehsilServiceImpl.class);
    
    private final Tehsil_subtehsilRepository tehsil_subtehsilRepository;

    private final Tehsil_subtehsilMapper tehsil_subtehsilMapper;

    public Tehsil_subtehsilServiceImpl(Tehsil_subtehsilRepository tehsil_subtehsilRepository, Tehsil_subtehsilMapper tehsil_subtehsilMapper) {
        this.tehsil_subtehsilRepository = tehsil_subtehsilRepository;
        this.tehsil_subtehsilMapper = tehsil_subtehsilMapper;
    }

    /**
     * Save a tehsil_subtehsil.
     *
     * @param tehsil_subtehsilDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Tehsil_subtehsilDTO save(Tehsil_subtehsilDTO tehsil_subtehsilDTO) {
        log.debug("Request to save Tehsil_subtehsil : {}", tehsil_subtehsilDTO);
        Tehsil_subtehsil tehsil_subtehsil = tehsil_subtehsilMapper.tehsil_subtehsilDTOToTehsil_subtehsil(tehsil_subtehsilDTO);
        tehsil_subtehsil = tehsil_subtehsilRepository.save(tehsil_subtehsil);
        Tehsil_subtehsilDTO result = tehsil_subtehsilMapper.tehsil_subtehsilToTehsil_subtehsilDTO(tehsil_subtehsil);
        return result;
    }

    /**
     *  Get all the tehsil_subtehsils.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Tehsil_subtehsilDTO> findAll() {
        log.debug("Request to get all Tehsil_subtehsils");
        List<Tehsil_subtehsilDTO> result = tehsil_subtehsilRepository.findAll().stream()
            .map(tehsil_subtehsilMapper::tehsil_subtehsilToTehsil_subtehsilDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one tehsil_subtehsil by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Tehsil_subtehsilDTO findOne(String id) {
        log.debug("Request to get Tehsil_subtehsil : {}", id);
        Tehsil_subtehsil tehsil_subtehsil = tehsil_subtehsilRepository.findOne(UUID.fromString(id));
        Tehsil_subtehsilDTO tehsil_subtehsilDTO = tehsil_subtehsilMapper.tehsil_subtehsilToTehsil_subtehsilDTO(tehsil_subtehsil);
        return tehsil_subtehsilDTO;
    }

    /**
     *  Delete the  tehsil_subtehsil by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Tehsil_subtehsil : {}", id);
        tehsil_subtehsilRepository.delete(UUID.fromString(id));
    }
}
