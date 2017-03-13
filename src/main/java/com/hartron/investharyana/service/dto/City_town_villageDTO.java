package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the City_town_village entity.
 */
public class City_town_villageDTO implements Serializable {

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

    public void setStateid(UUID stateid) {
        this.stateid = stateid;
    }
    public UUID getDistrictid() {
        return districtid;
    }

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }
    public UUID getBlockid() {
        return blockid;
    }

    public void setBlockid(UUID blockid) {
        this.blockid = blockid;
    }
    public String getCity_town_village_name() {
        return city_town_village_name;
    }

    public void setCity_town_village_name(String city_town_village_name) {
        this.city_town_village_name = city_town_village_name;
    }
    public String getDescription() {
        return description;
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

        City_town_villageDTO city_town_villageDTO = (City_town_villageDTO) o;

        if ( ! Objects.equals(id, city_town_villageDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "City_town_villageDTO{" +
            "id=" + id +
            ", stateid='" + stateid + "'" +
            ", districtid='" + districtid + "'" +
            ", blockid='" + blockid + "'" +
            ", city_town_village_name='" + city_town_village_name + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
