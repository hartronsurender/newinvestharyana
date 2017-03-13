
CREATE TABLE IF NOT EXISTS waste_water_detail (
    id uuid,
    projectid uuid,
    source_of_generation text,
    nature_type uuid,
    quantity text,
    mode_of_disposal uuid,
    PRIMARY KEY(id)
);
