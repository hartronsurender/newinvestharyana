package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Project_electricity_detail.
 */

@Table(name = "project_electricity_detail")
public class Project_electricity_detail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private ByteBuffer temporaryconnection;

    @Column(name = "temporaryconnection_content_type")
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

    @Column(name = "regular_connection_content_type")
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

    public Project_electricity_detail projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public ByteBuffer getTemporaryconnection() {
        return temporaryconnection;
    }

    public Project_electricity_detail temporaryconnection(ByteBuffer temporaryconnection) {
        this.temporaryconnection = temporaryconnection;
        return this;
    }

    public void setTemporaryconnection(ByteBuffer temporaryconnection) {
        this.temporaryconnection = temporaryconnection;
    }

    public String getTemporaryconnectionContentType() {
        return temporaryconnectionContentType;
    }

    public Project_electricity_detail temporaryconnectionContentType(String temporaryconnectionContentType) {
        this.temporaryconnectionContentType = temporaryconnectionContentType;
        return this;
    }

    public void setTemporaryconnectionContentType(String temporaryconnectionContentType) {
        this.temporaryconnectionContentType = temporaryconnectionContentType;
    }

    public Boolean isLoad_required() {
        return load_required;
    }

    public Project_electricity_detail load_required(Boolean load_required) {
        this.load_required = load_required;
        return this;
    }

    public void setLoad_required(Boolean load_required) {
        this.load_required = load_required;
    }

    public Boolean isExisting_connecton() {
        return existing_connecton;
    }

    public Project_electricity_detail existing_connecton(Boolean existing_connecton) {
        this.existing_connecton = existing_connecton;
        return this;
    }

    public void setExisting_connecton(Boolean existing_connecton) {
        this.existing_connecton = existing_connecton;
    }

    public String getAccount_number() {
        return account_number;
    }

    public Project_electricity_detail account_number(String account_number) {
        this.account_number = account_number;
        return this;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public BigDecimal getLoad_demand() {
        return load_demand;
    }

    public Project_electricity_detail load_demand(BigDecimal load_demand) {
        this.load_demand = load_demand;
        return this;
    }

    public void setLoad_demand(BigDecimal load_demand) {
        this.load_demand = load_demand;
    }

    public BigDecimal getLoad_demand_kva() {
        return load_demand_kva;
    }

    public Project_electricity_detail load_demand_kva(BigDecimal load_demand_kva) {
        this.load_demand_kva = load_demand_kva;
        return this;
    }

    public void setLoad_demand_kva(BigDecimal load_demand_kva) {
        this.load_demand_kva = load_demand_kva;
    }

    public BigDecimal getNew_load_demand_kw() {
        return new_load_demand_kw;
    }

    public Project_electricity_detail new_load_demand_kw(BigDecimal new_load_demand_kw) {
        this.new_load_demand_kw = new_load_demand_kw;
        return this;
    }

    public void setNew_load_demand_kw(BigDecimal new_load_demand_kw) {
        this.new_load_demand_kw = new_load_demand_kw;
    }

    public BigDecimal getNew_load_demand_kva() {
        return new_load_demand_kva;
    }

    public Project_electricity_detail new_load_demand_kva(BigDecimal new_load_demand_kva) {
        this.new_load_demand_kva = new_load_demand_kva;
        return this;
    }

    public void setNew_load_demand_kva(BigDecimal new_load_demand_kva) {
        this.new_load_demand_kva = new_load_demand_kva;
    }

    public ZonedDateTime getLoad_demand_date() {
        return load_demand_date;
    }

    public Project_electricity_detail load_demand_date(ZonedDateTime load_demand_date) {
        this.load_demand_date = load_demand_date;
        return this;
    }

    public void setLoad_demand_date(ZonedDateTime load_demand_date) {
        this.load_demand_date = load_demand_date;
    }

    public ByteBuffer getRegular_connection() {
        return regular_connection;
    }

    public Project_electricity_detail regular_connection(ByteBuffer regular_connection) {
        this.regular_connection = regular_connection;
        return this;
    }

    public void setRegular_connection(ByteBuffer regular_connection) {
        this.regular_connection = regular_connection;
    }

    public String getRegular_connectionContentType() {
        return regular_connectionContentType;
    }

    public Project_electricity_detail regular_connectionContentType(String regular_connectionContentType) {
        this.regular_connectionContentType = regular_connectionContentType;
        return this;
    }

    public void setRegular_connectionContentType(String regular_connectionContentType) {
        this.regular_connectionContentType = regular_connectionContentType;
    }

    public Boolean isRegular_load_required() {
        return regular_load_required;
    }

    public Project_electricity_detail regular_load_required(Boolean regular_load_required) {
        this.regular_load_required = regular_load_required;
        return this;
    }

    public void setRegular_load_required(Boolean regular_load_required) {
        this.regular_load_required = regular_load_required;
    }

    public Boolean isRegular_existing_connection() {
        return regular_existing_connection;
    }

    public Project_electricity_detail regular_existing_connection(Boolean regular_existing_connection) {
        this.regular_existing_connection = regular_existing_connection;
        return this;
    }

    public void setRegular_existing_connection(Boolean regular_existing_connection) {
        this.regular_existing_connection = regular_existing_connection;
    }

    public UUID getRegular_uhbvn_dhbvn_customer_type() {
        return regular_uhbvn_dhbvn_customer_type;
    }

    public Project_electricity_detail regular_uhbvn_dhbvn_customer_type(UUID regular_uhbvn_dhbvn_customer_type) {
        this.regular_uhbvn_dhbvn_customer_type = regular_uhbvn_dhbvn_customer_type;
        return this;
    }

    public void setRegular_uhbvn_dhbvn_customer_type(UUID regular_uhbvn_dhbvn_customer_type) {
        this.regular_uhbvn_dhbvn_customer_type = regular_uhbvn_dhbvn_customer_type;
    }

    public String getRegular_account_number() {
        return regular_account_number;
    }

    public Project_electricity_detail regular_account_number(String regular_account_number) {
        this.regular_account_number = regular_account_number;
        return this;
    }

    public void setRegular_account_number(String regular_account_number) {
        this.regular_account_number = regular_account_number;
    }

    public BigDecimal getRegular_load_if_any_kw() {
        return regular_load_if_any_kw;
    }

    public Project_electricity_detail regular_load_if_any_kw(BigDecimal regular_load_if_any_kw) {
        this.regular_load_if_any_kw = regular_load_if_any_kw;
        return this;
    }

    public void setRegular_load_if_any_kw(BigDecimal regular_load_if_any_kw) {
        this.regular_load_if_any_kw = regular_load_if_any_kw;
    }

    public BigDecimal getRegular_existing_load_kva() {
        return regular_existing_load_kva;
    }

    public Project_electricity_detail regular_existing_load_kva(BigDecimal regular_existing_load_kva) {
        this.regular_existing_load_kva = regular_existing_load_kva;
        return this;
    }

    public void setRegular_existing_load_kva(BigDecimal regular_existing_load_kva) {
        this.regular_existing_load_kva = regular_existing_load_kva;
    }

    public BigDecimal getRegular_new_load_demand_kw() {
        return regular_new_load_demand_kw;
    }

    public Project_electricity_detail regular_new_load_demand_kw(BigDecimal regular_new_load_demand_kw) {
        this.regular_new_load_demand_kw = regular_new_load_demand_kw;
        return this;
    }

    public void setRegular_new_load_demand_kw(BigDecimal regular_new_load_demand_kw) {
        this.regular_new_load_demand_kw = regular_new_load_demand_kw;
    }

    public BigDecimal getRegular_new_load_demand_kva() {
        return regular_new_load_demand_kva;
    }

    public Project_electricity_detail regular_new_load_demand_kva(BigDecimal regular_new_load_demand_kva) {
        this.regular_new_load_demand_kva = regular_new_load_demand_kva;
        return this;
    }

    public void setRegular_new_load_demand_kva(BigDecimal regular_new_load_demand_kva) {
        this.regular_new_load_demand_kva = regular_new_load_demand_kva;
    }

    public ZonedDateTime getRegular_demand_date() {
        return regular_demand_date;
    }

    public Project_electricity_detail regular_demand_date(ZonedDateTime regular_demand_date) {
        this.regular_demand_date = regular_demand_date;
        return this;
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
        Project_electricity_detail project_electricity_detail = (Project_electricity_detail) o;
        if (project_electricity_detail.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, project_electricity_detail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Project_electricity_detail{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", temporaryconnection='" + temporaryconnection + "'" +
            ", temporaryconnectionContentType='" + temporaryconnectionContentType + "'" +
            ", load_required='" + load_required + "'" +
            ", existing_connecton='" + existing_connecton + "'" +
            ", account_number='" + account_number + "'" +
            ", load_demand='" + load_demand + "'" +
            ", load_demand_kva='" + load_demand_kva + "'" +
            ", new_load_demand_kw='" + new_load_demand_kw + "'" +
            ", new_load_demand_kva='" + new_load_demand_kva + "'" +
            ", load_demand_date='" + load_demand_date + "'" +
            ", regular_connection='" + regular_connection + "'" +
            ", regular_connectionContentType='" + regular_connectionContentType + "'" +
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
