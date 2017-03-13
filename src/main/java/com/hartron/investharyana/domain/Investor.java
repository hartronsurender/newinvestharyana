package com.hartron.investharyana.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A Investor.
 */

@Table(name = "investor")
public class Investor implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private UUID id;

    private Boolean mouapplicable;

    private Integer mousignyear;

    private ByteBuffer moudocument;

    @Column(name = "moudocument_content_type")
    private String moudocumentContentType;

    private String mouidnumber;

    private ByteBuffer investorphoto;

    @Column(name = "investorphoto_content_type")
    private String investorphotoContentType;

    private String inv_f_name;

    private String inv_m_name;

    private String inv_l_name;

    private String invaddress1;

    private String invaddress2;

    private String invaddress3;

    private Double phone;

    private Double mobile;

    private Double fax;

    private String emailprimary;

    private String emailsecondary;

    private UUID country;

    private UUID state;

    private UUID city;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean isMouapplicable() {
        return mouapplicable;
    }

    public Investor mouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
        return this;
    }

    public void setMouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
    }

    public Integer getMousignyear() {
        return mousignyear;
    }

    public Investor mousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
        return this;
    }

    public void setMousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
    }

    public ByteBuffer getMoudocument() {
        return moudocument;
    }

    public Investor moudocument(ByteBuffer moudocument) {
        this.moudocument = moudocument;
        return this;
    }

    public void setMoudocument(ByteBuffer moudocument) {
        this.moudocument = moudocument;
    }

    public String getMoudocumentContentType() {
        return moudocumentContentType;
    }

    public Investor moudocumentContentType(String moudocumentContentType) {
        this.moudocumentContentType = moudocumentContentType;
        return this;
    }

    public void setMoudocumentContentType(String moudocumentContentType) {
        this.moudocumentContentType = moudocumentContentType;
    }

    public String getMouidnumber() {
        return mouidnumber;
    }

    public Investor mouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
        return this;
    }

    public void setMouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
    }

    public ByteBuffer getInvestorphoto() {
        return investorphoto;
    }

    public Investor investorphoto(ByteBuffer investorphoto) {
        this.investorphoto = investorphoto;
        return this;
    }

    public void setInvestorphoto(ByteBuffer investorphoto) {
        this.investorphoto = investorphoto;
    }

    public String getInvestorphotoContentType() {
        return investorphotoContentType;
    }

    public Investor investorphotoContentType(String investorphotoContentType) {
        this.investorphotoContentType = investorphotoContentType;
        return this;
    }

    public void setInvestorphotoContentType(String investorphotoContentType) {
        this.investorphotoContentType = investorphotoContentType;
    }

    public String getInv_f_name() {
        return inv_f_name;
    }

    public Investor inv_f_name(String inv_f_name) {
        this.inv_f_name = inv_f_name;
        return this;
    }

    public void setInv_f_name(String inv_f_name) {
        this.inv_f_name = inv_f_name;
    }

    public String getInv_m_name() {
        return inv_m_name;
    }

    public Investor inv_m_name(String inv_m_name) {
        this.inv_m_name = inv_m_name;
        return this;
    }

    public void setInv_m_name(String inv_m_name) {
        this.inv_m_name = inv_m_name;
    }

    public String getInv_l_name() {
        return inv_l_name;
    }

    public Investor inv_l_name(String inv_l_name) {
        this.inv_l_name = inv_l_name;
        return this;
    }

    public void setInv_l_name(String inv_l_name) {
        this.inv_l_name = inv_l_name;
    }

    public String getInvaddress1() {
        return invaddress1;
    }

    public Investor invaddress1(String invaddress1) {
        this.invaddress1 = invaddress1;
        return this;
    }

    public void setInvaddress1(String invaddress1) {
        this.invaddress1 = invaddress1;
    }

    public String getInvaddress2() {
        return invaddress2;
    }

    public Investor invaddress2(String invaddress2) {
        this.invaddress2 = invaddress2;
        return this;
    }

    public void setInvaddress2(String invaddress2) {
        this.invaddress2 = invaddress2;
    }

    public String getInvaddress3() {
        return invaddress3;
    }

    public Investor invaddress3(String invaddress3) {
        this.invaddress3 = invaddress3;
        return this;
    }

    public void setInvaddress3(String invaddress3) {
        this.invaddress3 = invaddress3;
    }

    public Double getPhone() {
        return phone;
    }

    public Investor phone(Double phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(Double phone) {
        this.phone = phone;
    }

    public Double getMobile() {
        return mobile;
    }

    public Investor mobile(Double mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(Double mobile) {
        this.mobile = mobile;
    }

    public Double getFax() {
        return fax;
    }

    public Investor fax(Double fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(Double fax) {
        this.fax = fax;
    }

    public String getEmailprimary() {
        return emailprimary;
    }

    public Investor emailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
        return this;
    }

    public void setEmailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
    }

    public String getEmailsecondary() {
        return emailsecondary;
    }

    public Investor emailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
        return this;
    }

    public void setEmailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
    }

    public UUID getCountry() {
        return country;
    }

    public Investor country(UUID country) {
        this.country = country;
        return this;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public UUID getState() {
        return state;
    }

    public Investor state(UUID state) {
        this.state = state;
        return this;
    }

    public void setState(UUID state) {
        this.state = state;
    }

    public UUID getCity() {
        return city;
    }

    public Investor city(UUID city) {
        this.city = city;
        return this;
    }

    public void setCity(UUID city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Investor investor = (Investor) o;
        if (investor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, investor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Investor{" +
            "id=" + id +
            ", mouapplicable='" + mouapplicable + "'" +
            ", mousignyear='" + mousignyear + "'" +
            ", moudocument='" + moudocument + "'" +
            ", moudocumentContentType='" + moudocumentContentType + "'" +
            ", mouidnumber='" + mouidnumber + "'" +
            ", investorphoto='" + investorphoto + "'" +
            ", investorphotoContentType='" + investorphotoContentType + "'" +
            ", inv_f_name='" + inv_f_name + "'" +
            ", inv_m_name='" + inv_m_name + "'" +
            ", inv_l_name='" + inv_l_name + "'" +
            ", invaddress1='" + invaddress1 + "'" +
            ", invaddress2='" + invaddress2 + "'" +
            ", invaddress3='" + invaddress3 + "'" +
            ", phone='" + phone + "'" +
            ", mobile='" + mobile + "'" +
            ", fax='" + fax + "'" +
            ", emailprimary='" + emailprimary + "'" +
            ", emailsecondary='" + emailsecondary + "'" +
            ", country='" + country + "'" +
            ", state='" + state + "'" +
            ", city='" + city + "'" +
            '}';
    }
}
