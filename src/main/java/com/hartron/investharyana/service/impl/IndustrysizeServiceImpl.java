package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.IndustrysizeService;
import com.hartron.investharyana.domain.Industrysize;
import com.hartron.investharyana.repository.IndustrysizeRepository;
import com.hartron.investharyana.service.dto.IndustrysizeDTO;
import com.hartron.investharyana.service.mapper.IndustrysizeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Industrysize.
 */
@Service
public class IndustrysizeServiceImpl implements IndustrysizeService{

    private final Logger log = LoggerFactory.getLogger(IndustrysizeServiceImpl.class);
    
    private final IndustrysizeRepository industrysizeRepository;

    private final IndustrysizeMapper industrysizeMapper;

    public IndustrysizeServiceImpl(IndustrysizeRepository industrysizeRepository, IndustrysizeMapper industrysizeMapper) {
        this.industrysizeRepository = industrysizeRepository;
        this.industrysizeMapper = industrysizeMapper;
    }

    /**
     * Save a industrysize.
     *
     * @param industrysizeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IndustrysizeDTO save(IndustrysizeDTO industrysizeDTO) {
        log.debug("Request to save Industrysize : {}", industrysizeDTO);
        Industrysize industrysize = industrysizeMapper.industrysizeDTOToIndustrysize(industrysizeDTO);
        industrysize = industrysizeRepository.save(industrysize);
        IndustrysizeDTO result = industrysizeMapper.industrysizeToIndustrysizeDTO(industrysize);
        return result;
    }

    /**
     *  Get all the industrysizes.
     *  
     *  @return the list of entities
     */
    @Override
    public List<IndustrysizeDTO> findAll() {
        log.debug("Request to get all Industrysizes");
        List<IndustrysizeDTO> result = industrysizeRepository.findAll().stream()
            .map(industrysizeMapper::industrysizeToIndustrysizeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one industrysize by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public IndustrysizeDTO findOne(String id) {
        log.debug("Request to get Industrysize : {}", id);
        Industrysize industrysize = industrysizeRepository.findOne(UUID.fromString(id));
        IndustrysizeDTO industrysizeDTO = industrysizeMapper.industrysizeToIndustrysizeDTO(industrysize);
        return industrysizeDTO;
    }

    /**
     *  Delete the  industrysize by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Industrysize : {}", id);
        industrysizeRepository.delete(UUID.fromString(id));
    }
}
