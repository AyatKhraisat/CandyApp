package com.ayat.candyapp.login.model;

import android.os.Parcel;
import android.os.Parcelable;

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
public class LoginRequestModel implements Parcelable {
    private String userName;
    private String password;
    private String deviceType;
    private String notificationToken;

    public LoginRequestModel(String userName, String password, String deviceType, String notificationToken) {
        this.userName = userName;
        this.password = password;
        this.deviceType = deviceType;
        this.notificationToken = notificationToken;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public String getDeviceType() {
        return deviceType == null ? "" : deviceType;
    }

    public String getNotificationToken() {
        return notificationToken == null ? "" : notificationToken;
    }


    @Override
    public String toString() {
        return "LoginRequestModel{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", notificationToken='" + notificationToken + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeString(this.deviceType);
        dest.writeString(this.notificationToken);
    }

    protected LoginRequestModel(Parcel in) {
        this.userName = in.readString();
        this.password = in.readString();
        this.deviceType = in.readString();
        this.notificationToken = in.readString();
    }

    public static final Creator<LoginRequestModel> CREATOR = new Creator<LoginRequestModel>() {
        @Override
        public LoginRequestModel createFromParcel(Parcel source) {
            return new LoginRequestModel(source);
        }

        @Override
        public LoginRequestModel[] newArray(int size) {
            return new LoginRequestModel[size];
        }
    };
}
