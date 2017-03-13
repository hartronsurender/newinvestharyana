package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Emission_particularsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Emission_particulars and its DTO Emission_particularsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Emission_particularsMapper {

    Emission_particularsDTO emission_particularsToEmission_particularsDTO(Emission_particulars emission_particulars);

    List<Emission_particularsDTO> emission_particularsToEmission_particularsDTOs(List<Emission_particulars> emission_particulars);

    Emission_particulars emission_particularsDTOToEmission_particulars(Emission_particularsDTO emission_particularsDTO);

    List<Emission_particulars> emission_particularsDTOsToEmission_particulars(List<Emission_particularsDTO> emission_particularsDTOs);
}
