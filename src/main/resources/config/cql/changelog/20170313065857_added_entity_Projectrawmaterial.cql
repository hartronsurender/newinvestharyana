
CREATE TABLE IF NOT EXISTS projectrawmaterial (
    id uuid,
    projectid uuid,
    raw_material text,
    raw_quantity int,
    raw_unit uuid,
    PRIMARY KEY(id)
);
