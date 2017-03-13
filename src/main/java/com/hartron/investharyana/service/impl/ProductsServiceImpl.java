package com.hartron.investharyana.service.impl;

import com.hartron.investharyana.service.ProductsService;
import com.hartron.investharyana.domain.Products;
import com.hartron.investharyana.repository.ProductsRepository;
import com.hartron.investharyana.service.dto.ProductsDTO;
import com.hartron.investharyana.service.mapper.ProductsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Products.
 */
@Service
public class ProductsServiceImpl implements ProductsService{

    private final Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);
    
    private final ProductsRepository productsRepository;

    private final ProductsMapper productsMapper;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductsMapper productsMapper) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
    }

    /**
     * Save a products.
     *
     * @param productsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductsDTO save(ProductsDTO productsDTO) {
        log.debug("Request to save Products : {}", productsDTO);
        Products products = productsMapper.productsDTOToProducts(productsDTO);
        products = productsRepository.save(products);
        ProductsDTO result = productsMapper.productsToProductsDTO(products);
        return result;
    }

    /**
     *  Get all the products.
     *  
     *  @return the list of entities
     */
    @Override
    public List<ProductsDTO> findAll() {
        log.debug("Request to get all Products");
        List<ProductsDTO> result = productsRepository.findAll().stream()
            .map(productsMapper::productsToProductsDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one products by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public ProductsDTO findOne(String id) {
        log.debug("Request to get Products : {}", id);
        Products products = productsRepository.findOne(UUID.fromString(id));
        ProductsDTO productsDTO = productsMapper.productsToProductsDTO(products);
        return productsDTO;
    }

    /**
     *  Delete the  products by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Products : {}", id);
        productsRepository.delete(UUID.fromString(id));
    }
}
