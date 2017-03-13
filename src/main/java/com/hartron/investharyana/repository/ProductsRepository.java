package com.hartron.investharyana.repository;

import com.hartron.investharyana.domain.Products;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the Products entity.
 */
@Repository
public class ProductsRepository {

    private final Session session;

    private Mapper<Products> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public ProductsRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(Products.class);
        this.findAllStmt = session.prepare("SELECT * FROM products");
        this.truncateStmt = session.prepare("TRUNCATE products");
    }

    public List<Products> findAll() {
        List<Products> productsList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Products products = new Products();
                products.setId(row.getUUID("id"));
                products.setProjectid(row.getUUID("projectid"));
                products.setMain_product(row.getString("main_product"));
                products.setProduct_quantity(row.getInt("product_quantity"));
                products.setUnits(row.getUUID("units"));
                return products;
            }
        ).forEach(productsList::add);
        return productsList;
    }

    public Products findOne(UUID id) {
        return mapper.get(id);
    }

    public Products save(Products products) {
        if (products.getId() == null) {
            products.setId(UUID.randomUUID());
        }
        mapper.save(products);
        return products;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
