package kz.almaty.satbayevuniversity.data.entity.admission;

import androidx.annotation.NonNull;

public class Speciality {
    private Integer id;
    private String title;
    private Object schoolID;
    private Integer levelId;

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

    public Object getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Object schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
