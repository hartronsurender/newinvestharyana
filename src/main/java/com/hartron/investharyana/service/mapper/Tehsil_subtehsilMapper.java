package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Tehsil_subtehsilDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Tehsil_subtehsil and its DTO Tehsil_subtehsilDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Tehsil_subtehsilMapper {

    Tehsil_subtehsilDTO tehsil_subtehsilToTehsil_subtehsilDTO(Tehsil_subtehsil tehsil_subtehsil);

    List<Tehsil_subtehsilDTO> tehsil_subtehsilsToTehsil_subtehsilDTOs(List<Tehsil_subtehsil> tehsil_subtehsils);

    Tehsil_subtehsil tehsil_subtehsilDTOToTehsil_subtehsil(Tehsil_subtehsilDTO tehsil_subtehsilDTO);

    List<Tehsil_subtehsil> tehsil_subtehsilDTOsToTehsil_subtehsils(List<Tehsil_subtehsilDTO> tehsil_subtehsilDTOs);
}
