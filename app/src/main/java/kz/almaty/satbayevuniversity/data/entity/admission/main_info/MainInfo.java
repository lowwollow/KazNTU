package kz.almaty.satbayevuniversity.data.entity.admission.main_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.data.entity.admission.TabState;
import kz.almaty.satbayevuniversity.utils.Utils;
import okhttp3.MultipartBody;

public class MainInfo {
    @SerializedName("citizenCategory")
    @Expose
    private Integer citizenCategory;
    @SerializedName("citizenship")
    @Expose
    private Integer citizenship;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("secondName")
    @Expose
    private String secondName;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("iin")
    @Expose
    private String iin;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;
    @SerializedName("cellphone")
    @Expose
    private String cellphone;
    @SerializedName("messenger")
    @Expose
    private Object messenger;
    @SerializedName("documentType")
    @Expose
    private Integer documentType;
    @SerializedName("documentIssueOrganization")
    @Expose
    private Integer documentIssueOrganization;
    @SerializedName("documentIssueOrganizationForeigner")
    @Expose
    private Object documentIssueOrganizationForeigner;
    @SerializedName("idCardPhotos")
    @Expose
    private List<IdCardPhoto> idCardPhotos = null;
    @SerializedName("photoFileId")
    @Expose
    private Integer photoFileId;
    @SerializedName("photo")
    @Expose
    private MultipartBody.Part photo;
    @SerializedName("documentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("documentReceiveDate")
    @Expose
    private String documentReceiveDate;
    @SerializedName("birthPlace")
    @Expose
    private String birthPlace;
    @SerializedName("nationality")
    @Expose
    private Integer nationality;
    @SerializedName("maritalStatus")
    @Expose
    private Integer maritalStatus;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("emailVerificationCode")
    @Expose
    private String emailVerificationCode;
    @SerializedName("personalEmail")
    @Expose
    private String personalEmail;
    @SerializedName("male")
    @Expose
    private Boolean male;
    @SerializedName("tabState")
    @Expose
    private TabState tabState;
    @SerializedName("hasPhoto")
    @Expose
    private Boolean hasPhoto;
    @SerializedName("levelID")
    @Expose
    private Integer levelID;
    @SerializedName("lastUpdatedBy")
    @Expose
    private Object lastUpdatedBy;
    @SerializedName("hearAboutId")
    @Expose
    private Integer hearAboutId;
    @SerializedName("hearAboutText")
    @Expose
    private String hearAboutText;
    @SerializedName("choose")
    @Expose
    private String choose;
    @SerializedName("needInAccomodation")
    @Expose
    private Boolean needInAccomodation;
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("surnameKZ")
    @Expose
    private String surnameKZ;
    @SerializedName("nameKZ")
    @Expose
    private String nameKZ;
    @SerializedName("secondNameKZ")
    @Expose
    private String secondNameKZ;
    @SerializedName("surnameEN")
    @Expose
    private String surnameEN;
    @SerializedName("nameEN")
    @Expose
    private String nameEN;
    @SerializedName("secondNameEN")
    @Expose
    private String secondNameEN;


    public Integer getCitizenCategory() {
        return citizenCategory;
    }

    public void setCitizenCategory(Integer citizenCategory) {
        this.citizenCategory = citizenCategory;
    }

    public Integer getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Integer citizenship) {
        this.citizenship = citizenship;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBirthDate() {
        if(birthDate!=null){
           birthDate =  Utils.parseDate(birthDate);
        }
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public Object getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Object getMessenger() {
        return messenger;
    }

    public void setMessenger(Object messenger) {
        this.messenger = messenger;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentIssueOrganization() {
        return documentIssueOrganization;
    }

    public void setDocumentIssueOrganization(Integer documentIssueOrganization) {
        this.documentIssueOrganization = documentIssueOrganization;
    }

    public Object getDocumentIssueOrganizationForeigner() {
        return documentIssueOrganizationForeigner;
    }

    public void setDocumentIssueOrganizationForeigner(Object documentIssueOrganizationForeigner) {
        this.documentIssueOrganizationForeigner = documentIssueOrganizationForeigner;
    }

    public List<IdCardPhoto> getIdCardPhotos() {
        return idCardPhotos;
    }

    public void setIdCardPhotos(List<IdCardPhoto> idCardPhotos) {
        this.idCardPhotos = idCardPhotos;
    }

    public Integer getPhotoFileId() {
        return photoFileId;
    }

    public void setPhotoFileId(Integer photoFileId) {
        this.photoFileId = photoFileId;
    }

    public MultipartBody.Part getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartBody.Part photo) {
        this.photo = photo;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentReceiveDate()  {
        if(documentReceiveDate !=null){
            documentReceiveDate = Utils.parseDate(documentReceiveDate);
        }
        return documentReceiveDate;
    }

    public void setDocumentReceiveDate(String documentReceiveDate) {
        this.documentReceiveDate = documentReceiveDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public TabState getTabState() {
        return tabState;
    }

    public void setTabState(TabState tabState) {
        this.tabState = tabState;
    }

    public Boolean getHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(Boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    public Object getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Object lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getHearAboutId() {
        return hearAboutId;
    }

    public void setHearAboutId(Integer hearAboutId) {
        this.hearAboutId = hearAboutId;
    }

    public String getHearAboutText() {
        return hearAboutText;
    }

    public void setHearAboutText(String hearAboutText) {
        this.hearAboutText = hearAboutText;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public Boolean getNeedInAccomodation() {
        return needInAccomodation;
    }

    public void setNeedInAccomodation(Boolean needInAccomodation) {
        this.needInAccomodation = needInAccomodation;
    }

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getSurnameKZ() {
        return surnameKZ;
    }

    public void setSurnameKZ(String surnameKZ) {
        this.surnameKZ = surnameKZ;
    }

    public Object getNameKZ() {
        return nameKZ;
    }

    public void setNameKZ(String nameKZ) {
        this.nameKZ = nameKZ;
    }

    public Object getSecondNameKZ() {
        return secondNameKZ;
    }

    public void setSecondNameKZ(String secondNameKZ) {
        this.secondNameKZ = secondNameKZ;
    }

    public String getSurnameEN() {
        return surnameEN;
    }

    public void setSurnameEN(String surnameEN) {
        this.surnameEN = surnameEN;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getSecondNameEN() {
        return secondNameEN;
    }

    public void setSecondNameEN(String secondNameEN) {
        this.secondNameEN = secondNameEN;
    }
}
