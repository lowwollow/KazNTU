package kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master;

import android.os.CpuUsageInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kz.almaty.satbayevuniversity.utils.Utils;

public class ScienceWork {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publishedOn")
    @Expose
    private String publishedOn;
    @SerializedName("journalTitle")
    @Expose
    private String journalTitle;
    @SerializedName("journalNumber")
    @Expose
    private String journalNumber;
    @SerializedName("sbornikTitle")
    @Expose
    private String sbornikTitle;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("coauthor")
    @Expose
    private String coauthor;
    @SerializedName("scienceWorkScan")
    @Expose
    private List<ScienceWorkScan> scienceWorkScan = null;
    @SerializedName("doiScan")
    @Expose
    private List<ScienceWorkScan> doiScan = null;
    @SerializedName("isPublished")
    @Expose
    private Boolean isPublished;
    @SerializedName("fileContainerID")
    @Expose
    private String fileContainerID;
    @SerializedName("publishCategoryId")
    @Expose
    private Object publishCategoryId;
    @SerializedName("scientWorkForm")
    @Expose
    private Object scientWorkForm;
    @SerializedName("issn")
    @Expose
    private Object issn;
    @SerializedName("isbn")
    @Expose
    private Object isbn;

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

    public String getPublishedOn() {
        if(publishedOn !=null){
            publishedOn = Utils.parseDate(publishedOn);
        }
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJournalNumber() {
        return journalNumber;
    }

    public void setJournalNumber(String journalNumber) {
        this.journalNumber = journalNumber;
    }

    public String getSbornikTitle() {
        return sbornikTitle;
    }

    public void setSbornikTitle(String sbornikTitle) {
        this.sbornikTitle = sbornikTitle;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getCoauthor() {
        return coauthor;
    }

    public void setCoauthor(String coauthor) {
        this.coauthor = coauthor;
    }

    public List<ScienceWorkScan> getScienceWorkScan() {
        return scienceWorkScan;
    }

    public void setScienceWorkScan(List<ScienceWorkScan> scienceWorkScan) {
        this.scienceWorkScan = scienceWorkScan;
    }

    public List<ScienceWorkScan> getDoiScan() {
        return doiScan;
    }

    public void setDoiScan(List<ScienceWorkScan> doiScan) {
        this.doiScan = doiScan;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getFileContainerID() {
        return fileContainerID;
    }

    public void setFileContainerID(String fileContainerID) {
        this.fileContainerID = fileContainerID;
    }

    public Object getPublishCategoryId() {
        return publishCategoryId;
    }

    public void setPublishCategoryId(Object publishCategoryId) {
        this.publishCategoryId = publishCategoryId;
    }

    public Object getScientWorkForm() {
        return scientWorkForm;
    }

    public void setScientWorkForm(Object scientWorkForm) {
        this.scientWorkForm = scientWorkForm;
    }

    public Object getIssn() {
        return issn;
    }

    public void setIssn(Object issn) {
        this.issn = issn;
    }

    public Object getIsbn() {
        return isbn;
    }

    public void setIsbn(Object isbn) {
        this.isbn = isbn;
    }
}
