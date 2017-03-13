package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projecttype.
 */

@Table(name = "projecttype")
public class Projecttype implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String projecttype_name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProjecttype_name() {
        return projecttype_name;
    }

    public Projecttype projecttype_name(String projecttype_name) {
        this.projecttype_name = projecttype_name;
        return this;
    }

    public void setProjecttype_name(String projecttype_name) {
        this.projecttype_name = projecttype_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projecttype projecttype = (Projecttype) o;
        if (projecttype.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projecttype.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projecttype{" +
            "id=" + id +
            ", projecttype_name='" + projecttype_name + "'" +
            '}';
    }
}
