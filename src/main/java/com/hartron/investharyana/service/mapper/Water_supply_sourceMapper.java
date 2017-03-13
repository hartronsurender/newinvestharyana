package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Water_supply_sourceDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Water_supply_source and its DTO Water_supply_sourceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Water_supply_sourceMapper {

    Water_supply_sourceDTO water_supply_sourceToWater_supply_sourceDTO(Water_supply_source water_supply_source);

    List<Water_supply_sourceDTO> water_supply_sourcesToWater_supply_sourceDTOs(List<Water_supply_source> water_supply_sources);

    Water_supply_source water_supply_sourceDTOToWater_supply_source(Water_supply_sourceDTO water_supply_sourceDTO);

    List<Water_supply_source> water_supply_sourceDTOsToWater_supply_sources(List<Water_supply_sourceDTO> water_supply_sourceDTOs);
}
