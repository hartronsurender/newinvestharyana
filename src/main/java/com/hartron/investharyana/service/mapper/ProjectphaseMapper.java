package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectphaseDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectphase and its DTO ProjectphaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectphaseMapper {

    ProjectphaseDTO projectphaseToProjectphaseDTO(Projectphase projectphase);

    List<ProjectphaseDTO> projectphasesToProjectphaseDTOs(List<Projectphase> projectphases);

    Projectphase projectphaseDTOToProjectphase(ProjectphaseDTO projectphaseDTO);

    List<Projectphase> projectphaseDTOsToProjectphases(List<ProjectphaseDTO> projectphaseDTOs);
}
