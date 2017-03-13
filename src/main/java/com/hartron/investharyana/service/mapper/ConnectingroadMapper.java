package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ConnectingroadDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Connectingroad and its DTO ConnectingroadDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConnectingroadMapper {

    ConnectingroadDTO connectingroadToConnectingroadDTO(Connectingroad connectingroad);

    List<ConnectingroadDTO> connectingroadsToConnectingroadDTOs(List<Connectingroad> connectingroads);

    Connectingroad connectingroadDTOToConnectingroad(ConnectingroadDTO connectingroadDTO);

    List<Connectingroad> connectingroadDTOsToConnectingroads(List<ConnectingroadDTO> connectingroadDTOs);
}
