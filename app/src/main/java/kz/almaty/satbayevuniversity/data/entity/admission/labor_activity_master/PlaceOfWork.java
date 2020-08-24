package kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kz.almaty.satbayevuniversity.utils.Utils;

public class PlaceOfWork {
    @SerializedName("workPlaceTitle")
    @Expose
    private String workPlaceTitle;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("startenOn")
    @Expose
    private String startedOn;
    @SerializedName("endedOn")
    @Expose
    private String endedOn;

    public String getWorkPlaceTitle() {
        return workPlaceTitle;
    }

    public void setWorkPlaceTitle(String workPlaceTitle) {
        this.workPlaceTitle = workPlaceTitle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartedOn() {
        if(startedOn !=null){
            startedOn = Utils.parseDate(startedOn);
        }
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getEndedOn() {
        if(endedOn !=null){
            endedOn = Utils.parseDate(endedOn);
        }
        return endedOn;
    }

    public void setEndedOn(String endedOn) {
        this.endedOn = endedOn;
    }

}
