package com.ayat.candyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ayat.candyapp.di.Qualifiers;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by Ayat khraisat  on 4/1/2018.
 * akhraisat@blessedtreeit.com
 * <p>
 * Project Name: BTIT-Dashboards-Android-V2.0
 * <p>
 * Blessed Tree IT
 */
@Singleton
public class UserPreferences {

    private final String USER_PREFERENCES_FILE_KEY = "user_file";
    private final String DEFAULT_PREF_STRING_VALUE = "";
    private final String PREF_USER_NAME_KEY="user_name";
    private final String PREF_IS_LOGGED_IN_KEY="is_logged_in";

    private Context context;
    private final SharedPreferences userPreferences;
    private final SharedPreferences defaultPreferences;


    @Inject
    public UserPreferences(@Qualifiers.ApplicationContext Context context) {
        this.context=context;
        userPreferences = context.getSharedPreferences(USER_PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        defaultPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void removeUserData() {
        userPreferences.edit().clear().apply();
    }


    public void setUserName(String userName) {
        userPreferences.edit()
                .putString(PREF_USER_NAME_KEY, userName).apply();
    }

    public String getUserName() {
        return userPreferences
                .getString(PREF_USER_NAME_KEY, "");
    }

    public boolean isLoggedIn(){
        return userPreferences.getBoolean(PREF_IS_LOGGED_IN_KEY,false);
    }

    public void setLoggedIn(boolean IsloggedIn){

        userPreferences.edit().putBoolean(PREF_IS_LOGGED_IN_KEY,IsloggedIn).apply();
    }


}
