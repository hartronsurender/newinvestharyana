package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Project_electricity_detail entity.
 */
public class Project_electricity_detailDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private ByteBuffer temporaryconnection;
    private String temporaryconnectionContentType;

    private Boolean load_required;

    private Boolean existing_connecton;

    private String account_number;

    private BigDecimal load_demand;

    private BigDecimal load_demand_kva;

    private BigDecimal new_load_demand_kw;

    private BigDecimal new_load_demand_kva;

    private ZonedDateTime load_demand_date;

    private ByteBuffer regular_connection;
    private String regular_connectionContentType;

    private Boolean regular_load_required;

    private Boolean regular_existing_connection;

    private UUID regular_uhbvn_dhbvn_customer_type;

    private String regular_account_number;

    private BigDecimal regular_load_if_any_kw;

    private BigDecimal regular_existing_load_kva;

    private BigDecimal regular_new_load_demand_kw;

    private BigDecimal regular_new_load_demand_kva;

    private ZonedDateTime regular_demand_date;

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
    public ByteBuffer getTemporaryconnection() {
        return temporaryconnection;
    }

    public void setTemporaryconnection(ByteBuffer temporaryconnection) {
        this.temporaryconnection = temporaryconnection;
    }

    public String getTemporaryconnectionContentType() {
        return temporaryconnectionContentType;
    }

    public void setTemporaryconnectionContentType(String temporaryconnectionContentType) {
        this.temporaryconnectionContentType = temporaryconnectionContentType;
    }
    public Boolean getLoad_required() {
        return load_required;
    }

    public void setLoad_required(Boolean load_required) {
        this.load_required = load_required;
    }
    public Boolean getExisting_connecton() {
        return existing_connecton;
    }

    public void setExisting_connecton(Boolean existing_connecton) {
        this.existing_connecton = existing_connecton;
    }
    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
    public BigDecimal getLoad_demand() {
        return load_demand;
    }

    public void setLoad_demand(BigDecimal load_demand) {
        this.load_demand = load_demand;
    }
    public BigDecimal getLoad_demand_kva() {
        return load_demand_kva;
    }

    public void setLoad_demand_kva(BigDecimal load_demand_kva) {
        this.load_demand_kva = load_demand_kva;
    }
    public BigDecimal getNew_load_demand_kw() {
        return new_load_demand_kw;
    }

    public void setNew_load_demand_kw(BigDecimal new_load_demand_kw) {
        this.new_load_demand_kw = new_load_demand_kw;
    }
    public BigDecimal getNew_load_demand_kva() {
        return new_load_demand_kva;
    }

    public void setNew_load_demand_kva(BigDecimal new_load_demand_kva) {
        this.new_load_demand_kva = new_load_demand_kva;
    }
    public ZonedDateTime getLoad_demand_date() {
        return load_demand_date;
    }

    public void setLoad_demand_date(ZonedDateTime load_demand_date) {
        this.load_demand_date = load_demand_date;
    }
    public ByteBuffer getRegular_connection() {
        return regular_connection;
    }

    public void setRegular_connection(ByteBuffer regular_connection) {
        this.regular_connection = regular_connection;
    }

    public String getRegular_connectionContentType() {
        return regular_connectionContentType;
    }

    public void setRegular_connectionContentType(String regular_connectionContentType) {
        this.regular_connectionContentType = regular_connectionContentType;
    }
    public Boolean getRegular_load_required() {
        return regular_load_required;
    }

    public void setRegular_load_required(Boolean regular_load_required) {
        this.regular_load_required = regular_load_required;
    }
    public Boolean getRegular_existing_connection() {
        return regular_existing_connection;
    }

    public void setRegular_existing_connection(Boolean regular_existing_connection) {
        this.regular_existing_connection = regular_existing_connection;
    }
    public UUID getRegular_uhbvn_dhbvn_customer_type() {
        return regular_uhbvn_dhbvn_customer_type;
    }

    public void setRegular_uhbvn_dhbvn_customer_type(UUID regular_uhbvn_dhbvn_customer_type) {
        this.regular_uhbvn_dhbvn_customer_type = regular_uhbvn_dhbvn_customer_type;
    }
    public String getRegular_account_number() {
        return regular_account_number;
    }

    public void setRegular_account_number(String regular_account_number) {
        this.regular_account_number = regular_account_number;
    }
    public BigDecimal getRegular_load_if_any_kw() {
        return regular_load_if_any_kw;
    }

    public void setRegular_load_if_any_kw(BigDecimal regular_load_if_any_kw) {
        this.regular_load_if_any_kw = regular_load_if_any_kw;
    }
    public BigDecimal getRegular_existing_load_kva() {
        return regular_existing_load_kva;
    }

    public void setRegular_existing_load_kva(BigDecimal regular_existing_load_kva) {
        this.regular_existing_load_kva = regular_existing_load_kva;
    }
    public BigDecimal getRegular_new_load_demand_kw() {
        return regular_new_load_demand_kw;
    }

    public void setRegular_new_load_demand_kw(BigDecimal regular_new_load_demand_kw) {
        this.regular_new_load_demand_kw = regular_new_load_demand_kw;
    }
    public BigDecimal getRegular_new_load_demand_kva() {
        return regular_new_load_demand_kva;
    }

    public void setRegular_new_load_demand_kva(BigDecimal regular_new_load_demand_kva) {
        this.regular_new_load_demand_kva = regular_new_load_demand_kva;
    }
    public ZonedDateTime getRegular_demand_date() {
        return regular_demand_date;
    }

    public void setRegular_demand_date(ZonedDateTime regular_demand_date) {
        this.regular_demand_date = regular_demand_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Project_electricity_detailDTO project_electricity_detailDTO = (Project_electricity_detailDTO) o;

        if ( ! Objects.equals(id, project_electricity_detailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project_electricity_detailDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", temporaryconnection='" + temporaryconnection + "'" +
            ", load_required='" + load_required + "'" +
            ", existing_connecton='" + existing_connecton + "'" +
            ", account_number='" + account_number + "'" +
            ", load_demand='" + load_demand + "'" +
            ", load_demand_kva='" + load_demand_kva + "'" +
            ", new_load_demand_kw='" + new_load_demand_kw + "'" +
            ", new_load_demand_kva='" + new_load_demand_kva + "'" +
            ", load_demand_date='" + load_demand_date + "'" +
            ", regular_connection='" + regular_connection + "'" +
            ", regular_load_required='" + regular_load_required + "'" +
            ", regular_existing_connection='" + regular_existing_connection + "'" +
            ", regular_uhbvn_dhbvn_customer_type='" + regular_uhbvn_dhbvn_customer_type + "'" +
            ", regular_account_number='" + regular_account_number + "'" +
            ", regular_load_if_any_kw='" + regular_load_if_any_kw + "'" +
            ", regular_existing_load_kva='" + regular_existing_load_kva + "'" +
            ", regular_new_load_demand_kw='" + regular_new_load_demand_kw + "'" +
            ", regular_new_load_demand_kva='" + regular_new_load_demand_kva + "'" +
            ", regular_demand_date='" + regular_demand_date + "'" +
            '}';
    }
}
