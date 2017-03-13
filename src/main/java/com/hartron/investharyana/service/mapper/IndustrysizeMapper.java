package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.IndustrysizeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Industrysize and its DTO IndustrysizeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IndustrysizeMapper {

    IndustrysizeDTO industrysizeToIndustrysizeDTO(Industrysize industrysize);

    List<IndustrysizeDTO> industrysizesToIndustrysizeDTOs(List<Industrysize> industrysizes);

    Industrysize industrysizeDTOToIndustrysize(IndustrysizeDTO industrysizeDTO);

    List<Industrysize> industrysizeDTOsToIndustrysizes(List<IndustrysizeDTO> industrysizeDTOs);
}
