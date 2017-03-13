package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.FinanceinvestmentDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Financeinvestment and its DTO FinanceinvestmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FinanceinvestmentMapper {

    FinanceinvestmentDTO financeinvestmentToFinanceinvestmentDTO(Financeinvestment financeinvestment);

    List<FinanceinvestmentDTO> financeinvestmentsToFinanceinvestmentDTOs(List<Financeinvestment> financeinvestments);

    Financeinvestment financeinvestmentDTOToFinanceinvestment(FinanceinvestmentDTO financeinvestmentDTO);

    List<Financeinvestment> financeinvestmentDTOsToFinanceinvestments(List<FinanceinvestmentDTO> financeinvestmentDTOs);
}
