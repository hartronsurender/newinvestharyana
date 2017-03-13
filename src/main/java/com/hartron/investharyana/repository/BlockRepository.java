package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Block;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Block entity.
 */
@Repository
public class BlockRepository {

    private final Session session;

    private Mapper<Block> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public BlockRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Block.class);
        this.findAllStmt = session.prepare("SELECT * FROM block");
        this.truncateStmt = session.prepare("TRUNCATE block");
    }

    public List<Block> findAll() {
        List<Block> blocksList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Block block = new Block();
                block.setId(row.getUUID("id"));
                block.setStateid(row.getUUID("stateid"));
                block.setDistrictid(row.getUUID("districtid"));
                block.setBlockname(row.getString("blockname"));
                return block;
            }
        ).forEach(blocksList::add);
        return blocksList;
    }

    public Block findOne(UUID id) {
        return mapper.get(id);
    }

    public Block save(Block block) {
        if (block.getId() == null) {
            block.setId(UUID.randomUUID());
        }
        mapper.save(block);
        return block;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
