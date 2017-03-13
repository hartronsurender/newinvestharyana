package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Foreignfundingresource.
 */

@Table(name = "foreignfundingresource")
public class Foreignfundingresource implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String foreign_funding_resource_type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getForeign_funding_resource_type() {
        return foreign_funding_resource_type;
    }

    public Foreignfundingresource foreign_funding_resource_type(String foreign_funding_resource_type) {
        this.foreign_funding_resource_type = foreign_funding_resource_type;
        return this;
    }

    public void setForeign_funding_resource_type(String foreign_funding_resource_type) {
        this.foreign_funding_resource_type = foreign_funding_resource_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Foreignfundingresource foreignfundingresource = (Foreignfundingresource) o;
        if (foreignfundingresource.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, foreignfundingresource.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Foreignfundingresource{" +
            "id=" + id +
            ", foreign_funding_resource_type='" + foreign_funding_resource_type + "'" +
            '}';
    }
}
