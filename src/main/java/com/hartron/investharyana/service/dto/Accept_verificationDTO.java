package com.hartron.investharyana.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Accept_verification entity.
 */
public class Accept_verificationDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private UUID investorid;

    private Boolean acceptcondition;

    private ZonedDateTime applicationdate;

    private ByteBuffer signature_document;
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

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }
    public UUID getInvestorid() {
        return investorid;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }
    public Boolean getAcceptcondition() {
        return acceptcondition;
    }

    public void setAcceptcondition(Boolean acceptcondition) {
        this.acceptcondition = acceptcondition;
    }
    public ZonedDateTime getApplicationdate() {
        return applicationdate;
    }

    public void setApplicationdate(ZonedDateTime applicationdate) {
        this.applicationdate = applicationdate;
    }
    public ByteBuffer getSignature_document() {
        return signature_document;
    }

    public void setSignature_document(ByteBuffer signature_document) {
        this.signature_document = signature_document;
    }

    public String getSignature_documentContentType() {
        return signature_documentContentType;
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

        Accept_verificationDTO accept_verificationDTO = (Accept_verificationDTO) o;

        if ( ! Objects.equals(id, accept_verificationDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Accept_verificationDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", investorid='" + investorid + "'" +
            ", acceptcondition='" + acceptcondition + "'" +
            ", applicationdate='" + applicationdate + "'" +
            ", signature_document='" + signature_document + "'" +
            '}';
    }
}
