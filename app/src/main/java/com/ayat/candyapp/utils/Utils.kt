package com.ayat.candyapp.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import java.util.*

/**
 *Created by Ayat Khriasat on 30,May,2019 at 23:54
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/

object AppUtils {
    @JvmStatic
    fun validatePassword(password: String): Boolean {
        val pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_|./;,:])(?=\\S+$).{8,}$"
        return password.matches(pattern.toRegex())
    }


    @BindingAdapter("helperTextValue")
    fun setHelperTextValue(textInputLayout: TextInputLayout, resourceId: Int?) {

        if (resourceId == null)
            textInputLayout.helperText = null
        else
            textInputLayout.helperText = textInputLayout.context.getString(resourceId)

    }
}
