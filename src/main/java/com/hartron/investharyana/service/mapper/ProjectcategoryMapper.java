package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProjectcategoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectcategory and its DTO ProjectcategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectcategoryMapper {

    ProjectcategoryDTO projectcategoryToProjectcategoryDTO(Projectcategory projectcategory);

    List<ProjectcategoryDTO> projectcategoriesToProjectcategoryDTOs(List<Projectcategory> projectcategories);

    Projectcategory projectcategoryDTOToProjectcategory(ProjectcategoryDTO projectcategoryDTO);

    List<Projectcategory> projectcategoryDTOsToProjectcategories(List<ProjectcategoryDTO> projectcategoryDTOs);
}
