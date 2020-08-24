package kz.almaty.satbayevuniversity.data.entity.admission.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registration {
    @SerializedName("CitizenCategory")
    @Expose
    private int citizenCategory;

    @SerializedName("Citizenship")
    @Expose
    private int citizenship;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("EntrantId")
    @Expose
    private int entrantId;

    public int getCitizenCategory() {
        return citizenCategory;
    }

    public void setCitizenCategory(int citizenCategory) {
        this.citizenCategory = citizenCategory;
    }

    public int getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(int citizenship) {
        this.citizenship = citizenship;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEntrantId() {
        return entrantId;
    }

    public void setEntrantId(int entrantId) {
        this.entrantId = entrantId;
    }
}
