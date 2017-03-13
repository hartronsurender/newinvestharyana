package com.hartron.investharyana.web.rest;

import com.hartron.investharyana.AbstractCassandraTest;
import com.hartron.investharyana.InvesthryApp;

import com.hartron.investharyana.domain.Investor;
import com.hartron.investharyana.repository.InvestorRepository;
import com.hartron.investharyana.service.InvestorService;
import com.hartron.investharyana.service.dto.InvestorDTO;
import com.hartron.investharyana.service.mapper.InvestorMapper;
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
import org.springframework.util.Base64Utils;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InvestorResource REST controller.
 *
 * @see InvestorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvesthryApp.class)
public class InvestorResourceIntTest extends AbstractCassandraTest {

    private static final Boolean DEFAULT_MOUAPPLICABLE = false;
    private static final Boolean UPDATED_MOUAPPLICABLE = true;

    private static final Integer DEFAULT_MOUSIGNYEAR = 1;
    private static final Integer UPDATED_MOUSIGNYEAR = 2;

    private static final ByteBuffer DEFAULT_MOUDOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_MOUDOCUMENT = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_MOUDOCUMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MOUDOCUMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_MOUIDNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOUIDNUMBER = "BBBBBBBBBB";

    private static final ByteBuffer DEFAULT_INVESTORPHOTO = ByteBuffer.wrap(TestUtil.createByteArray(1, "0"));
    private static final ByteBuffer UPDATED_INVESTORPHOTO = ByteBuffer.wrap(TestUtil.createByteArray(2, "1"));
    private static final String DEFAULT_INVESTORPHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_INVESTORPHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_INV_F_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INV_F_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INV_M_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INV_M_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INV_L_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INV_L_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_INVADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INVADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_INVADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_INVADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_INVADDRESS_3 = "BBBBBBBBBB";

    private static final Double DEFAULT_PHONE = 1D;
    private static final Double UPDATED_PHONE = 2D;

    private static final Double DEFAULT_MOBILE = 1D;
    private static final Double UPDATED_MOBILE = 2D;

    private static final Double DEFAULT_FAX = 1D;
    private static final Double UPDATED_FAX = 2D;

    private static final String DEFAULT_EMAILPRIMARY = "AAAAAAAAAA";
    private static final String UPDATED_EMAILPRIMARY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAILSECONDARY = "AAAAAAAAAA";
    private static final String UPDATED_EMAILSECONDARY = "BBBBBBBBBB";

    private static final UUID DEFAULT_COUNTRY = UUID.randomUUID();
    private static final UUID UPDATED_COUNTRY = UUID.randomUUID();

    private static final UUID DEFAULT_STATE = UUID.randomUUID();
    private static final UUID UPDATED_STATE = UUID.randomUUID();

    private static final UUID DEFAULT_CITY = UUID.randomUUID();
    private static final UUID UPDATED_CITY = UUID.randomUUID();

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private InvestorMapper investorMapper;

    @Autowired
    private InvestorService investorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restInvestorMockMvc;

    private Investor investor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InvestorResource investorResource = new InvestorResource(investorService);
        this.restInvestorMockMvc = MockMvcBuilders.standaloneSetup(investorResource)
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
    public static Investor createEntity() {
        Investor investor = new Investor()
                .mouapplicable(DEFAULT_MOUAPPLICABLE)
                .mousignyear(DEFAULT_MOUSIGNYEAR)
                .moudocument(DEFAULT_MOUDOCUMENT)
                .moudocumentContentType(DEFAULT_MOUDOCUMENT_CONTENT_TYPE)
                .mouidnumber(DEFAULT_MOUIDNUMBER)
                .investorphoto(DEFAULT_INVESTORPHOTO)
                .investorphotoContentType(DEFAULT_INVESTORPHOTO_CONTENT_TYPE)
                .inv_f_name(DEFAULT_INV_F_NAME)
                .inv_m_name(DEFAULT_INV_M_NAME)
                .inv_l_name(DEFAULT_INV_L_NAME)
                .invaddress1(DEFAULT_INVADDRESS_1)
                .invaddress2(DEFAULT_INVADDRESS_2)
                .invaddress3(DEFAULT_INVADDRESS_3)
                .phone(DEFAULT_PHONE)
                .mobile(DEFAULT_MOBILE)
                .fax(DEFAULT_FAX)
                .emailprimary(DEFAULT_EMAILPRIMARY)
                .emailsecondary(DEFAULT_EMAILSECONDARY)
                .country(DEFAULT_COUNTRY)
                .state(DEFAULT_STATE)
                .city(DEFAULT_CITY);
        return investor;
    }

    @Before
    public void initTest() {
        investorRepository.deleteAll();
        investor = createEntity();
    }

    @Test
    public void createInvestor() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(investor);

        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate + 1);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.isMouapplicable()).isEqualTo(DEFAULT_MOUAPPLICABLE);
        assertThat(testInvestor.getMousignyear()).isEqualTo(DEFAULT_MOUSIGNYEAR);
        assertThat(testInvestor.getMoudocument()).isEqualTo(DEFAULT_MOUDOCUMENT);
        assertThat(testInvestor.getMoudocumentContentType()).isEqualTo(DEFAULT_MOUDOCUMENT_CONTENT_TYPE);
        assertThat(testInvestor.getMouidnumber()).isEqualTo(DEFAULT_MOUIDNUMBER);
        assertThat(testInvestor.getInvestorphoto()).isEqualTo(DEFAULT_INVESTORPHOTO);
        assertThat(testInvestor.getInvestorphotoContentType()).isEqualTo(DEFAULT_INVESTORPHOTO_CONTENT_TYPE);
        assertThat(testInvestor.getInv_f_name()).isEqualTo(DEFAULT_INV_F_NAME);
        assertThat(testInvestor.getInv_m_name()).isEqualTo(DEFAULT_INV_M_NAME);
        assertThat(testInvestor.getInv_l_name()).isEqualTo(DEFAULT_INV_L_NAME);
        assertThat(testInvestor.getInvaddress1()).isEqualTo(DEFAULT_INVADDRESS_1);
        assertThat(testInvestor.getInvaddress2()).isEqualTo(DEFAULT_INVADDRESS_2);
        assertThat(testInvestor.getInvaddress3()).isEqualTo(DEFAULT_INVADDRESS_3);
        assertThat(testInvestor.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testInvestor.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testInvestor.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testInvestor.getEmailprimary()).isEqualTo(DEFAULT_EMAILPRIMARY);
        assertThat(testInvestor.getEmailsecondary()).isEqualTo(DEFAULT_EMAILSECONDARY);
        assertThat(testInvestor.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testInvestor.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testInvestor.getCity()).isEqualTo(DEFAULT_CITY);
    }

    @Test
    public void createInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor with an existing ID
        Investor existingInvestor = new Investor();
        existingInvestor.setId(UUID.randomUUID());
        InvestorDTO existingInvestorDTO = investorMapper.investorToInvestorDTO(existingInvestor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingInvestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllInvestors() throws Exception {
        // Initialize the database
        investorRepository.save(investor);

        // Get all the investorList
        restInvestorMockMvc.perform(get("/api/investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investor.getId().toString())))
            .andExpect(jsonPath("$.[*].mouapplicable").value(hasItem(DEFAULT_MOUAPPLICABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].mousignyear").value(hasItem(DEFAULT_MOUSIGNYEAR)))
            .andExpect(jsonPath("$.[*].moudocumentContentType").value(hasItem(DEFAULT_MOUDOCUMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].moudocument").value(hasItem(Base64Utils.encodeToString(DEFAULT_MOUDOCUMENT.array()))))
            .andExpect(jsonPath("$.[*].mouidnumber").value(hasItem(DEFAULT_MOUIDNUMBER.toString())))
            .andExpect(jsonPath("$.[*].investorphotoContentType").value(hasItem(DEFAULT_INVESTORPHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].investorphoto").value(hasItem(Base64Utils.encodeToString(DEFAULT_INVESTORPHOTO.array()))))
            .andExpect(jsonPath("$.[*].inv_f_name").value(hasItem(DEFAULT_INV_F_NAME.toString())))
            .andExpect(jsonPath("$.[*].inv_m_name").value(hasItem(DEFAULT_INV_M_NAME.toString())))
            .andExpect(jsonPath("$.[*].inv_l_name").value(hasItem(DEFAULT_INV_L_NAME.toString())))
            .andExpect(jsonPath("$.[*].invaddress1").value(hasItem(DEFAULT_INVADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].invaddress2").value(hasItem(DEFAULT_INVADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].invaddress3").value(hasItem(DEFAULT_INVADDRESS_3.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.doubleValue())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.doubleValue())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.doubleValue())))
            .andExpect(jsonPath("$.[*].emailprimary").value(hasItem(DEFAULT_EMAILPRIMARY.toString())))
            .andExpect(jsonPath("$.[*].emailsecondary").value(hasItem(DEFAULT_EMAILSECONDARY.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())));
    }

    @Test
    public void getInvestor() throws Exception {
        // Initialize the database
        investorRepository.save(investor);

        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", investor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investor.getId().toString()))
            .andExpect(jsonPath("$.mouapplicable").value(DEFAULT_MOUAPPLICABLE.booleanValue()))
            .andExpect(jsonPath("$.mousignyear").value(DEFAULT_MOUSIGNYEAR))
            .andExpect(jsonPath("$.moudocumentContentType").value(DEFAULT_MOUDOCUMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.moudocument").value(Base64Utils.encodeToString(DEFAULT_MOUDOCUMENT.array())))
            .andExpect(jsonPath("$.mouidnumber").value(DEFAULT_MOUIDNUMBER.toString()))
            .andExpect(jsonPath("$.investorphotoContentType").value(DEFAULT_INVESTORPHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.investorphoto").value(Base64Utils.encodeToString(DEFAULT_INVESTORPHOTO.array())))
            .andExpect(jsonPath("$.inv_f_name").value(DEFAULT_INV_F_NAME.toString()))
            .andExpect(jsonPath("$.inv_m_name").value(DEFAULT_INV_M_NAME.toString()))
            .andExpect(jsonPath("$.inv_l_name").value(DEFAULT_INV_L_NAME.toString()))
            .andExpect(jsonPath("$.invaddress1").value(DEFAULT_INVADDRESS_1.toString()))
            .andExpect(jsonPath("$.invaddress2").value(DEFAULT_INVADDRESS_2.toString()))
            .andExpect(jsonPath("$.invaddress3").value(DEFAULT_INVADDRESS_3.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.doubleValue()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.doubleValue()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.doubleValue()))
            .andExpect(jsonPath("$.emailprimary").value(DEFAULT_EMAILPRIMARY.toString()))
            .andExpect(jsonPath("$.emailsecondary").value(DEFAULT_EMAILSECONDARY.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()));
    }

    @Test
    public void getNonExistingInvestor() throws Exception {
        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInvestor() throws Exception {
        // Initialize the database
        investorRepository.save(investor);
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Update the investor
        Investor updatedInvestor = investorRepository.findOne(investor.getId());
        updatedInvestor
                .mouapplicable(UPDATED_MOUAPPLICABLE)
                .mousignyear(UPDATED_MOUSIGNYEAR)
                .moudocument(UPDATED_MOUDOCUMENT)
                .moudocumentContentType(UPDATED_MOUDOCUMENT_CONTENT_TYPE)
                .mouidnumber(UPDATED_MOUIDNUMBER)
                .investorphoto(UPDATED_INVESTORPHOTO)
                .investorphotoContentType(UPDATED_INVESTORPHOTO_CONTENT_TYPE)
                .inv_f_name(UPDATED_INV_F_NAME)
                .inv_m_name(UPDATED_INV_M_NAME)
                .inv_l_name(UPDATED_INV_L_NAME)
                .invaddress1(UPDATED_INVADDRESS_1)
                .invaddress2(UPDATED_INVADDRESS_2)
                .invaddress3(UPDATED_INVADDRESS_3)
                .phone(UPDATED_PHONE)
                .mobile(UPDATED_MOBILE)
                .fax(UPDATED_FAX)
                .emailprimary(UPDATED_EMAILPRIMARY)
                .emailsecondary(UPDATED_EMAILSECONDARY)
                .country(UPDATED_COUNTRY)
                .state(UPDATED_STATE)
                .city(UPDATED_CITY);
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(updatedInvestor);

        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isOk());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.isMouapplicable()).isEqualTo(UPDATED_MOUAPPLICABLE);
        assertThat(testInvestor.getMousignyear()).isEqualTo(UPDATED_MOUSIGNYEAR);
        assertThat(testInvestor.getMoudocument()).isEqualTo(UPDATED_MOUDOCUMENT);
        assertThat(testInvestor.getMoudocumentContentType()).isEqualTo(UPDATED_MOUDOCUMENT_CONTENT_TYPE);
        assertThat(testInvestor.getMouidnumber()).isEqualTo(UPDATED_MOUIDNUMBER);
        assertThat(testInvestor.getInvestorphoto()).isEqualTo(UPDATED_INVESTORPHOTO);
        assertThat(testInvestor.getInvestorphotoContentType()).isEqualTo(UPDATED_INVESTORPHOTO_CONTENT_TYPE);
        assertThat(testInvestor.getInv_f_name()).isEqualTo(UPDATED_INV_F_NAME);
        assertThat(testInvestor.getInv_m_name()).isEqualTo(UPDATED_INV_M_NAME);
        assertThat(testInvestor.getInv_l_name()).isEqualTo(UPDATED_INV_L_NAME);
        assertThat(testInvestor.getInvaddress1()).isEqualTo(UPDATED_INVADDRESS_1);
        assertThat(testInvestor.getInvaddress2()).isEqualTo(UPDATED_INVADDRESS_2);
        assertThat(testInvestor.getInvaddress3()).isEqualTo(UPDATED_INVADDRESS_3);
        assertThat(testInvestor.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testInvestor.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testInvestor.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testInvestor.getEmailprimary()).isEqualTo(UPDATED_EMAILPRIMARY);
        assertThat(testInvestor.getEmailsecondary()).isEqualTo(UPDATED_EMAILSECONDARY);
        assertThat(testInvestor.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testInvestor.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testInvestor.getCity()).isEqualTo(UPDATED_CITY);
    }

    @Test
    public void updateNonExistingInvestor() throws Exception {
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.investorToInvestorDTO(investor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteInvestor() throws Exception {
        // Initialize the database
        investorRepository.save(investor);
        int databaseSizeBeforeDelete = investorRepository.findAll().size();

        // Get the investor
        restInvestorMockMvc.perform(delete("/api/investors/{id}", investor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Investor.class);
    }
}
