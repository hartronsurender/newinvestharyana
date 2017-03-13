package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Electric_load_type entity.
 */
public class Electric_load_typeDTO implements Serializable {

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

        Electric_load_typeDTO electric_load_typeDTO = (Electric_load_typeDTO) o;

        if ( ! Objects.equals(id, electric_load_typeDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Electric_load_typeDTO{" +
            "id=" + id +
            ", regular_uhbvn_dhbvn_customer_type_name='" + regular_uhbvn_dhbvn_customer_type_name + "'" +
            '}';
    }
}
