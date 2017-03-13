package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Waste_water_natureDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Waste_water_nature and its DTO Waste_water_natureDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Waste_water_natureMapper {

    Waste_water_natureDTO waste_water_natureToWaste_water_natureDTO(Waste_water_nature waste_water_nature);

    List<Waste_water_natureDTO> waste_water_naturesToWaste_water_natureDTOs(List<Waste_water_nature> waste_water_natures);

    Waste_water_nature waste_water_natureDTOToWaste_water_nature(Waste_water_natureDTO waste_water_natureDTO);

    List<Waste_water_nature> waste_water_natureDTOsToWaste_water_natures(List<Waste_water_natureDTO> waste_water_natureDTOs);
}
