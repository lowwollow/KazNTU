package kz.almaty.satbayevuniversity.data.entity.admission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class University {
    @SerializedName("id")
    int id;

    @SerializedName("title")
    String title;

    @SerializedName("specialityID")
    int specialityID;

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

    public int getSpecialityID() {
        return specialityID;
    }

    public void setSpecialityID(int specialityID) {
        this.specialityID = specialityID;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof University) {
            University university = (University) obj;
            if (university.title.equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}