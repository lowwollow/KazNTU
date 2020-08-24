package kz.almaty.satbayevuniversity.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class AccountEntity {
    private String access_token;
    private String username;
    private String fullName;
    @PrimaryKey
    private int entrantId;
    private String language;

    public AccountEntity(){
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(int entrantId) {
        this.entrantId = entrantId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}


