package kz.almaty.satbayevuniversity.data.entity.admission.choosing_education_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kz.almaty.satbayevuniversity.data.entity.admission.TabState;

public class SpecialityChoise {
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("isCorporateStudent")
    @Expose
    private Boolean isCorporateStudent;
    @SerializedName("specialityId")
    @Expose
    private Integer specialityId;
    @SerializedName("groupEducationalPrograms")
    @Expose
    private Integer groupEducationalPrograms;
    @SerializedName("specialisationId")
    @Expose
    private Object specialisationId;
    @SerializedName("masterProgramTypeId")
    @Expose
    private Integer masterProgramTypeId;
    @SerializedName("paymentTypeId")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("grantTypeId")
    @Expose
    private Integer grantTypeId;
    @SerializedName("studyLanguageId")
    @Expose
    private Integer studyLanguageId;
    @SerializedName("isEnglishAvailable")
    @Expose
    private Boolean isEnglishAvailable;
    @SerializedName("garantMessageId")
    @Expose
    private Integer garantMessageId;
    @SerializedName("garantMessageFile")
    @Expose
    private Object garantMessageFile;
    @SerializedName("tabState")
    @Expose
    private TabState tabState;
    @SerializedName("lastUpdatedBy")
    @Expose
    private Object lastUpdatedBy;
    @SerializedName("language")
    @Expose
    private Object language;

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public Boolean getIsCorporateStudent() {
        return isCorporateStudent;
    }

    public void setIsCorporateStudent(Boolean isCorporateStudent) {
        this.isCorporateStudent = isCorporateStudent;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public Integer getGroupEducationalPrograms() {
        return groupEducationalPrograms;
    }

    public void setGroupEducationalPrograms(Integer groupEducationalPrograms) {
        this.groupEducationalPrograms = groupEducationalPrograms;
    }

    public Object getSpecialisationId() {
        return specialisationId;
    }

    public void setSpecialisationId(Object specialisationId) {
        this.specialisationId = specialisationId;
    }

    public Integer getMasterProgramTypeId() {
        return masterProgramTypeId;
    }

    public void setMasterProgramTypeId(Integer masterProgramTypeId) {
        this.masterProgramTypeId = masterProgramTypeId;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getGrantTypeId() {
        return grantTypeId;
    }

    public void setGrantTypeId(Integer grantTypeId) {
        this.grantTypeId = grantTypeId;
    }

    public Integer getStudyLanguageId() {
        return studyLanguageId;
    }

    public void setStudyLanguageId(Integer studyLanguageId) {
        this.studyLanguageId = studyLanguageId;
    }

    public Boolean getIsEnglishAvailable() {
        return isEnglishAvailable;
    }

    public void setIsEnglishAvailable(Boolean isEnglishAvailable) {
        this.isEnglishAvailable = isEnglishAvailable;
    }

    public Integer getGarantMessageId() {
        return garantMessageId;
    }

    public void setGarantMessageId(Integer garantMessageId) {
        this.garantMessageId = garantMessageId;
    }

    public Object getGarantMessageFile() {
        return garantMessageFile;
    }

    public void setGarantMessageFile(Object garantMessageFile) {
        this.garantMessageFile = garantMessageFile;
    }

    public TabState getTabState() {
        return tabState;
    }

    public void setTabState(TabState tabState) {
        this.tabState = tabState;
    }

    public Object getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Object lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

}
