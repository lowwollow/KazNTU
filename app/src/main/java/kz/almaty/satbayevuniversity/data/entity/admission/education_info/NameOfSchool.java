package kz.almaty.satbayevuniversity.data.entity.admission.education_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameOfSchool {
    @SerializedName("shortTitle")
    @Expose
    private Object shortTitle;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    public Object getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(Object shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}