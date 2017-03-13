package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Electric_load_type.
 */

@Table(name = "electric_load_type")
public class Electric_load_type implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private String regular_uhbvn_dhbvn_customer_type_name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegular_uhbvn_dhbvn_customer_type_name() {
        return regular_uhbvn_dhbvn_customer_type_name;
    }

    public Electric_load_type regular_uhbvn_dhbvn_customer_type_name(String regular_uhbvn_dhbvn_customer_type_name) {
        this.regular_uhbvn_dhbvn_customer_type_name = regular_uhbvn_dhbvn_customer_type_name;
        return this;
    }

    public void setRegular_uhbvn_dhbvn_customer_type_name(String regular_uhbvn_dhbvn_customer_type_name) {
        this.regular_uhbvn_dhbvn_customer_type_name = regular_uhbvn_dhbvn_customer_type_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Electric_load_type electric_load_type = (Electric_load_type) o;
        if (electric_load_type.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, electric_load_type.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Electric_load_type{" +
            "id=" + id +
            ", regular_uhbvn_dhbvn_customer_type_name='" + regular_uhbvn_dhbvn_customer_type_name + "'" +
            '}';
    }
}
