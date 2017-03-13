package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Project_electricity_detailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Project_electricity_detail and its DTO Project_electricity_detailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Project_electricity_detailMapper {

    Project_electricity_detailDTO project_electricity_detailToProject_electricity_detailDTO(Project_electricity_detail project_electricity_detail);

    List<Project_electricity_detailDTO> project_electricity_detailsToProject_electricity_detailDTOs(List<Project_electricity_detail> project_electricity_details);

    Project_electricity_detail project_electricity_detailDTOToProject_electricity_detail(Project_electricity_detailDTO project_electricity_detailDTO);

    List<Project_electricity_detail> project_electricity_detailDTOsToProject_electricity_details(List<Project_electricity_detailDTO> project_electricity_detailDTOs);
}
