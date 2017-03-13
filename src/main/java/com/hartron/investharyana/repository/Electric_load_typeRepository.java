package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Electric_load_type;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Electric_load_type entity.
 */
@Repository
public class Electric_load_typeRepository {

    private final Session session;

    private Mapper<Electric_load_type> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Electric_load_typeRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Electric_load_type.class);
        this.findAllStmt = session.prepare("SELECT * FROM electric_load_type");
        this.truncateStmt = session.prepare("TRUNCATE electric_load_type");
    }

    public List<Electric_load_type> findAll() {
        List<Electric_load_type> electric_load_typesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Electric_load_type electric_load_type = new Electric_load_type();
                electric_load_type.setId(row.getUUID("id"));
                electric_load_type.setRegular_uhbvn_dhbvn_customer_type_name(row.getString("regular_uhbvn_dhbvn_customer_type_name"));
                return electric_load_type;
            }
        ).forEach(electric_load_typesList::add);
        return electric_load_typesList;
    }

    public Electric_load_type findOne(UUID id) {
        return mapper.get(id);
    }

    public Electric_load_type save(Electric_load_type electric_load_type) {
        if (electric_load_type.getId() == null) {
            electric_load_type.setId(UUID.randomUUID());
        }
        mapper.save(electric_load_type);
        return electric_load_type;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
