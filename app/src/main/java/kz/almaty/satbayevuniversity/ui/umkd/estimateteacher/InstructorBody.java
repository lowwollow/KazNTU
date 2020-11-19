package kz.almaty.satbayevuniversity.ui.umkd.estimateteacher;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InstructorBody implements Serializable {
    @SerializedName("description")
    String description;

    @SerializedName("rating")
    double rating;

    public String getDescription() {
        return description;
    }
    public double getRating(){
        return rating;
    }
}
