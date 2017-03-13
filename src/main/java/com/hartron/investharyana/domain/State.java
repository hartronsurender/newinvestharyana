package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A State.
 */

@Table(name = "state")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public State countryid(UUID countryid) {
        this.countryid = countryid;
        return this;
    }

    public void setCountryid(UUID countryid) {
        this.countryid = countryid;
    }

    public String getStatename() {
        return statename;
    }

    public State statename(String statename) {
        this.statename = statename;
        return this;
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
        State state = (State) o;
        if (state.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "State{" +
            "id=" + id +
            ", countryid='" + countryid + "'" +
            ", statename='" + statename + "'" +
            '}';
    }
}
