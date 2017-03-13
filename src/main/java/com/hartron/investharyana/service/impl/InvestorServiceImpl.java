package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.InvestorService;
import com.hartron.investharyana.domain.Investor;
import com.hartron.investharyana.repository.InvestorRepository;
import com.hartron.investharyana.service.dto.InvestorDTO;
import com.hartron.investharyana.service.mapper.InvestorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Investor.
 */
@Service
public class InvestorServiceImpl implements InvestorService{

    private final Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);
    
    private final InvestorRepository investorRepository;

    private final InvestorMapper investorMapper;

    public InvestorServiceImpl(InvestorRepository investorRepository, InvestorMapper investorMapper) {
        this.investorRepository = investorRepository;
        this.investorMapper = investorMapper;
    }

    /**
     * Save a investor.
     *
     * @param investorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InvestorDTO save(InvestorDTO investorDTO) {
        log.debug("Request to save Investor : {}", investorDTO);
        Investor investor = investorMapper.investorDTOToInvestor(investorDTO);
        investor = investorRepository.save(investor);
        InvestorDTO result = investorMapper.investorToInvestorDTO(investor);
        return result;
    }

    /**
     *  Get all the investors.
     *  
     *  @return the list of entities
     */
    @Override
    public List<InvestorDTO> findAll() {
        log.debug("Request to get all Investors");
        List<InvestorDTO> result = investorRepository.findAll().stream()
            .map(investorMapper::investorToInvestorDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one investor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public InvestorDTO findOne(String id) {
        log.debug("Request to get Investor : {}", id);
        Investor investor = investorRepository.findOne(UUID.fromString(id));
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(investor);
        return investorDTO;
    }

    /**
     *  Delete the  investor by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Investor : {}", id);
        investorRepository.delete(UUID.fromString(id));
    }
}
