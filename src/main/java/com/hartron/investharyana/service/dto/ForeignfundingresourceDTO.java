package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Foreignfundingresource entity.
 */
public class ForeignfundingresourceDTO implements Serializable {

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

        ForeignfundingresourceDTO foreignfundingresourceDTO = (ForeignfundingresourceDTO) o;

        if ( ! Objects.equals(id, foreignfundingresourceDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ForeignfundingresourceDTO{" +
            "id=" + id +
            ", foreign_funding_resource_type='" + foreign_funding_resource_type + "'" +
            '}';
    }
}
