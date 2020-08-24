package kz.almaty.satbayevuniversity.data.entity.admission.medical_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kz.almaty.satbayevuniversity.utils.Utils;

public class MedicalInfo {
    @SerializedName("entrantId")
    @Expose
    private Integer entrantId;
    @SerializedName("fluorographyDate")
    @Expose
    private String fluorographyDate;
    @SerializedName("isEligible")
    @Expose
    private Boolean isEligible;
    @SerializedName("rejectionReason")
    @Expose
    private String rejectionReason;
    @SerializedName("isMedicalOperator")
    @Expose
    private Boolean isMedicalOperator;

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }

    public String getFluorographyDate() {
        if(fluorographyDate !=null){
            fluorographyDate = Utils.parseDate(fluorographyDate);
        }
        return fluorographyDate;
    }

    public void setFluorographyDate(String fluorographyDate) {
        this.fluorographyDate = fluorographyDate;
    }

    public Boolean getIsEligible() {
        return isEligible;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Object getIsMedicalOperator() {
        return isMedicalOperator;
    }

    public void setIsMedicalOperator(Boolean isMedicalOperator) {
        this.isMedicalOperator = isMedicalOperator;
    }
}
