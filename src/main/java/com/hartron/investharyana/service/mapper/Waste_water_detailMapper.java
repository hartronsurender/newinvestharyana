package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Waste_water_detailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Waste_water_detail and its DTO Waste_water_detailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Waste_water_detailMapper {

    Waste_water_detailDTO waste_water_detailToWaste_water_detailDTO(Waste_water_detail waste_water_detail);

    List<Waste_water_detailDTO> waste_water_detailsToWaste_water_detailDTOs(List<Waste_water_detail> waste_water_details);

    Waste_water_detail waste_water_detailDTOToWaste_water_detail(Waste_water_detailDTO waste_water_detailDTO);

    List<Waste_water_detail> waste_water_detailDTOsToWaste_water_details(List<Waste_water_detailDTO> waste_water_detailDTOs);
}
