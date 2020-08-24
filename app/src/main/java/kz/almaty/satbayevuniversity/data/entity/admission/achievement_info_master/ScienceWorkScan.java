package kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScienceWorkScan {
    @SerializedName("documentID")
    @Expose
    private String documentID;
    @SerializedName("file")
    @Expose
    private Object file;
    @SerializedName("src")
    @Expose
    private Object src;
    @SerializedName("isRemoved")
    @Expose
    private Boolean isRemoved;

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public Object getSrc() {
        return src;
    }

    public void setSrc(Object src) {
        this.src = src;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
}
