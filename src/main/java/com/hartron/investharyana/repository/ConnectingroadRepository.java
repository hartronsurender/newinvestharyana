package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Connectingroad;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Connectingroad entity.
 */
@Repository
public class ConnectingroadRepository {

    private final Session session;

    private Mapper<Connectingroad> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ConnectingroadRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Connectingroad.class);
        this.findAllStmt = session.prepare("SELECT * FROM connectingroad");
        this.truncateStmt = session.prepare("TRUNCATE connectingroad");
    }

    public List<Connectingroad> findAll() {
        List<Connectingroad> connectingroadsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Connectingroad connectingroad = new Connectingroad();
                connectingroad.setId(row.getUUID("id"));
                connectingroad.setConnectingroadtype(row.getString("connectingroadtype"));
                return connectingroad;
            }
        ).forEach(connectingroadsList::add);
        return connectingroadsList;
    }

    public Connectingroad findOne(UUID id) {
        return mapper.get(id);
    }

    public Connectingroad save(Connectingroad connectingroad) {
        if (connectingroad.getId() == null) {
            connectingroad.setId(UUID.randomUUID());
        }
        mapper.save(connectingroad);
        return connectingroad;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
