package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Investor;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Investor entity.
 */
@Repository
public class InvestorRepository {

    private final Session session;

    private Mapper<Investor> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public InvestorRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Investor.class);
        this.findAllStmt = session.prepare("SELECT * FROM investor");
        this.truncateStmt = session.prepare("TRUNCATE investor");
    }

    public List<Investor> findAll() {
        List<Investor> investorsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Investor investor = new Investor();
                investor.setId(row.getUUID("id"));
                investor.setMouapplicable(row.getBool("mouapplicable"));
                investor.setMousignyear(row.getInt("mousignyear"));
                investor.setMoudocument(row.getBytes("moudocument"));
                investor.setMoudocumentContentType(row.getString("moudocument_content_type"));

                investor.setMouidnumber(row.getString("mouidnumber"));
                investor.setInvestorphoto(row.getBytes("investorphoto"));
                investor.setInvestorphotoContentType(row.getString("investorphoto_content_type"));

                investor.setInv_f_name(row.getString("inv_f_name"));
                investor.setInv_m_name(row.getString("inv_m_name"));
                investor.setInv_l_name(row.getString("inv_l_name"));
                investor.setInvaddress1(row.getString("invaddress1"));
                investor.setInvaddress2(row.getString("invaddress2"));
                investor.setInvaddress3(row.getString("invaddress3"));
                investor.setPhone(row.getDouble("phone"));
                investor.setMobile(row.getDouble("mobile"));
                investor.setFax(row.getDouble("fax"));
                investor.setEmailprimary(row.getString("emailprimary"));
                investor.setEmailsecondary(row.getString("emailsecondary"));
                investor.setCountry(row.getUUID("country"));
                investor.setState(row.getUUID("state"));
                investor.setCity(row.getUUID("city"));
                return investor;
            }
        ).forEach(investorsList::add);
        return investorsList;
    }

    public Investor findOne(UUID id) {
        return mapper.get(id);
    }

    public Investor save(Investor investor) {
        if (investor.getId() == null) {
            investor.setId(UUID.randomUUID());
        }
        mapper.save(investor);
        return investor;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
