package com.ayat.candyapp.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.blessedtreeit.product.activitycenter.base_models.ApiError;
import com.google.gson.annotations.SerializedName;

/**
 * Created by: Ahmed Al-Hashimi
 * Created on: Sep. 30, 2018.
 * <p>
 * aalhashimi@blessedtreeit.com
 * <p>
 * Project Name: BTIT-Dashboards-Android-V2.0
 * <p>
 * BTIT
 */
public class LoginModel implements Parcelable {

    @SerializedName("userId")
    private Integer userId;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("isAdmin")
    private Boolean isAdmin;
    @SerializedName("hasMobileAccess")
    private Boolean hasMobileAccess;
    @SerializedName("accessMinisterMeetings")
    private Boolean accessMinisterMeetings;
    @SerializedName("viewMinisterMeetings")
    private Boolean viewMinisterMeetings;
    @SerializedName("accessCountries")
    private Boolean accessCountries;
    @SerializedName("accessBoards")
    private Boolean accessBoards;
    @SerializedName("accessCommittees")
    private Boolean accessCommittees;
    @SerializedName(value = "isSuccess", alternate = "IsSuccess")
    private boolean isSuccess;
    @SerializedName(value = "Errors", alternate = "errors")
    private ApiError error;

    public LoginModel() {
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName == null ? "" : fullName;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Boolean getHasMobileAccess() {
        return hasMobileAccess;
    }

    public Boolean getAccessMinisterMeetings() {
        return accessMinisterMeetings;
    }

    public Boolean getViewMinisterMeetings() {
        return viewMinisterMeetings;
    }

    public Boolean getAccessCountries() {
        return accessCountries;
    }

    public Boolean getAccessBoards() {
        return accessBoards;
    }

    public Boolean getAccessCommittees() {
        return accessCommittees;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", isAdmin=" + isAdmin +
                ", hasMobileAccess=" + hasMobileAccess +
                ", accessMinisterMeetings=" + accessMinisterMeetings +
                ", viewMinisterMeetings=" + viewMinisterMeetings +
                ", accessCountries=" + accessCountries +
                ", accessBoards=" + accessBoards +
                ", accessCommittees=" + accessCommittees +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeString(this.fullName);
        dest.writeValue(this.isAdmin);
        dest.writeValue(this.hasMobileAccess);
        dest.writeValue(this.accessMinisterMeetings);
        dest.writeValue(this.viewMinisterMeetings);
        dest.writeValue(this.accessCountries);
        dest.writeValue(this.accessBoards);
        dest.writeValue(this.accessCommittees);
    }

    protected LoginModel(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fullName = in.readString();
        this.isAdmin = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hasMobileAccess = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.accessMinisterMeetings = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.viewMinisterMeetings = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.accessCountries = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.accessBoards = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.accessCommittees = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel source) {
            return new LoginModel(source);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
