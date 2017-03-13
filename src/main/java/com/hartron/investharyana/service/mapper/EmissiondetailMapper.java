package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.EmissiondetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Emissiondetail and its DTO EmissiondetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmissiondetailMapper {

    EmissiondetailDTO emissiondetailToEmissiondetailDTO(Emissiondetail emissiondetail);

    List<EmissiondetailDTO> emissiondetailsToEmissiondetailDTOs(List<Emissiondetail> emissiondetails);

    Emissiondetail emissiondetailDTOToEmissiondetail(EmissiondetailDTO emissiondetailDTO);

    List<Emissiondetail> emissiondetailDTOsToEmissiondetails(List<EmissiondetailDTO> emissiondetailDTOs);
}
