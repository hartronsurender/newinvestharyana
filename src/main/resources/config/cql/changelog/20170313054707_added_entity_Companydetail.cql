
CREATE TABLE IF NOT EXISTS companydetail (
    id uuid,
    investorid uuid,
    promoter_md_director text,
    designation text,
    businessentity text,
    businesstype uuid,
    number_of_director_mds_ceo int,
    list_director_md_ceo blob,
    list_director_md_ceo_content_type text,
    pannumber text,
    pancard blob,
    pancard_content_type text,
    aadharnumber double,
    aadharcard blob,
    aadharcard_content_type text,
    nri boolean,
    tin_vat_no text,
    tin_vat_document blob,
    tin_vat_document_content_type text,
    cstno text,
    cstdocument blob,
    cstdocument_content_type text,
    moa_partnershipdeed_document blob,
    moa_partnershipdeed_document_content_type text,
    registration_document blob,
    registration_document_content_type text,
    PRIMARY KEY(id)
);
