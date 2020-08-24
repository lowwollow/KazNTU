package kz.almaty.satbayevuniversity.data.entity.admission.residence_info;


import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.TabState;

public class ResidenceInfo {
    @SerializedName("countryId")
    private Integer countryId;
    @SerializedName("cityOrVillagePermanent")
    private IdAndTitle cityOrVillagePermanent;
    @SerializedName("region")
    private IdAndTitle region;
    @SerializedName("area")
    private IdAndTitle area;
    @SerializedName("addressPermanent")
    private String addressPermanent;
    @SerializedName("districtInAlmatyId")
    private Integer districtInAlmatyId;
    @SerializedName("addressInAlmaty")
    private String addressInAlmaty;
    @SerializedName("entrantId")
    private Integer entrantId;
    @SerializedName("addressPaperDocumentID")
    private Object addressPaperDocumentID;
    @SerializedName("addressPaperScan")
    private Object addressPaperScan;
    @SerializedName("addressDocUploadTime")
    private Object addressDocUploadTime;
    @SerializedName("needInAccomodation")
    private Boolean needInAccomodation;
    @SerializedName("hosterPrivelegeID")
    private Integer hosterPrivelegeID;
    @SerializedName("tabState")
    private TabState tabState;
    @SerializedName("lastUpdatedBy")
    private Object lastUpdatedBy;
    @SerializedName("language")
    private Object language;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public IdAndTitle getCityOrVillagePermanent() {
        return cityOrVillagePermanent;
    }

    public void setCityOrVillagePermanent(IdAndTitle cityOrVillagePermanent) {
        this.cityOrVillagePermanent = cityOrVillagePermanent;
    }

    public IdAndTitle getRegion() {
        return region;
    }

    public void setRegion(IdAndTitle region) {
        this.region = region;
    }

    public IdAndTitle getArea() {
        return area;
    }

    public void setArea(IdAndTitle area) {
        this.area = area;
    }

    public String getAddressPermanent() {
        return addressPermanent;
    }

    public void setAddressPermanent(String addressPermanent) {
        this.addressPermanent = addressPermanent;
    }

    public Integer getDistrictInAlmatyId() {
        return districtInAlmatyId;
    }

    public void setDistrictInAlmatyId(Integer districtInAlmatyId) {
        this.districtInAlmatyId = districtInAlmatyId;
    }

    public String getAddressInAlmaty() {
        return addressInAlmaty;
    }

    public void setAddressInAlmaty(String addressInAlmaty) {
        this.addressInAlmaty = addressInAlmaty;
    }

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public Object getAddressPaperDocumentID() {
        return addressPaperDocumentID;
    }

    public void setAddressPaperDocumentID(Object addressPaperDocumentID) {
        this.addressPaperDocumentID = addressPaperDocumentID;
    }

    public Object getAddressPaperScan() {
        return addressPaperScan;
    }

    public void setAddressPaperScan(Object addressPaperScan) {
        this.addressPaperScan = addressPaperScan;
    }

    public Object getAddressDocUploadTime() {
        return addressDocUploadTime;
    }

    public void setAddressDocUploadTime(Object addressDocUploadTime) {
        this.addressDocUploadTime = addressDocUploadTime;
    }

    public Boolean getNeedInAccomodation() {
        return needInAccomodation;
    }

    public void setNeedInAccomodation(Boolean needInAccomodation) {
        this.needInAccomodation = needInAccomodation;
    }

    public Integer getHosterPrivelegeID() {
        return hosterPrivelegeID;
    }

    public void setHosterPrivelegeID(Integer hosterPrivelegeID) {
        this.hosterPrivelegeID = hosterPrivelegeID;
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
