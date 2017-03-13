package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ForeignfundingresourceService;
import com.hartron.investharyana.domain.Foreignfundingresource;
import com.hartron.investharyana.repository.ForeignfundingresourceRepository;
import com.hartron.investharyana.service.dto.ForeignfundingresourceDTO;
import com.hartron.investharyana.service.mapper.ForeignfundingresourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Foreignfundingresource.
 */
@Service
public class ForeignfundingresourceServiceImpl implements ForeignfundingresourceService{

    private final Logger log = LoggerFactory.getLogger(ForeignfundingresourceServiceImpl.class);
    
    private final ForeignfundingresourceRepository foreignfundingresourceRepository;

    private final ForeignfundingresourceMapper foreignfundingresourceMapper;

    public ForeignfundingresourceServiceImpl(ForeignfundingresourceRepository foreignfundingresourceRepository, ForeignfundingresourceMapper foreignfundingresourceMapper) {
        this.foreignfundingresourceRepository = foreignfundingresourceRepository;
        this.foreignfundingresourceMapper = foreignfundingresourceMapper;
    }

    /**
     * Save a foreignfundingresource.
     *
     * @param foreignfundingresourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ForeignfundingresourceDTO save(ForeignfundingresourceDTO foreignfundingresourceDTO) {
        log.debug("Request to save Foreignfundingresource : {}", foreignfundingresourceDTO);
        Foreignfundingresource foreignfundingresource = foreignfundingresourceMapper.foreignfundingresourceDTOToForeignfundingresource(foreignfundingresourceDTO);
        foreignfundingresource = foreignfundingresourceRepository.save(foreignfundingresource);
        ForeignfundingresourceDTO result = foreignfundingresourceMapper.foreignfundingresourceToForeignfundingresourceDTO(foreignfundingresource);
        return result;
    }

    /**
     *  Get all the foreignfundingresources.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ForeignfundingresourceDTO> findAll() {
        log.debug("Request to get all Foreignfundingresources");
        List<ForeignfundingresourceDTO> result = foreignfundingresourceRepository.findAll().stream()
            .map(foreignfundingresourceMapper::foreignfundingresourceToForeignfundingresourceDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one foreignfundingresource by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ForeignfundingresourceDTO findOne(String id) {
        log.debug("Request to get Foreignfundingresource : {}", id);
        Foreignfundingresource foreignfundingresource = foreignfundingresourceRepository.findOne(UUID.fromString(id));
        ForeignfundingresourceDTO foreignfundingresourceDTO = foreignfundingresourceMapper.foreignfundingresourceToForeignfundingresourceDTO(foreignfundingresource);
        return foreignfundingresourceDTO;
    }

    /**
     *  Delete the  foreignfundingresource by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Foreignfundingresource : {}", id);
        foreignfundingresourceRepository.delete(UUID.fromString(id));
    }
}
