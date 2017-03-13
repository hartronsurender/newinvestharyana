
CREATE TABLE IF NOT EXISTS accept_verification (
    id uuid,
    projectid uuid,
    investorid uuid,
    acceptcondition boolean,
    applicationdate timestamp,
    signature_document blob,
    signature_document_content_type text,
    PRIMARY KEY(id)
);
