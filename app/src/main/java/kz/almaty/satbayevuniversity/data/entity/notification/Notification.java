package kz.almaty.satbayevuniversity.data.entity.notification;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity
public class Notification implements Serializable {
	@PrimaryKey(autoGenerate = true)
	private int primaryId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("content")
	private String content;

	@SerializedName("publishedOn")
	private String publishedOn;

	@SerializedName("fileID")
	private String fileID;

	@SerializedName("isPersonal")
	private boolean isPersonal;

	@SerializedName("isImportant")
	private boolean isImportant;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setPublishedOn(String publishedOn){
		this.publishedOn = publishedOn;
	}

	public String getPublishedOn(){
		return publishedOn;
	}

	public void setFileID(String fileID){
		this.fileID = fileID;
	}

	public String getFileID(){
		return fileID;
	}

	public void setIsPersonal(boolean isPersonal){
		this.isPersonal = isPersonal;
	}

	public boolean isIsPersonal(){
		return isPersonal;
	}

	public void setIsImportant(boolean isImportant){
		this.isImportant = isImportant;
	}

	public boolean isIsImportant(){
		return isImportant;
	}

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	@Override
 	public String toString(){
		return 
			"Notification{" + 
			"id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' + 
			",publishedOn = '" + publishedOn + '\'' + 
			",fileID = '" + fileID + '\'' + 
			",isPersonal = '" + isPersonal + '\'' + 
			",isImportant = '" + isImportant + '\'' + 
			"}";
		}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof  Notification) {
			Notification notification = (Notification) obj;
				if (this.getId() != notification.getId()) {
					return false;
				}

				if(this.getFileID()!=null && notification.getFileID()!=null) {
					if (!this.getFileID().equals(notification.getFileID()))
						return false;
				} else if(this.getFileID()!=null || notification.getFileID()!=null) {
					return false;
				}

				if(this.getPublishedOn()!=null && notification.getPublishedOn()!=null) {
					if (!this.getPublishedOn().equals(notification.getPublishedOn()))
						return false;
				}else if(this.getPublishedOn()!=null || notification.getPublishedOn()!=null) {
					return false;
				}

				if(this.getContent()!=null && notification.getContent()!=null) {
					if (!this.getContent().equals(notification.getContent()))
						return false;
				}else if(this.getContent()!=null || notification.getContent()!=null) {
					return false;
				}

				if(this.getTitle()!=null && notification.getTitle()!=null) {
					if (!this.getTitle().equals(notification.getTitle()))
						return false;
				}else if(this.getTitle()!=null || notification.getTitle()!=null) {
					return false;
				}

				if(this.isIsImportant()  !=notification.isIsImportant())
					return false;
				if(this.isIsPersonal() != notification.isIsPersonal())
					return false;

				return true;
		}
		return false;
	}
}