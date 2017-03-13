package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A Accept_verification.
 */

@Table(name = "accept_verification")
public class Accept_verification implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private UUID investorid;

    private Boolean acceptcondition;

    private ZonedDateTime applicationdate;

    private ByteBuffer signature_document;

    @Column(name = "signature_document_content_type")
    private String signature_documentContentType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectid() {
        return projectid;
    }

    public Accept_verification projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public UUID getInvestorid() {
        return investorid;
    }

    public Accept_verification investorid(UUID investorid) {
        this.investorid = investorid;
        return this;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }

    public Boolean isAcceptcondition() {
        return acceptcondition;
    }

    public Accept_verification acceptcondition(Boolean acceptcondition) {
        this.acceptcondition = acceptcondition;
        return this;
    }

    public void setAcceptcondition(Boolean acceptcondition) {
        this.acceptcondition = acceptcondition;
    }

    public ZonedDateTime getApplicationdate() {
        return applicationdate;
    }

    public Accept_verification applicationdate(ZonedDateTime applicationdate) {
        this.applicationdate = applicationdate;
        return this;
    }

    public void setApplicationdate(ZonedDateTime applicationdate) {
        this.applicationdate = applicationdate;
    }

    public ByteBuffer getSignature_document() {
        return signature_document;
    }

    public Accept_verification signature_document(ByteBuffer signature_document) {
        this.signature_document = signature_document;
        return this;
    }

    public void setSignature_document(ByteBuffer signature_document) {
        this.signature_document = signature_document;
    }

    public String getSignature_documentContentType() {
        return signature_documentContentType;
    }

    public Accept_verification signature_documentContentType(String signature_documentContentType) {
        this.signature_documentContentType = signature_documentContentType;
        return this;
    }

    public void setSignature_documentContentType(String signature_documentContentType) {
        this.signature_documentContentType = signature_documentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accept_verification accept_verification = (Accept_verification) o;
        if (accept_verification.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, accept_verification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Accept_verification{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", investorid='" + investorid + "'" +
            ", acceptcondition='" + acceptcondition + "'" +
            ", applicationdate='" + applicationdate + "'" +
            ", signature_document='" + signature_document + "'" +
            ", signature_documentContentType='" + signature_documentContentType + "'" +
            '}';
    }
}
