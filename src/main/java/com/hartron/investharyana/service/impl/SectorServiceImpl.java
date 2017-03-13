package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.SectorService;
import com.hartron.investharyana.domain.Sector;
import com.hartron.investharyana.repository.SectorRepository;
import com.hartron.investharyana.service.dto.SectorDTO;
import com.hartron.investharyana.service.mapper.SectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Sector.
 */
@Service
public class SectorServiceImpl implements SectorService{

    private final Logger log = LoggerFactory.getLogger(SectorServiceImpl.class);
    
    private final SectorRepository sectorRepository;

    private final SectorMapper sectorMapper;

    public SectorServiceImpl(SectorRepository sectorRepository, SectorMapper sectorMapper) {
        this.sectorRepository = sectorRepository;
        this.sectorMapper = sectorMapper;
    }

    /**
     * Save a sector.
     *
     * @param sectorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SectorDTO save(SectorDTO sectorDTO) {
        log.debug("Request to save Sector : {}", sectorDTO);
        Sector sector = sectorMapper.sectorDTOToSector(sectorDTO);
        sector = sectorRepository.save(sector);
        SectorDTO result = sectorMapper.sectorToSectorDTO(sector);
        return result;
    }

    /**
     *  Get all the sectors.
     *  
     *  @return the list of entities
     */
    @Override
    public List<SectorDTO> findAll() {
        log.debug("Request to get all Sectors");
        List<SectorDTO> result = sectorRepository.findAll().stream()
            .map(sectorMapper::sectorToSectorDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one sector by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public SectorDTO findOne(String id) {
        log.debug("Request to get Sector : {}", id);
        Sector sector = sectorRepository.findOne(UUID.fromString(id));
        SectorDTO sectorDTO = sectorMapper.sectorToSectorDTO(sector);
        return sectorDTO;
    }

    /**
     *  Delete the  sector by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Sector : {}", id);
        sectorRepository.delete(UUID.fromString(id));
    }
}
