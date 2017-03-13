package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Industrysize;
import com.hartron.investharyana.repository.IndustrysizeRepository;
import com.hartron.investharyana.service.IndustrysizeService;
import com.hartron.investharyana.service.dto.IndustrysizeDTO;
import com.hartron.investharyana.service.mapper.IndustrysizeMapper;
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
 * Test class for the IndustrysizeResource REST controller.
 *
 * @see IndustrysizeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class IndustrysizeResourceIntTest extends AbstractCassandraTest {

    private static final String DEFAULT_SIZE_OF_INDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_SIZE_OF_INDUSTRY = "BBBBBBBBBB";

    @Autowired
    private IndustrysizeRepository industrysizeRepository;

    @Autowired
    private IndustrysizeMapper industrysizeMapper;

    @Autowired
    private IndustrysizeService industrysizeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restIndustrysizeMockMvc;

    private Industrysize industrysize;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IndustrysizeResource industrysizeResource = new IndustrysizeResource(industrysizeService);
        this.restIndustrysizeMockMvc = MockMvcBuilders.standaloneSetup(industrysizeResource)
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
    public static Industrysize createEntity() {
        Industrysize industrysize = new Industrysize()
                .size_of_industry(DEFAULT_SIZE_OF_INDUSTRY);
        return industrysize;
    }

    @Before
    public void initTest() {
        industrysizeRepository.deleteAll();
        industrysize = createEntity();
    }

    @Test
    public void createIndustrysize() throws Exception {
        int databaseSizeBeforeCreate = industrysizeRepository.findAll().size();

        // Create the Industrysize
        IndustrysizeDTO industrysizeDTO = industrysizeMapper.industrysizeToIndustrysizeDTO(industrysize);

        restIndustrysizeMockMvc.perform(post("/api/industrysizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industrysizeDTO)))
            .andExpect(status().isCreated());

        // Validate the Industrysize in the database
        List<Industrysize> industrysizeList = industrysizeRepository.findAll();
        assertThat(industrysizeList).hasSize(databaseSizeBeforeCreate + 1);
        Industrysize testIndustrysize = industrysizeList.get(industrysizeList.size() - 1);
        assertThat(testIndustrysize.getSize_of_industry()).isEqualTo(DEFAULT_SIZE_OF_INDUSTRY);
    }

    @Test
    public void createIndustrysizeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = industrysizeRepository.findAll().size();

        // Create the Industrysize with an existing ID
        Industrysize existingIndustrysize = new Industrysize();
        existingIndustrysize.setId(UUID.randomUUID());
        IndustrysizeDTO existingIndustrysizeDTO = industrysizeMapper.industrysizeToIndustrysizeDTO(existingIndustrysize);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndustrysizeMockMvc.perform(post("/api/industrysizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingIndustrysizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Industrysize> industrysizeList = industrysizeRepository.findAll();
        assertThat(industrysizeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllIndustrysizes() throws Exception {
        // Initialize the database
        industrysizeRepository.save(industrysize);

        // Get all the industrysizeList
        restIndustrysizeMockMvc.perform(get("/api/industrysizes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(industrysize.getId().toString())))
            .andExpect(jsonPath("$.[*].size_of_industry").value(hasItem(DEFAULT_SIZE_OF_INDUSTRY.toString())));
    }

    @Test
    public void getIndustrysize() throws Exception {
        // Initialize the database
        industrysizeRepository.save(industrysize);

        // Get the industrysize
        restIndustrysizeMockMvc.perform(get("/api/industrysizes/{id}", industrysize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(industrysize.getId().toString()))
            .andExpect(jsonPath("$.size_of_industry").value(DEFAULT_SIZE_OF_INDUSTRY.toString()));
    }

    @Test
    public void getNonExistingIndustrysize() throws Exception {
        // Get the industrysize
        restIndustrysizeMockMvc.perform(get("/api/industrysizes/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIndustrysize() throws Exception {
        // Initialize the database
        industrysizeRepository.save(industrysize);
        int databaseSizeBeforeUpdate = industrysizeRepository.findAll().size();

        // Update the industrysize
        Industrysize updatedIndustrysize = industrysizeRepository.findOne(industrysize.getId());
        updatedIndustrysize
                .size_of_industry(UPDATED_SIZE_OF_INDUSTRY);
        IndustrysizeDTO industrysizeDTO = industrysizeMapper.industrysizeToIndustrysizeDTO(updatedIndustrysize);

        restIndustrysizeMockMvc.perform(put("/api/industrysizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industrysizeDTO)))
            .andExpect(status().isOk());

        // Validate the Industrysize in the database
        List<Industrysize> industrysizeList = industrysizeRepository.findAll();
        assertThat(industrysizeList).hasSize(databaseSizeBeforeUpdate);
        Industrysize testIndustrysize = industrysizeList.get(industrysizeList.size() - 1);
        assertThat(testIndustrysize.getSize_of_industry()).isEqualTo(UPDATED_SIZE_OF_INDUSTRY);
    }

    @Test
    public void updateNonExistingIndustrysize() throws Exception {
        int databaseSizeBeforeUpdate = industrysizeRepository.findAll().size();

        // Create the Industrysize
        IndustrysizeDTO industrysizeDTO = industrysizeMapper.industrysizeToIndustrysizeDTO(industrysize);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIndustrysizeMockMvc.perform(put("/api/industrysizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industrysizeDTO)))
            .andExpect(status().isCreated());

        // Validate the Industrysize in the database
        List<Industrysize> industrysizeList = industrysizeRepository.findAll();
        assertThat(industrysizeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteIndustrysize() throws Exception {
        // Initialize the database
        industrysizeRepository.save(industrysize);
        int databaseSizeBeforeDelete = industrysizeRepository.findAll().size();

        // Get the industrysize
        restIndustrysizeMockMvc.perform(delete("/api/industrysizes/{id}", industrysize.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Industrysize> industrysizeList = industrysizeRepository.findAll();
        assertThat(industrysizeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Industrysize.class);
    }
}
