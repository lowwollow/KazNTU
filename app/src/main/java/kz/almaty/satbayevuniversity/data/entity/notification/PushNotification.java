package kz.almaty.satbayevuniversity.data.entity.notification;

import com.google.gson.annotations.SerializedName;

public class PushNotification {

    @SerializedName("id")
    private Integer id;
    @SerializedName("textRus")
    private String textRus;
    @SerializedName("textKaz")
    private String textKaz;
    @SerializedName("dateCreated")
    private String dateCreated;
    @SerializedName("isRead")
    private Boolean isRead;
    @SerializedName("typeId")
    private Integer typeId;
    @SerializedName("entrantId")
    private Integer entrantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextRus() {
        return textRus;
    }

    public void setTextRus(String textRus) {
        this.textRus = textRus;
    }

    public String getTextKaz() {
        return textKaz;
    }

    public void setTextKaz(String textKaz) {
        this.textKaz = textKaz;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(Integer entrantId) {
        this.entrantId = entrantId;
    }
}
