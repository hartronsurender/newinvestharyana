package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Investor entity.
 */
public class InvestorDTO implements Serializable {

    private UUID id;

    private Boolean mouapplicable;

    private Integer mousignyear;

    private ByteBuffer moudocument;
    private String moudocumentContentType;

    private String mouidnumber;

    private ByteBuffer investorphoto;
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
    public Boolean getMouapplicable() {
        return mouapplicable;
    }

    public void setMouapplicable(Boolean mouapplicable) {
        this.mouapplicable = mouapplicable;
    }
    public Integer getMousignyear() {
        return mousignyear;
    }

    public void setMousignyear(Integer mousignyear) {
        this.mousignyear = mousignyear;
    }
    public ByteBuffer getMoudocument() {
        return moudocument;
    }

    public void setMoudocument(ByteBuffer moudocument) {
        this.moudocument = moudocument;
    }

    public String getMoudocumentContentType() {
        return moudocumentContentType;
    }

    public void setMoudocumentContentType(String moudocumentContentType) {
        this.moudocumentContentType = moudocumentContentType;
    }
    public String getMouidnumber() {
        return mouidnumber;
    }

    public void setMouidnumber(String mouidnumber) {
        this.mouidnumber = mouidnumber;
    }
    public ByteBuffer getInvestorphoto() {
        return investorphoto;
    }

    public void setInvestorphoto(ByteBuffer investorphoto) {
        this.investorphoto = investorphoto;
    }

    public String getInvestorphotoContentType() {
        return investorphotoContentType;
    }

    public void setInvestorphotoContentType(String investorphotoContentType) {
        this.investorphotoContentType = investorphotoContentType;
    }
    public String getInv_f_name() {
        return inv_f_name;
    }

    public void setInv_f_name(String inv_f_name) {
        this.inv_f_name = inv_f_name;
    }
    public String getInv_m_name() {
        return inv_m_name;
    }

    public void setInv_m_name(String inv_m_name) {
        this.inv_m_name = inv_m_name;
    }
    public String getInv_l_name() {
        return inv_l_name;
    }

    public void setInv_l_name(String inv_l_name) {
        this.inv_l_name = inv_l_name;
    }
    public String getInvaddress1() {
        return invaddress1;
    }

    public void setInvaddress1(String invaddress1) {
        this.invaddress1 = invaddress1;
    }
    public String getInvaddress2() {
        return invaddress2;
    }

    public void setInvaddress2(String invaddress2) {
        this.invaddress2 = invaddress2;
    }
    public String getInvaddress3() {
        return invaddress3;
    }

    public void setInvaddress3(String invaddress3) {
        this.invaddress3 = invaddress3;
    }
    public Double getPhone() {
        return phone;
    }

    public void setPhone(Double phone) {
        this.phone = phone;
    }
    public Double getMobile() {
        return mobile;
    }

    public void setMobile(Double mobile) {
        this.mobile = mobile;
    }
    public Double getFax() {
        return fax;
    }

    public void setFax(Double fax) {
        this.fax = fax;
    }
    public String getEmailprimary() {
        return emailprimary;
    }

    public void setEmailprimary(String emailprimary) {
        this.emailprimary = emailprimary;
    }
    public String getEmailsecondary() {
        return emailsecondary;
    }

    public void setEmailsecondary(String emailsecondary) {
        this.emailsecondary = emailsecondary;
    }
    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }
    public UUID getState() {
        return state;
    }

    public void setState(UUID state) {
        this.state = state;
    }
    public UUID getCity() {
        return city;
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

        InvestorDTO investorDTO = (InvestorDTO) o;

        if ( ! Objects.equals(id, investorDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InvestorDTO{" +
            "id=" + id +
            ", mouapplicable='" + mouapplicable + "'" +
            ", mousignyear='" + mousignyear + "'" +
            ", moudocument='" + moudocument + "'" +
            ", mouidnumber='" + mouidnumber + "'" +
            ", investorphoto='" + investorphoto + "'" +
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
