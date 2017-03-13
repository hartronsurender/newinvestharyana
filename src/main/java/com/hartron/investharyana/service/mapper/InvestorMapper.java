package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.InvestorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Investor and its DTO InvestorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvestorMapper {

    InvestorDTO investorToInvestorDTO(Investor investor);

    List<InvestorDTO> investorsToInvestorDTOs(List<Investor> investors);

    Investor investorDTOToInvestor(InvestorDTO investorDTO);

    List<Investor> investorDTOsToInvestors(List<InvestorDTO> investorDTOs);
}
