package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.City_town_villageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity City_town_village and its DTO City_town_villageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface City_town_villageMapper {

    City_town_villageDTO city_town_villageToCity_town_villageDTO(City_town_village city_town_village);

    List<City_town_villageDTO> city_town_villagesToCity_town_villageDTOs(List<City_town_village> city_town_villages);

    City_town_village city_town_villageDTOToCity_town_village(City_town_villageDTO city_town_villageDTO);

    List<City_town_village> city_town_villageDTOsToCity_town_villages(List<City_town_villageDTO> city_town_villageDTOs);
}
