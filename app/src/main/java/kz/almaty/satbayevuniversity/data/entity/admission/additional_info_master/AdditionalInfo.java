package kz.almaty.satbayevuniversity.data.entity.admission.additional_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kz.almaty.satbayevuniversity.data.entity.admission.TabState;

public class AdditionalInfo {
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("passportDocumentID")
    @Expose
    private Integer passportDocumentID;
    @SerializedName("passportScan")
    @Expose
    private Object passportScan;
    @SerializedName("diplomaDocumentID")
    @Expose
    private Integer diplomaDocumentID;
    @SerializedName("diplomaScan")
    @Expose
    private Object diplomaScan;
    @SerializedName("notarizedTranslationOfPassportOrDiploma")
    @Expose
    private Boolean notarizedTranslationOfPassportOrDiploma;
    @SerializedName("nostrificationDocumentID")
    @Expose
    private Integer nostrificationDocumentID;
    @SerializedName("submittedNostrification")
    @Expose
    private Boolean submittedNostrification;
    @SerializedName("nostrificationScan")
    @Expose
    private Object nostrificationScan;
    @SerializedName("healthInfoId")
    @Expose
    private Integer healthInfoId;
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

    public Integer getPassportDocumentID() {
        return passportDocumentID;
    }

    public void setPassportDocumentID(Integer passportDocumentID) {
        this.passportDocumentID = passportDocumentID;
    }

    public Object getPassportScan() {
        return passportScan;
    }

    public void setPassportScan(Object passportScan) {
        this.passportScan = passportScan;
    }

    public Integer getDiplomaDocumentID() {
        return diplomaDocumentID;
    }

    public void setDiplomaDocumentID(Integer diplomaDocumentID) {
        this.diplomaDocumentID = diplomaDocumentID;
    }

    public Object getDiplomaScan() {
        return diplomaScan;
    }

    public void setDiplomaScan(Object diplomaScan) {
        this.diplomaScan = diplomaScan;
    }

    public Boolean getNotarizedTranslationOfPassportOrDiploma() {
        return notarizedTranslationOfPassportOrDiploma;
    }

    public void setNotarizedTranslationOfPassportOrDiploma(Boolean notarizedTranslationOfPassportOrDiploma) {
        this.notarizedTranslationOfPassportOrDiploma = notarizedTranslationOfPassportOrDiploma;
    }

    public Integer getNostrificationDocumentID() {
        return nostrificationDocumentID;
    }

    public void setNostrificationDocumentID(Integer nostrificationDocumentID) {
        this.nostrificationDocumentID = nostrificationDocumentID;
    }

    public Boolean getSubmittedNostrification() {
        return submittedNostrification;
    }

    public void setSubmittedNostrification(Boolean submittedNostrification) {
        this.submittedNostrification = submittedNostrification;
    }

    public Object getNostrificationScan() {
        return nostrificationScan;
    }

    public void setNostrificationScan(Object nostrificationScan) {
        this.nostrificationScan = nostrificationScan;
    }

    public Integer getHealthInfoId() {
        return healthInfoId;
    }

    public void setHealthInfoId(Integer healthInfoId) {
        this.healthInfoId = healthInfoId;
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
