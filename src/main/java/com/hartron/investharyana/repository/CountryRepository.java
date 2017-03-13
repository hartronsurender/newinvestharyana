package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Country;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Country entity.
 */
@Repository
public class CountryRepository {

    private final Session session;

    private Mapper<Country> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public CountryRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Country.class);
        this.findAllStmt = session.prepare("SELECT * FROM country");
        this.truncateStmt = session.prepare("TRUNCATE country");
    }

    public List<Country> findAll() {
        List<Country> countriesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Country country = new Country();
                country.setId(row.getUUID("id"));
                country.setCountryname(row.getString("countryname"));
                return country;
            }
        ).forEach(countriesList::add);
        return countriesList;
    }

    public Country findOne(UUID id) {
        return mapper.get(id);
    }

    public Country save(Country country) {
        if (country.getId() == null) {
            country.setId(UUID.randomUUID());
        }
        mapper.save(country);
        return country;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
