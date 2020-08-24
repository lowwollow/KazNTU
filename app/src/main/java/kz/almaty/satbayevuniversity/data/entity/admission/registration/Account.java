package kz.almaty.satbayevuniversity.data.entity.admission.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Account {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isValid")
    @Expose
    private Boolean isValid;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("changedObj")
    @Expose
    private Object changedObj;
    @SerializedName("isReload")
    @Expose
    private Boolean isReload;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public Object getChangedObj() {
        return changedObj;
    }

    public void setChangedObj(Object changedObj) {
        this.changedObj = changedObj;
    }

    public Boolean getIsReload() {
        return isReload;
    }

    public void setIsReload(Boolean isReload) {
        this.isReload = isReload;
    }

}
