package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.CompanydetailDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Companydetail and its DTO CompanydetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompanydetailMapper {

    CompanydetailDTO companydetailToCompanydetailDTO(Companydetail companydetail);

    List<CompanydetailDTO> companydetailsToCompanydetailDTOs(List<Companydetail> companydetails);

    Companydetail companydetailDTOToCompanydetail(CompanydetailDTO companydetailDTO);

    List<Companydetail> companydetailDTOsToCompanydetails(List<CompanydetailDTO> companydetailDTOs);
}
