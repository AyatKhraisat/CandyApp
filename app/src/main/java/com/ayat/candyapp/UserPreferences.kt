package com.ayat.candyapp

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ayat.candyapp.di.Qualifiers

import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Ayat khraisat  on 4/1/2018.
 * ayatzkhraisat@gmail.com
 */
@Singleton
class UserPreferences @Inject
constructor(@Qualifiers.ApplicationContext private val context: Context) {

    private val USER_PREFERENCES_FILE_KEY = "user_file"
    private val DEFAULT_PREF_STRING_VALUE = ""
    private val PREF_USER_NAME_KEY = "user_name"
    private val PREF_IS_LOGGED_IN_KEY = "is_logged_in"
    private val userPreferences: SharedPreferences
    private val defaultPreferences: SharedPreferences

    var userName: String
        get() = userPreferences
            .getString(PREF_USER_NAME_KEY, "")
        set(userName) = userPreferences.edit()
            .putString(PREF_USER_NAME_KEY, userName).apply()

    var isLoggedIn: Boolean
        get() = userPreferences.getBoolean(PREF_IS_LOGGED_IN_KEY, false)
        set(IsloggedIn) = userPreferences.edit().putBoolean(PREF_IS_LOGGED_IN_KEY,
            IsloggedIn).apply()


    init {
        userPreferences = context.getSharedPreferences(USER_PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        defaultPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }


    fun removeUserData() {
        userPreferences.edit().clear().apply()
    }


}
