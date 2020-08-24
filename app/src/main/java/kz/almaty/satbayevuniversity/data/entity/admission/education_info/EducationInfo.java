package kz.almaty.satbayevuniversity.data.entity.admission.education_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.TabState;
import kz.almaty.satbayevuniversity.utils.Utils;

public class EducationInfo {
    @SerializedName("ieltsCert")
    @Expose
    private IeltsCert ieltsCert;
    @SerializedName("toeflCert")
    @Expose
    private ToeflCert toeflCert;
    @SerializedName("isSecondEducation")
    @Expose
    private Object isSecondEducation;
    @SerializedName("educationDocument")
    @Expose
    private Integer educationDocument;
    @SerializedName("studyLang")
    @Expose
    private Integer studyLang;
    @SerializedName("certificateNumber")
    @Expose
    private String certificateNumber;
    @SerializedName("certificateSeries")
    @Expose
    private Object certificateSeries;
    @SerializedName("typeOfCertificateOrDiploma")
    @Expose
    private Integer typeOfCertificateOrDiploma;
    @SerializedName("issueDateOfCertificateOrDiploma")
    @Expose
    private String issueDateOfCertificateOrDiploma;
    @SerializedName("averageScoreOfCertOrDiploma")
    @Expose
    private Double averageScoreOfCertOrDiploma;
    @SerializedName("certficationDocumentID")
    @Expose
    private Object certficationDocumentID;
    @SerializedName("certificateScan")
    @Expose
    private Object certificateScan;
    @SerializedName("fileContainerId")
    @Expose
    private Object fileContainerId;
    @SerializedName("educationInstituteType")
    @Expose
    private Integer educationInstituteType;
    @SerializedName("nameOfSchool")
    @Expose
    private NameOfSchool nameOfSchool;
    @SerializedName("isCitySettlementType")
    @Expose
    private Boolean isCitySettlementType;
    @SerializedName("cityOfSchoolLocation")
    @Expose
    private IdAndTitle cityOfSchoolLocation;
    @SerializedName("schoolDistrictName")
    @Expose
    private String schoolDistrictName;
    @SerializedName("schoolRegionName")
    @Expose
    private String schoolRegionName;
    @SerializedName("entCertificateNumber")
    @Expose
    private Object entCertificateNumber;
    @SerializedName("entCertificateDate")
    @Expose
    private Object entCertificateDate;
    @SerializedName("hasEntCertificate")
    @Expose
    private Boolean hasEntCertificate;
    @SerializedName("entCertDocumentID")
    @Expose
    private Object entCertDocumentID;
    @SerializedName("entCertificateScan")
    @Expose
    private Object entCertificateScan;
    @SerializedName("entSubjects")
    @Expose
    private List<EntSubject> entSubjects = null;
    @SerializedName("isGrantAccessible")
    @Expose
    private Boolean isGrantAccessible;
    @SerializedName("hasGrantCertificate")
    @Expose
    private Boolean hasGrantCertificate;
    @SerializedName("grantCertificateID")
    @Expose
    private Object grantCertificateID;
    @SerializedName("grantCertificateScan")
    @Expose
    private Object grantCertificateScan;
    @SerializedName("grantCertificateNumber")
    @Expose
    private Object grantCertificateNumber;
    @SerializedName("hasKtaCertificate")
    @Expose
    private Boolean hasKtaCertificate;
    @SerializedName("ktaCertificateID")
    @Expose
    private Object ktaCertificateID;
    @SerializedName("ktaCertificateScan")
    @Expose
    private Object ktaCertificateScan;
    @SerializedName("ktaSubjects")
    @Expose
    private List<Object> ktaSubjects = null;
    @SerializedName("isRelatedSpecialty")
    @Expose
    private Boolean isRelatedSpecialty;
    @SerializedName("isForeigner")
    @Expose
    private Boolean isForeigner;
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("diplomaScan")
    @Expose
    private List<Object> diplomaScan = null;
    @SerializedName("extraFileContainerId")
    @Expose
    private String extraFileContainerId;
    @SerializedName("isRuralQuota")
    @Expose
    private Object isRuralQuota;
    @SerializedName("tabState")
    @Expose
    private TabState tabState;
    @SerializedName("lastUpdatedBy")
    @Expose
    private Object lastUpdatedBy;
    @SerializedName("language")
    @Expose
    private Object language;

    public IeltsCert getIeltsCert() {
        return ieltsCert;
    }

    public void setIeltsCert(IeltsCert ieltsCert) {
        this.ieltsCert = ieltsCert;
    }

    public ToeflCert getToeflCert() {
        return toeflCert;
    }

    public void setToeflCert(ToeflCert toeflCert) {
        this.toeflCert = toeflCert;
    }

    public Object getIsSecondEducation() {
        return isSecondEducation;
    }

    public void setIsSecondEducation(Object isSecondEducation) {
        this.isSecondEducation = isSecondEducation;
    }

    public Integer getEducationDocument() {
        return educationDocument;
    }

    public void setEducationDocument(Integer educationDocument) {
        this.educationDocument = educationDocument;
    }

    public Integer getStudyLang() {
        return studyLang;
    }

    public void setStudyLang(Integer studyLang) {
        this.studyLang = studyLang;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Object getCertificateSeries() {
        return certificateSeries;
    }

    public void setCertificateSeries(Object certificateSeries) {
        this.certificateSeries = certificateSeries;
    }

    public Integer getTypeOfCertificateOrDiploma() {
        return typeOfCertificateOrDiploma;
    }

    public void setTypeOfCertificateOrDiploma(Integer typeOfCertificateOrDiploma) {
        this.typeOfCertificateOrDiploma = typeOfCertificateOrDiploma;
    }

    public String getIssueDateOfCertificateOrDiploma() {
        return Utils.parseDate(issueDateOfCertificateOrDiploma);
    }

    public void setIssueDateOfCertificateOrDiploma(String issueDateOfCertificateOrDiploma) {
        this.issueDateOfCertificateOrDiploma = issueDateOfCertificateOrDiploma;
    }

    public Double getAverageScoreOfCertOrDiploma() {
        return averageScoreOfCertOrDiploma;
    }

    public void setAverageScoreOfCertOrDiploma(Double averageScoreOfCertOrDiploma) {
        this.averageScoreOfCertOrDiploma = averageScoreOfCertOrDiploma;
    }

    public Object getCertficationDocumentID() {
        return certficationDocumentID;
    }

    public void setCertficationDocumentID(Object certficationDocumentID) {
        this.certficationDocumentID = certficationDocumentID;
    }

    public Object getCertificateScan() {
        return certificateScan;
    }

    public void setCertificateScan(Object certificateScan) {
        this.certificateScan = certificateScan;
    }

    public Object getFileContainerId() {
        return fileContainerId;
    }

    public void setFileContainerId(Object fileContainerId) {
        this.fileContainerId = fileContainerId;
    }

    public Integer getEducationInstituteType() {
        return educationInstituteType;
    }

    public void setEducationInstituteType(Integer educationInstituteType) {
        this.educationInstituteType = educationInstituteType;
    }

    public NameOfSchool getNameOfSchool() {
        return nameOfSchool;
    }

    public void setNameOfSchool(NameOfSchool nameOfSchool) {
        this.nameOfSchool = nameOfSchool;
    }

    public Boolean getIsCitySettlementType() {
        return isCitySettlementType;
    }

    public void setIsCitySettlementType(Boolean isCitySettlementType) {
        this.isCitySettlementType = isCitySettlementType;
    }

    public IdAndTitle getCityOfSchoolLocation() {
        return cityOfSchoolLocation;
    }

    public void setCityOfSchoolLocation(IdAndTitle cityOfSchoolLocation) {
        this.cityOfSchoolLocation = cityOfSchoolLocation;
    }

    public String getSchoolDistrictName() {
        return schoolDistrictName;
    }

    public void setSchoolDistrictName(String schoolDistrictName) {
        this.schoolDistrictName = schoolDistrictName;
    }

    public String getSchoolRegionName() {
        return schoolRegionName;
    }

    public void setSchoolRegionName(String schoolRegionName) {
        this.schoolRegionName = schoolRegionName;
    }

    public Object getEntCertificateNumber() {
        return entCertificateNumber;
    }

    public void setEntCertificateNumber(Object entCertificateNumber) {
        this.entCertificateNumber = entCertificateNumber;
    }

    public Object getEntCertificateDate() {
        return entCertificateDate;
    }

    public void setEntCertificateDate(Object entCertificateDate) {
        this.entCertificateDate = entCertificateDate;
    }

    public Boolean getHasEntCertificate() {
        return hasEntCertificate;
    }

    public void setHasEntCertificate(Boolean hasEntCertificate) {
        this.hasEntCertificate = hasEntCertificate;
    }

    public Object getEntCertDocumentID() {
        return entCertDocumentID;
    }

    public void setEntCertDocumentID(Object entCertDocumentID) {
        this.entCertDocumentID = entCertDocumentID;
    }

    public Object getEntCertificateScan() {
        return entCertificateScan;
    }

    public void setEntCertificateScan(Object entCertificateScan) {
        this.entCertificateScan = entCertificateScan;
    }

    public List<EntSubject> getEntSubjects() {
        return entSubjects;
    }

    public void setEntSubjects(List<EntSubject> entSubjects) {
        this.entSubjects = entSubjects;
    }

    public Boolean getIsGrantAccessible() {
        return isGrantAccessible;
    }

    public void setIsGrantAccessible(Boolean isGrantAccessible) {
        this.isGrantAccessible = isGrantAccessible;
    }

    public Boolean getHasGrantCertificate() {
        return hasGrantCertificate;
    }

    public void setHasGrantCertificate(Boolean hasGrantCertificate) {
        this.hasGrantCertificate = hasGrantCertificate;
    }

    public Object getGrantCertificateID() {
        return grantCertificateID;
    }

    public void setGrantCertificateID(Object grantCertificateID) {
        this.grantCertificateID = grantCertificateID;
    }

    public Object getGrantCertificateScan() {
        return grantCertificateScan;
    }

    public void setGrantCertificateScan(Object grantCertificateScan) {
        this.grantCertificateScan = grantCertificateScan;
    }

    public Object getGrantCertificateNumber() {
        return grantCertificateNumber;
    }

    public void setGrantCertificateNumber(Object grantCertificateNumber) {
        this.grantCertificateNumber = grantCertificateNumber;
    }

    public Boolean getHasKtaCertificate() {
        return hasKtaCertificate;
    }

    public void setHasKtaCertificate(Boolean hasKtaCertificate) {
        this.hasKtaCertificate = hasKtaCertificate;
    }

    public Object getKtaCertificateID() {
        return ktaCertificateID;
    }

    public void setKtaCertificateID(Object ktaCertificateID) {
        this.ktaCertificateID = ktaCertificateID;
    }

    public Object getKtaCertificateScan() {
        return ktaCertificateScan;
    }

    public void setKtaCertificateScan(Object ktaCertificateScan) {
        this.ktaCertificateScan = ktaCertificateScan;
    }

    public List<Object> getKtaSubjects() {
        return ktaSubjects;
    }

    public void setKtaSubjects(List<Object> ktaSubjects) {
        this.ktaSubjects = ktaSubjects;
    }

    public Boolean getIsRelatedSpecialty() {
        return isRelatedSpecialty;
    }

    public void setIsRelatedSpecialty(Boolean isRelatedSpecialty) {
        this.isRelatedSpecialty = isRelatedSpecialty;
    }

    public Boolean getIsForeigner() {
        return isForeigner;
    }

    public void setIsForeigner(Boolean isForeigner) {
        this.isForeigner = isForeigner;
    }

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public List<Object> getDiplomaScan() {
        return diplomaScan;
    }

    public void setDiplomaScan(List<Object> diplomaScan) {
        this.diplomaScan = diplomaScan;
    }

    public String getExtraFileContainerId() {
        return extraFileContainerId;
    }

    public void setExtraFileContainerId(String extraFileContainerId) {
        this.extraFileContainerId = extraFileContainerId;
    }

    public Object getIsRuralQuota() {
        return isRuralQuota;
    }

    public void setIsRuralQuota(Object isRuralQuota) {
        this.isRuralQuota = isRuralQuota;
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
