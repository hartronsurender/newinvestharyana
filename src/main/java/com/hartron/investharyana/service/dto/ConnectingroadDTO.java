package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Connectingroad entity.
 */
public class ConnectingroadDTO implements Serializable {

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

        ConnectingroadDTO connectingroadDTO = (ConnectingroadDTO) o;

        if ( ! Objects.equals(id, connectingroadDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ConnectingroadDTO{" +
            "id=" + id +
            ", connectingroadtype='" + connectingroadtype + "'" +
            '}';
    }
}
