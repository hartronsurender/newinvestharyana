
CREATE TABLE IF NOT EXISTS environment_impact_detail (
    id uuid,
    projectid uuid,
    water_supply_source uuid,
    water_usage_process int,
    water_usage_cooling int,
    water_usage_domestic int,
    water_usage_other text,
    water_waste_process int,
    water_waste_cooling int,
    water_waste_domesting int,
    water_waste_other text,
    waste_water_treatment text,
    waste_water_treatment_document blob,
    waste_water_treatment_document_content_type text,
    mode_of_disposal uuid,
    emissionid uuid,
    water_waste_id uuid,
    PRIMARY KEY(id)
);
