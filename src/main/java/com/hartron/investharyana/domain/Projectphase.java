package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectphase.
 */

@Table(name = "projectphase")
public class Projectphase implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String fciphase;

    private String fci_product_category;

    private String fci;

    private ZonedDateTime fic_implementation_date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Projectphase projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getFciphase() {
        return fciphase;
    }

    public Projectphase fciphase(String fciphase) {
        this.fciphase = fciphase;
        return this;
    }

    public void setFciphase(String fciphase) {
        this.fciphase = fciphase;
    }

    public String getFci_product_category() {
        return fci_product_category;
    }

    public Projectphase fci_product_category(String fci_product_category) {
        this.fci_product_category = fci_product_category;
        return this;
    }

    public void setFci_product_category(String fci_product_category) {
        this.fci_product_category = fci_product_category;
    }

    public String getFci() {
        return fci;
    }

    public Projectphase fci(String fci) {
        this.fci = fci;
        return this;
    }

    public void setFci(String fci) {
        this.fci = fci;
    }

    public ZonedDateTime getFic_implementation_date() {
        return fic_implementation_date;
    }

    public Projectphase fic_implementation_date(ZonedDateTime fic_implementation_date) {
        this.fic_implementation_date = fic_implementation_date;
        return this;
    }

    public void setFic_implementation_date(ZonedDateTime fic_implementation_date) {
        this.fic_implementation_date = fic_implementation_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Projectphase projectphase = (Projectphase) o;
        if (projectphase.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectphase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectphase{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", fciphase='" + fciphase + "'" +
            ", fci_product_category='" + fci_product_category + "'" +
            ", fci='" + fci + "'" +
            ", fic_implementation_date='" + fic_implementation_date + "'" +
            '}';
    }
}
