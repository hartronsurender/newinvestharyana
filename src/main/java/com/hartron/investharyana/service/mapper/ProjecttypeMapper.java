package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjecttypeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projecttype and its DTO ProjecttypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjecttypeMapper {

    ProjecttypeDTO projecttypeToProjecttypeDTO(Projecttype projecttype);

    List<ProjecttypeDTO> projecttypesToProjecttypeDTOs(List<Projecttype> projecttypes);

    Projecttype projecttypeDTOToProjecttype(ProjecttypeDTO projecttypeDTO);

    List<Projecttype> projecttypeDTOsToProjecttypes(List<ProjecttypeDTO> projecttypeDTOs);
}
