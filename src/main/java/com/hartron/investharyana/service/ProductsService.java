package com.hartron.investharyana.service;

import com.hartron.investharyana.service.dto.ProductsDTO;
import java.util.List;

/**
 * Service Interface for managing Products.
 */
public interface ProductsService {

    /**
     * Save a products.
     *
     * @param productsDTO the entity to save
     * @return the persisted entity
     */
    ProductsDTO save(ProductsDTO productsDTO);

    /**
     *  Get all the products.
     *  
     *  @return the list of entities
     */
    List<ProductsDTO> findAll();

    /**
     *  Get the "id" products.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProductsDTO findOne(String id);

    /**
     *  Delete the "id" products.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
