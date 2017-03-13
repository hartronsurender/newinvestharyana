package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Environment_impact_detailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Environment_impact_detail and its DTO Environment_impact_detailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Environment_impact_detailMapper {

    Environment_impact_detailDTO environment_impact_detailToEnvironment_impact_detailDTO(Environment_impact_detail environment_impact_detail);

    List<Environment_impact_detailDTO> environment_impact_detailsToEnvironment_impact_detailDTOs(List<Environment_impact_detail> environment_impact_details);

    Environment_impact_detail environment_impact_detailDTOToEnvironment_impact_detail(Environment_impact_detailDTO environment_impact_detailDTO);

    List<Environment_impact_detail> environment_impact_detailDTOsToEnvironment_impact_details(List<Environment_impact_detailDTO> environment_impact_detailDTOs);
}
