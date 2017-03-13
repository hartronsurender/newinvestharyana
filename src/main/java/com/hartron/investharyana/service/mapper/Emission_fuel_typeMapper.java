package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Emission_fuel_typeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Emission_fuel_type and its DTO Emission_fuel_typeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Emission_fuel_typeMapper {

    Emission_fuel_typeDTO emission_fuel_typeToEmission_fuel_typeDTO(Emission_fuel_type emission_fuel_type);

    List<Emission_fuel_typeDTO> emission_fuel_typesToEmission_fuel_typeDTOs(List<Emission_fuel_type> emission_fuel_types);

    Emission_fuel_type emission_fuel_typeDTOToEmission_fuel_type(Emission_fuel_typeDTO emission_fuel_typeDTO);

    List<Emission_fuel_type> emission_fuel_typeDTOsToEmission_fuel_types(List<Emission_fuel_typeDTO> emission_fuel_typeDTOs);
}
