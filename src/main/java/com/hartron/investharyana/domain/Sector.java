package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Sector.
 */

@Table(name = "sector")
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String sectorname;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSectorname() {
        return sectorname;
    }

    public Sector sectorname(String sectorname) {
        this.sectorname = sectorname;
        return this;
    }

    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sector sector = (Sector) o;
        if (sector.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, sector.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sector{" +
            "id=" + id +
            ", sectorname='" + sectorname + "'" +
            '}';
    }
}
