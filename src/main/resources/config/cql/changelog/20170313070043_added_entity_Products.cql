
CREATE TABLE IF NOT EXISTS products (
    id uuid,
    projectid uuid,
    main_product text,
    product_quantity int,
    units uuid,
    PRIMARY KEY(id)
);
