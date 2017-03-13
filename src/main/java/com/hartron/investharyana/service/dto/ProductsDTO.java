package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Products entity.
 */
public class ProductsDTO implements Serializable {

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

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public String getMain_product() {
        return main_product;
    }

    public void setMain_product(String main_product) {
        this.main_product = main_product;
    }
    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }
    public UUID getUnits() {
        return units;
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

        ProductsDTO productsDTO = (ProductsDTO) o;

        if ( ! Objects.equals(id, productsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", main_product='" + main_product + "'" +
            ", product_quantity='" + product_quantity + "'" +
            ", units='" + units + "'" +
            '}';
    }
}
