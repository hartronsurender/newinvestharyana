package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Industrysize.
 */

@Table(name = "industrysize")
public class Industrysize implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
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

    public Industrysize size_of_industry(String size_of_industry) {
        this.size_of_industry = size_of_industry;
        return this;
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
        Industrysize industrysize = (Industrysize) o;
        if (industrysize.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, industrysize.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Industrysize{" +
            "id=" + id +
            ", size_of_industry='" + size_of_industry + "'" +
            '}';
    }
}
