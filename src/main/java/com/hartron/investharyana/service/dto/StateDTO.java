package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the State entity.
 */
public class StateDTO implements Serializable {

    private UUID id;

    private UUID countryid;

    private String statename;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getCountryid() {
        return countryid;
    }

    public void setCountryid(UUID countryid) {
        this.countryid = countryid;
    }
    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StateDTO stateDTO = (StateDTO) o;

        if ( ! Objects.equals(id, stateDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StateDTO{" +
            "id=" + id +
            ", countryid='" + countryid + "'" +
            ", statename='" + statename + "'" +
            '}';
    }
}
