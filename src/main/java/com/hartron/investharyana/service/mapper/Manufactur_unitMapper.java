package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Manufactur_unitDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Manufactur_unit and its DTO Manufactur_unitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Manufactur_unitMapper {

    Manufactur_unitDTO manufactur_unitToManufactur_unitDTO(Manufactur_unit manufactur_unit);

    List<Manufactur_unitDTO> manufactur_unitsToManufactur_unitDTOs(List<Manufactur_unit> manufactur_units);

    Manufactur_unit manufactur_unitDTOToManufactur_unit(Manufactur_unitDTO manufactur_unitDTO);

    List<Manufactur_unit> manufactur_unitDTOsToManufactur_units(List<Manufactur_unitDTO> manufactur_unitDTOs);
}
