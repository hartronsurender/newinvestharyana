package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectdetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectdetail and its DTO ProjectdetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectdetailMapper {

    ProjectdetailDTO projectdetailToProjectdetailDTO(Projectdetail projectdetail);

    List<ProjectdetailDTO> projectdetailsToProjectdetailDTOs(List<Projectdetail> projectdetails);

    Projectdetail projectdetailDTOToProjectdetail(ProjectdetailDTO projectdetailDTO);

    List<Projectdetail> projectdetailDTOsToProjectdetails(List<ProjectdetailDTO> projectdetailDTOs);
}
