package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.SectorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Sector and its DTO SectorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SectorMapper {

    SectorDTO sectorToSectorDTO(Sector sector);

    List<SectorDTO> sectorsToSectorDTOs(List<Sector> sectors);

    Sector sectorDTOToSector(SectorDTO sectorDTO);

    List<Sector> sectorDTOsToSectors(List<SectorDTO> sectorDTOs);
}
