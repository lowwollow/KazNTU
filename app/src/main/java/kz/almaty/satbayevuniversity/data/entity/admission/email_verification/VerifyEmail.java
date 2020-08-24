package kz.almaty.satbayevuniversity.data.entity.admission.email_verification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VerifyEmail {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("errors")
    @Expose
    private List<String> errors;
    @SerializedName("isOperator")
    @Expose
    private boolean isOperator;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean getIsOperator() {
        return isOperator;
    }

    public void setIsOperator(boolean isOperator) {
        this.isOperator = isOperator;
    }

}
