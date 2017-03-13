package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Products.
 */

@Table(name = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private String main_product;

    private Integer product_quantity;

    private UUID units;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Products projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public String getMain_product() {
        return main_product;
    }

    public Products main_product(String main_product) {
        this.main_product = main_product;
        return this;
    }

    public void setMain_product(String main_product) {
        this.main_product = main_product;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public Products product_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
        return this;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }

    public UUID getUnits() {
        return units;
    }

    public Products units(UUID units) {
        this.units = units;
        return this;
    }

    public void setUnits(UUID units) {
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Products products = (Products) o;
        if (products.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, products.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Products{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", main_product='" + main_product + "'" +
            ", product_quantity='" + product_quantity + "'" +
            ", units='" + units + "'" +
            '}';
    }
}
