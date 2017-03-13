package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.Disposal_for_dischargeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Disposal_for_discharge and its DTO Disposal_for_dischargeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Disposal_for_dischargeMapper {

    Disposal_for_dischargeDTO disposal_for_dischargeToDisposal_for_dischargeDTO(Disposal_for_discharge disposal_for_discharge);

    List<Disposal_for_dischargeDTO> disposal_for_dischargesToDisposal_for_dischargeDTOs(List<Disposal_for_discharge> disposal_for_discharges);

    Disposal_for_discharge disposal_for_dischargeDTOToDisposal_for_discharge(Disposal_for_dischargeDTO disposal_for_dischargeDTO);

    List<Disposal_for_discharge> disposal_for_dischargeDTOsToDisposal_for_discharges(List<Disposal_for_dischargeDTO> disposal_for_dischargeDTOs);
}
