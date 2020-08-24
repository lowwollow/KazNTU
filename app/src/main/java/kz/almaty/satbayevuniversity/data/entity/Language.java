package kz.almaty.satbayevuniversity.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Language {
    @PrimaryKey(autoGenerate = true)
    private int primaryId;
    private String language;
    private String languageCode;
    private int position;

    @Ignore
    public Language(String language, String languageCode, int position) {
        this.language = language;
        this.languageCode = languageCode;
        this.position = position;
    }
    public Language() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }
}
