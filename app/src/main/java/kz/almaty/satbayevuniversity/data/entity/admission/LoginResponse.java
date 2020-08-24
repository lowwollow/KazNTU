package kz.almaty.satbayevuniversity.data.entity.admission;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("message")
    private Object message;

    @SerializedName("error")
    private Boolean error;

    @SerializedName("redirect")
    private Boolean redirect;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getRedirect() {
        return redirect;
    }

    public void setRedirect(Boolean redirect) {
        this.redirect = redirect;
    }
}
