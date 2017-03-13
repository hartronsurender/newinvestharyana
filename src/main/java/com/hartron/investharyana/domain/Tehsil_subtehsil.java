package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Tehsil_subtehsil.
 */

@Table(name = "tehsil_subtehsil")
public class Tehsil_subtehsil implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID districtid;

    private UUID blockid;

    private UUID city_town_villageid;

    private String tehsilname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDistrictid() {
        return districtid;
    }

    public Tehsil_subtehsil districtid(UUID districtid) {
        this.districtid = districtid;
        return this;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }

    public UUID getBlockid() {
        return blockid;
    }

    public Tehsil_subtehsil blockid(UUID blockid) {
        this.blockid = blockid;
        return this;
    }

    public void setBlockid(UUID blockid) {
        this.blockid = blockid;
    }

    public UUID getCity_town_villageid() {
        return city_town_villageid;
    }

    public Tehsil_subtehsil city_town_villageid(UUID city_town_villageid) {
        this.city_town_villageid = city_town_villageid;
        return this;
    }

    public void setCity_town_villageid(UUID city_town_villageid) {
        this.city_town_villageid = city_town_villageid;
    }

    public String getTehsilname() {
        return tehsilname;
    }

    public Tehsil_subtehsil tehsilname(String tehsilname) {
        this.tehsilname = tehsilname;
        return this;
    }

    public void setTehsilname(String tehsilname) {
        this.tehsilname = tehsilname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tehsil_subtehsil tehsil_subtehsil = (Tehsil_subtehsil) o;
        if (tehsil_subtehsil.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tehsil_subtehsil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tehsil_subtehsil{" +
            "id=" + id +
            ", districtid='" + districtid + "'" +
            ", blockid='" + blockid + "'" +
            ", city_town_villageid='" + city_town_villageid + "'" +
            ", tehsilname='" + tehsilname + "'" +
            '}';
    }
}
