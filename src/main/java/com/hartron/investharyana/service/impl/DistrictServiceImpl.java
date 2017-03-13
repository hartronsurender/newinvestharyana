package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.DistrictService;
import com.hartron.investharyana.domain.District;
import com.hartron.investharyana.repository.DistrictRepository;
import com.hartron.investharyana.service.dto.DistrictDTO;
import com.hartron.investharyana.service.mapper.DistrictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing District.
 */
@Service
public class DistrictServiceImpl implements DistrictService{

    private final Logger log = LoggerFactory.getLogger(DistrictServiceImpl.class);
    
    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DistrictDTO save(DistrictDTO districtDTO) {
        log.debug("Request to save District : {}", districtDTO);
        District district = districtMapper.districtDTOToDistrict(districtDTO);
        district = districtRepository.save(district);
        DistrictDTO result = districtMapper.districtToDistrictDTO(district);
        return result;
    }

    /**
     *  Get all the districts.
     *  
     *  @return the list of entities
     */
    @Override
    public List<DistrictDTO> findAll() {
        log.debug("Request to get all Districts");
        List<DistrictDTO> result = districtRepository.findAll().stream()
            .map(districtMapper::districtToDistrictDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one district by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public DistrictDTO findOne(String id) {
        log.debug("Request to get District : {}", id);
        District district = districtRepository.findOne(UUID.fromString(id));
        DistrictDTO districtDTO = districtMapper.districtToDistrictDTO(district);
        return districtDTO;
    }

    /**
     *  Delete the  district by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete District : {}", id);
        districtRepository.delete(UUID.fromString(id));
    }
}
