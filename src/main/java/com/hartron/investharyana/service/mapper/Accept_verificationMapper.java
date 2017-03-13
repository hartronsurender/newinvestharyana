package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Accept_verificationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Accept_verification and its DTO Accept_verificationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Accept_verificationMapper {

    Accept_verificationDTO accept_verificationToAccept_verificationDTO(Accept_verification accept_verification);

    List<Accept_verificationDTO> accept_verificationsToAccept_verificationDTOs(List<Accept_verification> accept_verifications);

    Accept_verification accept_verificationDTOToAccept_verification(Accept_verificationDTO accept_verificationDTO);

    List<Accept_verification> accept_verificationDTOsToAccept_verifications(List<Accept_verificationDTO> accept_verificationDTOs);
}
