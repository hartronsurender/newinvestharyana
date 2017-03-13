package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectrawmaterialDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectrawmaterial and its DTO ProjectrawmaterialDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectrawmaterialMapper {

    ProjectrawmaterialDTO projectrawmaterialToProjectrawmaterialDTO(Projectrawmaterial projectrawmaterial);

    List<ProjectrawmaterialDTO> projectrawmaterialsToProjectrawmaterialDTOs(List<Projectrawmaterial> projectrawmaterials);

    Projectrawmaterial projectrawmaterialDTOToProjectrawmaterial(ProjectrawmaterialDTO projectrawmaterialDTO);

    List<Projectrawmaterial> projectrawmaterialDTOsToProjectrawmaterials(List<ProjectrawmaterialDTO> projectrawmaterialDTOs);
}
