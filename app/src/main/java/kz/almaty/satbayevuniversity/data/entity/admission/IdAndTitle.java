package kz.almaty.satbayevuniversity.data.entity.admission;

import com.google.gson.annotations.SerializedName;

public class IdAndTitle {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() { return title;}
}
