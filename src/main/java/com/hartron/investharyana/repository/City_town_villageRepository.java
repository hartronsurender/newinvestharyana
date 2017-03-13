package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.City_town_village;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the City_town_village entity.
 */
@Repository
public class City_town_villageRepository {

    private final Session session;

    private Mapper<City_town_village> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public City_town_villageRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(City_town_village.class);
        this.findAllStmt = session.prepare("SELECT * FROM city_town_village");
        this.truncateStmt = session.prepare("TRUNCATE city_town_village");
    }

    public List<City_town_village> findAll() {
        List<City_town_village> city_town_villagesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                City_town_village city_town_village = new City_town_village();
                city_town_village.setId(row.getUUID("id"));
                city_town_village.setStateid(row.getUUID("stateid"));
                city_town_village.setDistrictid(row.getUUID("districtid"));
                city_town_village.setBlockid(row.getUUID("blockid"));
                city_town_village.setCity_town_village_name(row.getString("city_town_village_name"));
                city_town_village.setDescription(row.getString("description"));
                return city_town_village;
            }
        ).forEach(city_town_villagesList::add);
        return city_town_villagesList;
    }

    public City_town_village findOne(UUID id) {
        return mapper.get(id);
    }

    public City_town_village save(City_town_village city_town_village) {
        if (city_town_village.getId() == null) {
            city_town_village.setId(UUID.randomUUID());
        }
        mapper.save(city_town_village);
        return city_town_village;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
