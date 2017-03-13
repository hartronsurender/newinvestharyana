package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Projectprocess_flowsteps.
 */

@Table(name = "projectprocess_flowsteps")
public class Projectprocess_flowsteps implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private UUID projectid;

    private ByteBuffer process_flow_document;

    @Column(name = "process_flow_document_content_type")
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

    public Projectprocess_flowsteps projectid(UUID projectid) {
        this.projectid = projectid;
        return this;
    }

    public void setProjectid(UUID projectid) {
        this.projectid = projectid;
    }

    public ByteBuffer getProcess_flow_document() {
        return process_flow_document;
    }

    public Projectprocess_flowsteps process_flow_document(ByteBuffer process_flow_document) {
        this.process_flow_document = process_flow_document;
        return this;
    }

    public void setProcess_flow_document(ByteBuffer process_flow_document) {
        this.process_flow_document = process_flow_document;
    }

    public String getProcess_flow_documentContentType() {
        return process_flow_documentContentType;
    }

    public Projectprocess_flowsteps process_flow_documentContentType(String process_flow_documentContentType) {
        this.process_flow_documentContentType = process_flow_documentContentType;
        return this;
    }

    public void setProcess_flow_documentContentType(String process_flow_documentContentType) {
        this.process_flow_documentContentType = process_flow_documentContentType;
    }

    public String getSteps() {
        return steps;
    }

    public Projectprocess_flowsteps steps(String steps) {
        this.steps = steps;
        return this;
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
        Projectprocess_flowsteps projectprocess_flowsteps = (Projectprocess_flowsteps) o;
        if (projectprocess_flowsteps.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, projectprocess_flowsteps.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Projectprocess_flowsteps{" +
            "id=" + id +
            ", projectid='" + projectid + "'" +
            ", process_flow_document='" + process_flow_document + "'" +
            ", process_flow_documentContentType='" + process_flow_documentContentType + "'" +
            ", steps='" + steps + "'" +
            '}';
    }
}
