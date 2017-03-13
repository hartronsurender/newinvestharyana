package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.BlockDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Block and its DTO BlockDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BlockMapper {

    BlockDTO blockToBlockDTO(Block block);

    List<BlockDTO> blocksToBlockDTOs(List<Block> blocks);

    Block blockDTOToBlock(BlockDTO blockDTO);

    List<Block> blockDTOsToBlocks(List<BlockDTO> blockDTOs);
}
