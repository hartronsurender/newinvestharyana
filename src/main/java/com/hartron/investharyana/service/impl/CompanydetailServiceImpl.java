package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.CompanydetailService;
import com.hartron.investharyana.domain.Companydetail;
import com.hartron.investharyana.repository.CompanydetailRepository;
import com.hartron.investharyana.service.dto.CompanydetailDTO;
import com.hartron.investharyana.service.mapper.CompanydetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Companydetail.
 */
@Service
public class CompanydetailServiceImpl implements CompanydetailService{

    private final Logger log = LoggerFactory.getLogger(CompanydetailServiceImpl.class);
    
    private final CompanydetailRepository companydetailRepository;

    private final CompanydetailMapper companydetailMapper;

    public CompanydetailServiceImpl(CompanydetailRepository companydetailRepository, CompanydetailMapper companydetailMapper) {
        this.companydetailRepository = companydetailRepository;
        this.companydetailMapper = companydetailMapper;
    }

    /**
     * Save a companydetail.
     *
     * @param companydetailDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanydetailDTO save(CompanydetailDTO companydetailDTO) {
        log.debug("Request to save Companydetail : {}", companydetailDTO);
        Companydetail companydetail = companydetailMapper.companydetailDTOToCompanydetail(companydetailDTO);
        companydetail = companydetailRepository.save(companydetail);
        CompanydetailDTO result = companydetailMapper.companydetailToCompanydetailDTO(companydetail);
        return result;
    }

    /**
     *  Get all the companydetails.
     *  
     *  @return the list of entities
     */
    @Override
    public List<CompanydetailDTO> findAll() {
        log.debug("Request to get all Companydetails");
        List<CompanydetailDTO> result = companydetailRepository.findAll().stream()
            .map(companydetailMapper::companydetailToCompanydetailDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one companydetail by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public CompanydetailDTO findOne(String id) {
        log.debug("Request to get Companydetail : {}", id);
        Companydetail companydetail = companydetailRepository.findOne(UUID.fromString(id));
        CompanydetailDTO companydetailDTO = companydetailMapper.companydetailToCompanydetailDTO(companydetail);
        return companydetailDTO;
    }

    /**
     *  Delete the  companydetail by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Companydetail : {}", id);
        companydetailRepository.delete(UUID.fromString(id));
    }
}
