package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A City_town_village.
 */

@Table(name = "city_town_village")
public class City_town_village implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID stateid;

    private UUID districtid;

    private UUID blockid;

    private String city_town_village_name;

    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStateid() {
        return stateid;
    }

    public City_town_village stateid(UUID stateid) {
        this.stateid = stateid;
        return this;
    }

    public void setStateid(UUID stateid) {
        this.stateid = stateid;
    }

    public UUID getDistrictid() {
        return districtid;
    }

    public City_town_village districtid(UUID districtid) {
        this.districtid = districtid;
        return this;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }

    public UUID getBlockid() {
        return blockid;
    }

    public City_town_village blockid(UUID blockid) {
        this.blockid = blockid;
        return this;
    }

    public void setBlockid(UUID blockid) {
        this.blockid = blockid;
    }

    public String getCity_town_village_name() {
        return city_town_village_name;
    }

    public City_town_village city_town_village_name(String city_town_village_name) {
        this.city_town_village_name = city_town_village_name;
        return this;
    }

    public void setCity_town_village_name(String city_town_village_name) {
        this.city_town_village_name = city_town_village_name;
    }

    public String getDescription() {
        return description;
    }

    public City_town_village description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City_town_village city_town_village = (City_town_village) o;
        if (city_town_village.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, city_town_village.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "City_town_village{" +
            "id=" + id +
            ", stateid='" + stateid + "'" +
            ", districtid='" + districtid + "'" +
            ", blockid='" + blockid + "'" +
            ", city_town_village_name='" + city_town_village_name + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
