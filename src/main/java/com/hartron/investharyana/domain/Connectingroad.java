package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Connectingroad.
 */

@Table(name = "connectingroad")
public class Connectingroad implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String connectingroadtype;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConnectingroadtype() {
        return connectingroadtype;
    }

    public Connectingroad connectingroadtype(String connectingroadtype) {
        this.connectingroadtype = connectingroadtype;
        return this;
    }

    public void setConnectingroadtype(String connectingroadtype) {
        this.connectingroadtype = connectingroadtype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Connectingroad connectingroad = (Connectingroad) o;
        if (connectingroad.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, connectingroad.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Connectingroad{" +
            "id=" + id +
            ", connectingroadtype='" + connectingroadtype + "'" +
            '}';
    }
}
