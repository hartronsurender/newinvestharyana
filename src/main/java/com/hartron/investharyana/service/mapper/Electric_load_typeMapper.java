package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Electric_load_typeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Electric_load_type and its DTO Electric_load_typeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Electric_load_typeMapper {

    Electric_load_typeDTO electric_load_typeToElectric_load_typeDTO(Electric_load_type electric_load_type);

    List<Electric_load_typeDTO> electric_load_typesToElectric_load_typeDTOs(List<Electric_load_type> electric_load_types);

    Electric_load_type electric_load_typeDTOToElectric_load_type(Electric_load_typeDTO electric_load_typeDTO);

    List<Electric_load_type> electric_load_typeDTOsToElectric_load_types(List<Electric_load_typeDTO> electric_load_typeDTOs);
}
