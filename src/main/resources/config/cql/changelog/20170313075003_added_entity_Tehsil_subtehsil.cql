
CREATE TABLE IF NOT EXISTS tehsil_subtehsil (
    id uuid,
    districtid uuid,
    blockid uuid,
    city_town_villageid uuid,
    tehsilname text,
    PRIMARY KEY(id)
);
