package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.UnitsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Units and its DTO UnitsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UnitsMapper {

    UnitsDTO unitsToUnitsDTO(Units units);

    List<UnitsDTO> unitsToUnitsDTOs(List<Units> units);

    Units unitsDTOToUnits(UnitsDTO unitsDTO);

    List<Units> unitsDTOsToUnits(List<UnitsDTO> unitsDTOs);
}
