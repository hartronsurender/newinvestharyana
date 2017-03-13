package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Accept_verification;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Accept_verification entity.
 */
@Repository
public class Accept_verificationRepository {

    private final Session session;

    private Mapper<Accept_verification> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public Accept_verificationRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Accept_verification.class);
        this.findAllStmt = session.prepare("SELECT * FROM accept_verification");
        this.truncateStmt = session.prepare("TRUNCATE accept_verification");
    }

    public List<Accept_verification> findAll() {
        List<Accept_verification> accept_verificationsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Accept_verification accept_verification = new Accept_verification();
                accept_verification.setId(row.getUUID("id"));
                accept_verification.setProjectid(row.getUUID("projectid"));
                accept_verification.setInvestorid(row.getUUID("investorid"));
                accept_verification.setAcceptcondition(row.getBool("acceptcondition"));
                accept_verification.setApplicationdate(row.get("applicationdate", ZonedDateTime.class));
                accept_verification.setSignature_document(row.getBytes("signature_document"));
                accept_verification.setSignature_documentContentType(row.getString("signature_document_content_type"));

                return accept_verification;
            }
        ).forEach(accept_verificationsList::add);
        return accept_verificationsList;
    }

    public Accept_verification findOne(UUID id) {
        return mapper.get(id);
    }

    public Accept_verification save(Accept_verification accept_verification) {
        if (accept_verification.getId() == null) {
            accept_verification.setId(UUID.randomUUID());
        }
        mapper.save(accept_verification);
        return accept_verification;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
