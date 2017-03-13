package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Industrysize entity.
 */
public class IndustrysizeDTO implements Serializable {

    private UUID id;

    private String size_of_industry;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getSize_of_industry() {
        return size_of_industry;
    }

    public void setSize_of_industry(String size_of_industry) {
        this.size_of_industry = size_of_industry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IndustrysizeDTO industrysizeDTO = (IndustrysizeDTO) o;

        if ( ! Objects.equals(id, industrysizeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "IndustrysizeDTO{" +
            "id=" + id +
            ", size_of_industry='" + size_of_industry + "'" +
            '}';
    }
}
