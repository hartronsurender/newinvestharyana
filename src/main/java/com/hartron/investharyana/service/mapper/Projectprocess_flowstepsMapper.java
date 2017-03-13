package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Projectprocess_flowstepsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Projectprocess_flowsteps and its DTO Projectprocess_flowstepsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Projectprocess_flowstepsMapper {

    Projectprocess_flowstepsDTO projectprocess_flowstepsToProjectprocess_flowstepsDTO(Projectprocess_flowsteps projectprocess_flowsteps);

    List<Projectprocess_flowstepsDTO> projectprocess_flowstepsToProjectprocess_flowstepsDTOs(List<Projectprocess_flowsteps> projectprocess_flowsteps);

    Projectprocess_flowsteps projectprocess_flowstepsDTOToProjectprocess_flowsteps(Projectprocess_flowstepsDTO projectprocess_flowstepsDTO);

    List<Projectprocess_flowsteps> projectprocess_flowstepsDTOsToProjectprocess_flowsteps(List<Projectprocess_flowstepsDTO> projectprocess_flowstepsDTOs);
}
