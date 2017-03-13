
CREATE TABLE IF NOT EXISTS financeinvestment (
    id uuid,
    projectid uuid,
    land_cost decimal,
    building_cost decimal,
    plan_machinery_cost decimal,
    misc_assets decimal,
    totalproject_cost decimal,
    fdi_applicable boolean,
    fdi_country uuid,
    fdi_value decimal,
    fdi_resource uuid,
    project_construction_start_date timestamp,
    project_commercial_activity_date timestamp,
    PRIMARY KEY(id)
);
