package com.hartron.investharyana.service.dto;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Companydetail entity.
 */
public class CompanydetailDTO implements Serializable {

    private UUID id;

    private UUID investorid;

    private String promoter_md_director;

    private String designation;

    private String businessentity;

    private UUID businesstype;

    private Integer number_of_director_mds_ceo;

    private ByteBuffer list_director_md_ceo;
    private String list_director_md_ceoContentType;

    private String pannumber;

    private ByteBuffer pancard;
    private String pancardContentType;

    private Double aadharnumber;

    private ByteBuffer aadharcard;
    private String aadharcardContentType;

    private Boolean nri;

    private String tin_vat_no;

    private ByteBuffer tin_vat_document;
    private String tin_vat_documentContentType;

    private String cstno;

    private ByteBuffer cstdocument;
    private String cstdocumentContentType;

    private ByteBuffer moa_partnershipdeed_document;
    private String moa_partnershipdeed_documentContentType;

    private ByteBuffer registration_document;
    private String registration_documentContentType;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getInvestorid() {
        return investorid;
    }

    public void setInvestorid(UUID investorid) {
        this.investorid = investorid;
    }
    public String getPromoter_md_director() {
        return promoter_md_director;
    }

    public void setPromoter_md_director(String promoter_md_director) {
        this.promoter_md_director = promoter_md_director;
    }
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getBusinessentity() {
        return businessentity;
    }

    public void setBusinessentity(String businessentity) {
        this.businessentity = businessentity;
    }
    public UUID getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(UUID businesstype) {
        this.businesstype = businesstype;
    }
    public Integer getNumber_of_director_mds_ceo() {
        return number_of_director_mds_ceo;
    }

    public void setNumber_of_director_mds_ceo(Integer number_of_director_mds_ceo) {
        this.number_of_director_mds_ceo = number_of_director_mds_ceo;
    }
    public ByteBuffer getList_director_md_ceo() {
        return list_director_md_ceo;
    }

    public void setList_director_md_ceo(ByteBuffer list_director_md_ceo) {
        this.list_director_md_ceo = list_director_md_ceo;
    }

    public String getList_director_md_ceoContentType() {
        return list_director_md_ceoContentType;
    }

    public void setList_director_md_ceoContentType(String list_director_md_ceoContentType) {
        this.list_director_md_ceoContentType = list_director_md_ceoContentType;
    }
    public String getPannumber() {
        return pannumber;
    }

    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
    }
    public ByteBuffer getPancard() {
        return pancard;
    }

    public void setPancard(ByteBuffer pancard) {
        this.pancard = pancard;
    }

    public String getPancardContentType() {
        return pancardContentType;
    }

    public void setPancardContentType(String pancardContentType) {
        this.pancardContentType = pancardContentType;
    }
    public Double getAadharnumber() {
        return aadharnumber;
    }

    public void setAadharnumber(Double aadharnumber) {
        this.aadharnumber = aadharnumber;
    }
    public ByteBuffer getAadharcard() {
        return aadharcard;
    }

    public void setAadharcard(ByteBuffer aadharcard) {
        this.aadharcard = aadharcard;
    }

    public String getAadharcardContentType() {
        return aadharcardContentType;
    }

    public void setAadharcardContentType(String aadharcardContentType) {
        this.aadharcardContentType = aadharcardContentType;
    }
    public Boolean getNri() {
        return nri;
    }

    public void setNri(Boolean nri) {
        this.nri = nri;
    }
    public String getTin_vat_no() {
        return tin_vat_no;
    }

    public void setTin_vat_no(String tin_vat_no) {
        this.tin_vat_no = tin_vat_no;
    }
    public ByteBuffer getTin_vat_document() {
        return tin_vat_document;
    }

    public void setTin_vat_document(ByteBuffer tin_vat_document) {
        this.tin_vat_document = tin_vat_document;
    }

    public String getTin_vat_documentContentType() {
        return tin_vat_documentContentType;
    }

    public void setTin_vat_documentContentType(String tin_vat_documentContentType) {
        this.tin_vat_documentContentType = tin_vat_documentContentType;
    }
    public String getCstno() {
        return cstno;
    }

    public void setCstno(String cstno) {
        this.cstno = cstno;
    }
    public ByteBuffer getCstdocument() {
        return cstdocument;
    }

    public void setCstdocument(ByteBuffer cstdocument) {
        this.cstdocument = cstdocument;
    }

    public String getCstdocumentContentType() {
        return cstdocumentContentType;
    }

    public void setCstdocumentContentType(String cstdocumentContentType) {
        this.cstdocumentContentType = cstdocumentContentType;
    }
    public ByteBuffer getMoa_partnershipdeed_document() {
        return moa_partnershipdeed_document;
    }

    public void setMoa_partnershipdeed_document(ByteBuffer moa_partnershipdeed_document) {
        this.moa_partnershipdeed_document = moa_partnershipdeed_document;
    }

    public String getMoa_partnershipdeed_documentContentType() {
        return moa_partnershipdeed_documentContentType;
    }

    public void setMoa_partnershipdeed_documentContentType(String moa_partnershipdeed_documentContentType) {
        this.moa_partnershipdeed_documentContentType = moa_partnershipdeed_documentContentType;
    }
    public ByteBuffer getRegistration_document() {
        return registration_document;
    }

    public void setRegistration_document(ByteBuffer registration_document) {
        this.registration_document = registration_document;
    }

    public String getRegistration_documentContentType() {
        return registration_documentContentType;
    }

    public void setRegistration_documentContentType(String registration_documentContentType) {
        this.registration_documentContentType = registration_documentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanydetailDTO companydetailDTO = (CompanydetailDTO) o;

        if ( ! Objects.equals(id, companydetailDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompanydetailDTO{" +
            "id=" + id +
            ", investorid='" + investorid + "'" +
            ", promoter_md_director='" + promoter_md_director + "'" +
            ", designation='" + designation + "'" +
            ", businessentity='" + businessentity + "'" +
            ", businesstype='" + businesstype + "'" +
            ", number_of_director_mds_ceo='" + number_of_director_mds_ceo + "'" +
            ", list_director_md_ceo='" + list_director_md_ceo + "'" +
            ", pannumber='" + pannumber + "'" +
            ", pancard='" + pancard + "'" +
            ", aadharnumber='" + aadharnumber + "'" +
            ", aadharcard='" + aadharcard + "'" +
            ", nri='" + nri + "'" +
            ", tin_vat_no='" + tin_vat_no + "'" +
            ", tin_vat_document='" + tin_vat_document + "'" +
            ", cstno='" + cstno + "'" +
            ", cstdocument='" + cstdocument + "'" +
            ", moa_partnershipdeed_document='" + moa_partnershipdeed_document + "'" +
            ", registration_document='" + registration_document + "'" +
            '}';
    }
}
