package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.StateService;
import com.hartron.investharyana.domain.State;
import com.hartron.investharyana.repository.StateRepository;
import com.hartron.investharyana.service.dto.StateDTO;
import com.hartron.investharyana.service.mapper.StateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing State.
 */
@Service
public class StateServiceImpl implements StateService{

    private final Logger log = LoggerFactory.getLogger(StateServiceImpl.class);
    
    private final StateRepository stateRepository;

    private final StateMapper stateMapper;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }

    /**
     * Save a state.
     *
     * @param stateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StateDTO save(StateDTO stateDTO) {
        log.debug("Request to save State : {}", stateDTO);
        State state = stateMapper.stateDTOToState(stateDTO);
        state = stateRepository.save(state);
        StateDTO result = stateMapper.stateToStateDTO(state);
        return result;
    }

    /**
     *  Get all the states.
     *  
     *  @return the list of entities
     */
    @Override
    public List<StateDTO> findAll() {
        log.debug("Request to get all States");
        List<StateDTO> result = stateRepository.findAll().stream()
            .map(stateMapper::stateToStateDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one state by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public StateDTO findOne(String id) {
        log.debug("Request to get State : {}", id);
        State state = stateRepository.findOne(UUID.fromString(id));
        StateDTO stateDTO = stateMapper.stateToStateDTO(state);
        return stateDTO;
    }

    /**
     *  Delete the  state by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete State : {}", id);
        stateRepository.delete(UUID.fromString(id));
    }
}
