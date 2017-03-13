package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.LandzoneclassificationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Landzoneclassification and its DTO LandzoneclassificationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LandzoneclassificationMapper {

    LandzoneclassificationDTO landzoneclassificationToLandzoneclassificationDTO(Landzoneclassification landzoneclassification);

    List<LandzoneclassificationDTO> landzoneclassificationsToLandzoneclassificationDTOs(List<Landzoneclassification> landzoneclassifications);

    Landzoneclassification landzoneclassificationDTOToLandzoneclassification(LandzoneclassificationDTO landzoneclassificationDTO);

    List<Landzoneclassification> landzoneclassificationDTOsToLandzoneclassifications(List<LandzoneclassificationDTO> landzoneclassificationDTOs);
}
