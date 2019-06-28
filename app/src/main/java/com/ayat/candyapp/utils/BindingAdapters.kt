package com.ayat.candyapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ayat.candyapp.R
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


    @JvmStatic
    @BindingAdapter("errorValue")
    fun setErrorValue(textInputLayout: TextInputLayout, resourceId: Int?) {
        if (resourceId == null)
            textInputLayout.error = null
        else
            textInputLayout.error = textInputLayout.context.getString(resourceId)
    }
    @JvmStatic
    @BindingAdapter("randomSrc")
    fun setErrorValue(imageView: ImageView, id: Int?) {

        if(id!!.rem(4)==0)
            imageView.setImageResource(R.drawable.ic_orange)
       else if(id.rem(2)==0)
            imageView.setImageResource(R.drawable.ic_candy_green)
        else
            imageView.setImageResource(R.drawable.ic_candy_pink)
    }

}
