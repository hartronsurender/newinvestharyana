package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.Electric_load_typeService;
import com.hartron.investharyana.domain.Electric_load_type;
import com.hartron.investharyana.repository.Electric_load_typeRepository;
import com.hartron.investharyana.service.dto.Electric_load_typeDTO;
import com.hartron.investharyana.service.mapper.Electric_load_typeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Electric_load_type.
 */
@Service
public class Electric_load_typeServiceImpl implements Electric_load_typeService{

    private final Logger log = LoggerFactory.getLogger(Electric_load_typeServiceImpl.class);
    
    private final Electric_load_typeRepository electric_load_typeRepository;

    private final Electric_load_typeMapper electric_load_typeMapper;

    public Electric_load_typeServiceImpl(Electric_load_typeRepository electric_load_typeRepository, Electric_load_typeMapper electric_load_typeMapper) {
        this.electric_load_typeRepository = electric_load_typeRepository;
        this.electric_load_typeMapper = electric_load_typeMapper;
    }

    /**
     * Save a electric_load_type.
     *
     * @param electric_load_typeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Electric_load_typeDTO save(Electric_load_typeDTO electric_load_typeDTO) {
        log.debug("Request to save Electric_load_type : {}", electric_load_typeDTO);
        Electric_load_type electric_load_type = electric_load_typeMapper.electric_load_typeDTOToElectric_load_type(electric_load_typeDTO);
        electric_load_type = electric_load_typeRepository.save(electric_load_type);
        Electric_load_typeDTO result = electric_load_typeMapper.electric_load_typeToElectric_load_typeDTO(electric_load_type);
        return result;
    }

    /**
     *  Get all the electric_load_types.
     *  
     *  @return the list of entities
     */
    @Override
    public List<Electric_load_typeDTO> findAll() {
        log.debug("Request to get all Electric_load_types");
        List<Electric_load_typeDTO> result = electric_load_typeRepository.findAll().stream()
            .map(electric_load_typeMapper::electric_load_typeToElectric_load_typeDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one electric_load_type by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Electric_load_typeDTO findOne(String id) {
        log.debug("Request to get Electric_load_type : {}", id);
        Electric_load_type electric_load_type = electric_load_typeRepository.findOne(UUID.fromString(id));
        Electric_load_typeDTO electric_load_typeDTO = electric_load_typeMapper.electric_load_typeToElectric_load_typeDTO(electric_load_type);
        return electric_load_typeDTO;
    }

    /**
     *  Delete the  electric_load_type by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Electric_load_type : {}", id);
        electric_load_typeRepository.delete(UUID.fromString(id));
    }
}
