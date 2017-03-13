package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ForeignfundingresourceDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Foreignfundingresource and its DTO ForeignfundingresourceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ForeignfundingresourceMapper {

    ForeignfundingresourceDTO foreignfundingresourceToForeignfundingresourceDTO(Foreignfundingresource foreignfundingresource);

    List<ForeignfundingresourceDTO> foreignfundingresourcesToForeignfundingresourceDTOs(List<Foreignfundingresource> foreignfundingresources);

    Foreignfundingresource foreignfundingresourceDTOToForeignfundingresource(ForeignfundingresourceDTO foreignfundingresourceDTO);

    List<Foreignfundingresource> foreignfundingresourceDTOsToForeignfundingresources(List<ForeignfundingresourceDTO> foreignfundingresourceDTOs);
}
