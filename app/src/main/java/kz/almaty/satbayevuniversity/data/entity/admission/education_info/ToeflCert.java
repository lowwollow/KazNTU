package kz.almaty.satbayevuniversity.data.entity.admission.education_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.utils.Utils;

public class ToeflCert {

    @SerializedName("toeflTypeId")
    @Expose
    private Integer toeflTypeId;
    @SerializedName("toeflSettings")
    @Expose
    private List<ToeflSettings> toeflSettings = null;
    @SerializedName("has")
    @Expose
    private Boolean has;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("file")
    @Expose
    private Object file;
    @SerializedName("documentID")
    @Expose
    private String documentID;
    @SerializedName("imageSrc")
    @Expose
    private Object imageSrc;
    @SerializedName("hasFile")
    @Expose
    private Boolean hasFile;
    @SerializedName("settings")
    @Expose
    private Settings settings;

    public Boolean getHas() {
        return has;
    }

    public void setHas(Boolean has) {
        this.has = has;
    }

    public String getDate() {
        if(date !=null){
            date = Utils.parseDate(date);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public Object getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(Object imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Boolean getHasFile() {
        return hasFile;
    }

    public void setHasFile(Boolean hasFile) {
        this.hasFile = hasFile;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Integer getToeflTypeId() {
        return toeflTypeId;
    }

    public void setToeflTypeId(Integer toeflTypeId) {
        this.toeflTypeId = toeflTypeId;
    }

    public List<ToeflSettings> getToeflSettings() {
        return toeflSettings;
    }

    public void setToeflSettings(List<ToeflSettings> toeflSettings) {
        this.toeflSettings = toeflSettings;
    }
}
