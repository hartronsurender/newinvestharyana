
CREATE TABLE IF NOT EXISTS projectphase (
    id uuid,
    projectid uuid,
    fciphase text,
    fci_product_category text,
    fci text,
    fic_implementation_date timestamp,
    PRIMARY KEY(id)
);
