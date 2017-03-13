package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the District entity.
 */
public class DistrictDTO implements Serializable {

    private UUID id;

    private UUID stateid;

    private String districtname;

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
    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DistrictDTO districtDTO = (DistrictDTO) o;

        if ( ! Objects.equals(id, districtDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DistrictDTO{" +
            "id=" + id +
            ", stateid='" + stateid + "'" +
            ", districtname='" + districtname + "'" +
            '}';
    }
}
