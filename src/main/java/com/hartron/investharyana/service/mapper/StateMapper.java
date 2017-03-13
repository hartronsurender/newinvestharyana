package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.StateDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity State and its DTO StateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StateMapper {

    StateDTO stateToStateDTO(State state);

    List<StateDTO> statesToStateDTOs(List<State> states);

    State stateDTOToState(StateDTO stateDTO);

    List<State> stateDTOsToStates(List<StateDTO> stateDTOs);
}
