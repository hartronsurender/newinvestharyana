package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ConnectingroadService;
import com.hartron.investharyana.domain.Connectingroad;
import com.hartron.investharyana.repository.ConnectingroadRepository;
import com.hartron.investharyana.service.dto.ConnectingroadDTO;
import com.hartron.investharyana.service.mapper.ConnectingroadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Connectingroad.
 */
@Service
public class ConnectingroadServiceImpl implements ConnectingroadService{

    private final Logger log = LoggerFactory.getLogger(ConnectingroadServiceImpl.class);
    
    private final ConnectingroadRepository connectingroadRepository;

    private final ConnectingroadMapper connectingroadMapper;

    public ConnectingroadServiceImpl(ConnectingroadRepository connectingroadRepository, ConnectingroadMapper connectingroadMapper) {
        this.connectingroadRepository = connectingroadRepository;
        this.connectingroadMapper = connectingroadMapper;
    }

    /**
     * Save a connectingroad.
     *
     * @param connectingroadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConnectingroadDTO save(ConnectingroadDTO connectingroadDTO) {
        log.debug("Request to save Connectingroad : {}", connectingroadDTO);
        Connectingroad connectingroad = connectingroadMapper.connectingroadDTOToConnectingroad(connectingroadDTO);
        connectingroad = connectingroadRepository.save(connectingroad);
        ConnectingroadDTO result = connectingroadMapper.connectingroadToConnectingroadDTO(connectingroad);
        return result;
    }

    /**
     *  Get all the connectingroads.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ConnectingroadDTO> findAll() {
        log.debug("Request to get all Connectingroads");
        List<ConnectingroadDTO> result = connectingroadRepository.findAll().stream()
            .map(connectingroadMapper::connectingroadToConnectingroadDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one connectingroad by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ConnectingroadDTO findOne(String id) {
        log.debug("Request to get Connectingroad : {}", id);
        Connectingroad connectingroad = connectingroadRepository.findOne(UUID.fromString(id));
        ConnectingroadDTO connectingroadDTO = connectingroadMapper.connectingroadToConnectingroadDTO(connectingroad);
        return connectingroadDTO;
    }

    /**
     *  Delete the  connectingroad by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Connectingroad : {}", id);
        connectingroadRepository.delete(UUID.fromString(id));
    }
}
