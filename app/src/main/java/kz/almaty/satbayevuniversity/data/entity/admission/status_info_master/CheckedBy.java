package kz.almaty.satbayevuniversity.data.entity.admission.status_info_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckedBy {
    @SerializedName("isChecked")
    @Expose
    private Boolean isChecked;
    @SerializedName("comment")
    @Expose
    private String comment;

    public Boolean getIsChecked() {
        if(isChecked == null){
            return false;
        }
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
