
CREATE TABLE IF NOT EXISTS projectprocess_flowsteps (
    id uuid,
    projectid uuid,
    process_flow_document blob,
    process_flow_document_content_type text,
    steps text,
    PRIMARY KEY(id)
);
