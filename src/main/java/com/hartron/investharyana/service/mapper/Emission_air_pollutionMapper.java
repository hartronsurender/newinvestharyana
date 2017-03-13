package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Emission_air_pollutionDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Emission_air_pollution and its DTO Emission_air_pollutionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Emission_air_pollutionMapper {

    Emission_air_pollutionDTO emission_air_pollutionToEmission_air_pollutionDTO(Emission_air_pollution emission_air_pollution);

    List<Emission_air_pollutionDTO> emission_air_pollutionsToEmission_air_pollutionDTOs(List<Emission_air_pollution> emission_air_pollutions);

    Emission_air_pollution emission_air_pollutionDTOToEmission_air_pollution(Emission_air_pollutionDTO emission_air_pollutionDTO);

    List<Emission_air_pollution> emission_air_pollutionDTOsToEmission_air_pollutions(List<Emission_air_pollutionDTO> emission_air_pollutionDTOs);
}
