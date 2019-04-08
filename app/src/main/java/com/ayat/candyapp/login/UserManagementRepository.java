package com.ayat.candyapp.login;

import com.blessedtreeit.product.activitycenter.UserPreferences;
import com.blessedtreeit.product.activitycenter.flows.login.model.LoginModel;
import com.blessedtreeit.product.activitycenter.flows.login.model.LoginRequestModel;
import com.blessedtreeit.product.activitycenter.network.EndPoint;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ayat khraisat  on 12/27/18
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: ActivityCenter
 * <p>
 * Blessed Tree IT
 */

@Singleton
public class UserManagementRepository {

    private UserPreferences userPreferences;
    private EndPoint endPoint;

    @Inject
    public UserManagementRepository(EndPoint endPoint, UserPreferences userPreferences) {
        this.endPoint = endPoint;
        this.userPreferences = userPreferences;
    }

    public Single<LoginModel> getLoginObservable(String userName, String password) {
        return endPoint.login(
                new LoginRequestModel(userName, password, "", ""))
                .doOnSuccess(loginModel -> {
                    if (loginModel.isSuccess()) {
                        setUserName(loginModel.getFullName());
                    }
                });
    }

    public String getUserName() {
        return userPreferences.getUserName();
    }

    public void setUserName(String name) {
        userPreferences.setUserName(name);
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        userPreferences.setLoggedIn(isLoggedIn);
    }

    public boolean getIsLoggedIn() {
        return userPreferences.isLoggedIn();
    }



}
