package com.hartron.investharyana.service.mapper;

import com.hartron.investharyana.domain.*;
import com.hartron.investharyana.service.dto.ProductsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Products and its DTO ProductsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductsMapper {

    ProductsDTO productsToProductsDTO(Products products);

    List<ProductsDTO> productsToProductsDTOs(List<Products> products);

    Products productsDTOToProducts(ProductsDTO productsDTO);

    List<Products> productsDTOsToProducts(List<ProductsDTO> productsDTOs);
}
