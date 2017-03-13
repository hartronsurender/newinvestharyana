package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.FinanceinvestmentService;
import com.hartron.investharyana.domain.Financeinvestment;
import com.hartron.investharyana.repository.FinanceinvestmentRepository;
import com.hartron.investharyana.service.dto.FinanceinvestmentDTO;
import com.hartron.investharyana.service.mapper.FinanceinvestmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Financeinvestment.
 */
@Service
public class FinanceinvestmentServiceImpl implements FinanceinvestmentService{

    private final Logger log = LoggerFactory.getLogger(FinanceinvestmentServiceImpl.class);
    
    private final FinanceinvestmentRepository financeinvestmentRepository;

    private final FinanceinvestmentMapper financeinvestmentMapper;

    public FinanceinvestmentServiceImpl(FinanceinvestmentRepository financeinvestmentRepository, FinanceinvestmentMapper financeinvestmentMapper) {
        this.financeinvestmentRepository = financeinvestmentRepository;
        this.financeinvestmentMapper = financeinvestmentMapper;
    }

    /**
     * Save a financeinvestment.
     *
     * @param financeinvestmentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinanceinvestmentDTO save(FinanceinvestmentDTO financeinvestmentDTO) {
        log.debug("Request to save Financeinvestment : {}", financeinvestmentDTO);
        Financeinvestment financeinvestment = financeinvestmentMapper.financeinvestmentDTOToFinanceinvestment(financeinvestmentDTO);
        financeinvestment = financeinvestmentRepository.save(financeinvestment);
        FinanceinvestmentDTO result = financeinvestmentMapper.financeinvestmentToFinanceinvestmentDTO(financeinvestment);
        return result;
    }

    /**
     *  Get all the financeinvestments.
     *  
     *  @return the list of entities
     */
    @Override
    public List<FinanceinvestmentDTO> findAll() {
        log.debug("Request to get all Financeinvestments");
        List<FinanceinvestmentDTO> result = financeinvestmentRepository.findAll().stream()
            .map(financeinvestmentMapper::financeinvestmentToFinanceinvestmentDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one financeinvestment by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public FinanceinvestmentDTO findOne(String id) {
        log.debug("Request to get Financeinvestment : {}", id);
        Financeinvestment financeinvestment = financeinvestmentRepository.findOne(UUID.fromString(id));
        FinanceinvestmentDTO financeinvestmentDTO = financeinvestmentMapper.financeinvestmentToFinanceinvestmentDTO(financeinvestment);
        return financeinvestmentDTO;
    }

    /**
     *  Delete the  financeinvestment by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Financeinvestment : {}", id);
        financeinvestmentRepository.delete(UUID.fromString(id));
    }
}
