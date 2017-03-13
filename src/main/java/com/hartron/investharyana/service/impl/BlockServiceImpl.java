package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.BlockService;
import com.hartron.investharyana.domain.Block;
import com.hartron.investharyana.repository.BlockRepository;
import com.hartron.investharyana.service.dto.BlockDTO;
import com.hartron.investharyana.service.mapper.BlockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Block.
 */
@Service
public class BlockServiceImpl implements BlockService{

    private final Logger log = LoggerFactory.getLogger(BlockServiceImpl.class);
    
    private final BlockRepository blockRepository;

    private final BlockMapper blockMapper;

    public BlockServiceImpl(BlockRepository blockRepository, BlockMapper blockMapper) {
        this.blockRepository = blockRepository;
        this.blockMapper = blockMapper;
    }

    /**
     * Save a block.
     *
     * @param blockDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BlockDTO save(BlockDTO blockDTO) {
        log.debug("Request to save Block : {}", blockDTO);
        Block block = blockMapper.blockDTOToBlock(blockDTO);
        block = blockRepository.save(block);
        BlockDTO result = blockMapper.blockToBlockDTO(block);
        return result;
    }

    /**
     *  Get all the blocks.
     *  
     *  @return the list of entities
     */
    @Override
    public List<BlockDTO> findAll() {
        log.debug("Request to get all Blocks");
        List<BlockDTO> result = blockRepository.findAll().stream()
            .map(blockMapper::blockToBlockDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one block by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public BlockDTO findOne(String id) {
        log.debug("Request to get Block : {}", id);
        Block block = blockRepository.findOne(UUID.fromString(id));
        BlockDTO blockDTO = blockMapper.blockToBlockDTO(block);
        return blockDTO;
    }

    /**
     *  Delete the  block by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Block : {}", id);
        blockRepository.delete(UUID.fromString(id));
    }
}
