package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Products;
import com.hartron.investharyana.repository.ProductsRepository;
import com.hartron.investharyana.service.ProductsService;
import com.hartron.investharyana.service.dto.ProductsDTO;
import com.hartron.investharyana.service.mapper.ProductsMapper;
import com.hartron.investharyana.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductsResource REST controller.
 *
 * @see ProductsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class ProductsResourceIntTest extends AbstractCassandraTest {

    private static final UUID DEFAULT_PROJECTID = UUID.randomUUID();
    private static final UUID UPDATED_PROJECTID = UUID.randomUUID();

    private static final String DEFAULT_MAIN_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_PRODUCT = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCT_QUANTITY = 1;
    private static final Integer UPDATED_PRODUCT_QUANTITY = 2;

    private static final UUID DEFAULT_UNITS = UUID.randomUUID();
    private static final UUID UPDATED_UNITS = UUID.randomUUID();

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProductsMockMvc;

    private Products products;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductsResource productsResource = new ProductsResource(productsService);
        this.restProductsMockMvc = MockMvcBuilders.standaloneSetup(productsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Products createEntity() {
        Products products = new Products()
                .projectid(DEFAULT_PROJECTID)
                .main_product(DEFAULT_MAIN_PRODUCT)
                .product_quantity(DEFAULT_PRODUCT_QUANTITY)
                .units(DEFAULT_UNITS);
        return products;
    }

    @Before
    public void initTest() {
        productsRepository.deleteAll();
        products = createEntity();
    }

    @Test
    public void createProducts() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products
        ProductsDTO productsDTO = productsMapper.productsToProductsDTO(products);

        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isCreated());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate + 1);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getProjectid()).isEqualTo(DEFAULT_PROJECTID);
        assertThat(testProducts.getMain_product()).isEqualTo(DEFAULT_MAIN_PRODUCT);
        assertThat(testProducts.getProduct_quantity()).isEqualTo(DEFAULT_PRODUCT_QUANTITY);
        assertThat(testProducts.getUnits()).isEqualTo(DEFAULT_UNITS);
    }

    @Test
    public void createProductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products with an existing ID
        Products existingProducts = new Products();
        existingProducts.setId(UUID.randomUUID());
        ProductsDTO existingProductsDTO = productsMapper.productsToProductsDTO(existingProducts);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingProductsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProducts() throws Exception {
        // Initialize the database
        productsRepository.save(products);

        // Get all the productsList
        restProductsMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(products.getId().toString())))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID.toString())))
            .andExpect(jsonPath("$.[*].main_product").value(hasItem(DEFAULT_MAIN_PRODUCT.toString())))
            .andExpect(jsonPath("$.[*].product_quantity").value(hasItem(DEFAULT_PRODUCT_QUANTITY)))
            .andExpect(jsonPath("$.[*].units").value(hasItem(DEFAULT_UNITS.toString())));
    }

    @Test
    public void getProducts() throws Exception {
        // Initialize the database
        productsRepository.save(products);

        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", products.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(products.getId().toString()))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID.toString()))
            .andExpect(jsonPath("$.main_product").value(DEFAULT_MAIN_PRODUCT.toString()))
            .andExpect(jsonPath("$.product_quantity").value(DEFAULT_PRODUCT_QUANTITY))
            .andExpect(jsonPath("$.units").value(DEFAULT_UNITS.toString()));
    }

    @Test
    public void getNonExistingProducts() throws Exception {
        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProducts() throws Exception {
        // Initialize the database
        productsRepository.save(products);
        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Update the products
        Products updatedProducts = productsRepository.findOne(products.getId());
        updatedProducts
                .projectid(UPDATED_PROJECTID)
                .main_product(UPDATED_MAIN_PRODUCT)
                .product_quantity(UPDATED_PRODUCT_QUANTITY)
                .units(UPDATED_UNITS);
        ProductsDTO productsDTO = productsMapper.productsToProductsDTO(updatedProducts);

        restProductsMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isOk());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getProjectid()).isEqualTo(UPDATED_PROJECTID);
        assertThat(testProducts.getMain_product()).isEqualTo(UPDATED_MAIN_PRODUCT);
        assertThat(testProducts.getProduct_quantity()).isEqualTo(UPDATED_PRODUCT_QUANTITY);
        assertThat(testProducts.getUnits()).isEqualTo(UPDATED_UNITS);
    }

    @Test
    public void updateNonExistingProducts() throws Exception {
        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Create the Products
        ProductsDTO productsDTO = productsMapper.productsToProductsDTO(products);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductsMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productsDTO)))
            .andExpect(status().isCreated());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProducts() throws Exception {
        // Initialize the database
        productsRepository.save(products);
        int databaseSizeBeforeDelete = productsRepository.findAll().size();

        // Get the products
        restProductsMockMvc.perform(delete("/api/products/{id}", products.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Products.class);
    }
}
