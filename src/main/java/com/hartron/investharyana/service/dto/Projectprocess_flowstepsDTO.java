package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Projectprocess_flowsteps entity.
 */
public class Projectprocess_flowstepsDTO implements Serializable {

    private UUID id;

    private UUID projectid;

    private ByteBuffer process_flow_document;
    private String process_flow_documentContentType;

    private String steps;

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
    public ByteBuffer getProcess_flow_document() {
        return process_flow_document;
    }

    public void setProcess_flow_document(ByteBuffer process_flow_document) {
        this.process_flow_document = process_flow_document;
    }

    public String getProcess_flow_documentContentType() {
        return process_flow_documentContentType;
    }

    public void setProcess_flow_documentContentType(String process_flow_documentContentType) {
        this.process_flow_documentContentType = process_flow_documentContentType;
    }
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Projectprocess_flowstepsDTO projectprocess_flowstepsDTO = (Projectprocess_flowstepsDTO) o;

        if ( ! Objects.equals(id, projectprocess_flowstepsDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectprocess_flowstepsDTO{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", process_flow_document='" + process_flow_document + "'" +
            ", steps='" + steps + "'" +
            '}';
    }
}
