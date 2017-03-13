
CREATE TABLE IF NOT EXISTS city_town_village (
    id uuid,
    stateid uuid,
    districtid uuid,
    blockid uuid,
    city_town_village_name text,
    description text,
    PRIMARY KEY(id)
);
