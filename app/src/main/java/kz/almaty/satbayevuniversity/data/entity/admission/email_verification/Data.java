package kz.almaty.satbayevuniversity.data.entity.admission.email_verification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("iin")
    @Expose
    private String iin;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("verificationCode")
    @Expose
    private String verificationCode;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;
    @SerializedName("eduUserID")
    @Expose
    private int eduUserID;
    @SerializedName("isReVerification")
    @Expose
    private boolean isReVerification;
    @SerializedName("levelID")
    @Expose
    private int levelID;

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public int getEduUserID() {
        return eduUserID;
    }

    public void setEduUserID(int eduUserID) {
        this.eduUserID = eduUserID;
    }

    public boolean getIsReVerification() {
        return isReVerification;
    }

    public void setIsReVerification(boolean isReVerification) {
        this.isReVerification = isReVerification;
    }
    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }
}
