package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Tehsil_subtehsil entity.
 */
public class Tehsil_subtehsilDTO implements Serializable {

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

    public void setDistrictid(UUID districtid) {
        this.districtid = districtid;
    }
    public UUID getBlockid() {
        return blockid;
    }

    public void setBlockid(UUID blockid) {
        this.blockid = blockid;
    }
    public UUID getCity_town_villageid() {
        return city_town_villageid;
    }

    public void setCity_town_villageid(UUID city_town_villageid) {
        this.city_town_villageid = city_town_villageid;
    }
    public String getTehsilname() {
        return tehsilname;
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

        Tehsil_subtehsilDTO tehsil_subtehsilDTO = (Tehsil_subtehsilDTO) o;

        if ( ! Objects.equals(id, tehsil_subtehsilDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tehsil_subtehsilDTO{" +
            "id=" + id +
            ", districtid='" + districtid + "'" +
            ", blockid='" + blockid + "'" +
            ", city_town_villageid='" + city_town_villageid + "'" +
            ", tehsilname='" + tehsilname + "'" +
            '}';
    }
}
