package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Waste_water_disposal_modeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Waste_water_disposal_mode and its DTO Waste_water_disposal_modeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Waste_water_disposal_modeMapper {

    Waste_water_disposal_modeDTO waste_water_disposal_modeToWaste_water_disposal_modeDTO(Waste_water_disposal_mode waste_water_disposal_mode);

    List<Waste_water_disposal_modeDTO> waste_water_disposal_modesToWaste_water_disposal_modeDTOs(List<Waste_water_disposal_mode> waste_water_disposal_modes);

    Waste_water_disposal_mode waste_water_disposal_modeDTOToWaste_water_disposal_mode(Waste_water_disposal_modeDTO waste_water_disposal_modeDTO);

    List<Waste_water_disposal_mode> waste_water_disposal_modeDTOsToWaste_water_disposal_modes(List<Waste_water_disposal_modeDTO> waste_water_disposal_modeDTOs);
}
