package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectsitedetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectsitedetail and its DTO ProjectsitedetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectsitedetailMapper {

    ProjectsitedetailDTO projectsitedetailToProjectsitedetailDTO(Projectsitedetail projectsitedetail);

    List<ProjectsitedetailDTO> projectsitedetailsToProjectsitedetailDTOs(List<Projectsitedetail> projectsitedetails);

    Projectsitedetail projectsitedetailDTOToProjectsitedetail(ProjectsitedetailDTO projectsitedetailDTO);

    List<Projectsitedetail> projectsitedetailDTOsToProjectsitedetails(List<ProjectsitedetailDTO> projectsitedetailDTOs);
}
