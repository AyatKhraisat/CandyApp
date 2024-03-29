package com.ayat.candyapp.user_flow.signup

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseResponse
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.user_flow.login.UserManagementRepository
import com.ayat.candyapp.utils.AppUtils.validatePassword
import com.ayat.candyapp.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 *Created by Ayat Khriasat on 20,May,2019 at 23:40
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
class SignUpViewModel @Inject
constructor(private val userManagementRepository: UserManagementRepository) : BaseViewModel() {

    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    private val _signUpSuccessEvent = MutableLiveData<Event<String>>()
    val signUpSuccessEvent: LiveData<Event<String>>
        get() = _signUpSuccessEvent


    val userNameError = Transformations.map(name, { input ->
        if (input == null || input.toString().isEmpty())
            return@map R.string.required_field
        else return@map null
    })
    val passwordError = Transformations.map(password) {
        if (TextUtils.isEmpty(it)) {
            return@map R.string.required_field
        } else if (!validatePassword(it))
            return@map R.string.invalid_password

        return@map null;
    }

    val confirmPasswordError: LiveData<Int> = Transformations.map(confirmPassword) {
        if (TextUtils.isEmpty(it)) {
            return@map R.string.required_field
        } else if (it != password.value)
            return@map R.string.passwords_not_matches

        return@map null;
    }


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun onSignupClicked() {

        if (isInputValid()) {
            coroutineScope.launch {
                val getLoginDeferred = userManagementRepository.getSiguoDeffered(name.value!!, password.value!!)
                try {
                    showLoading()

                    val response: BaseResponse = getLoginDeferred.await()
                    hideLoading()

                    if (response.success) {
                        _signUpSuccessEvent.value = Event(response.message!!)
                    } else
                        showError(response.message!!)

                } catch (e: Exception) {
                    hideLoading()
                    if ((e as HttpException).code() == 401)
                        showError("Login Failed")
                    else
                        showError(e.message())


                }
            }

        }
    }

    private fun isInputValid(): Boolean {

        if (name.value == null) {
            name.value = ""
        }
        if (password.value == null) {
            password.value = ""
        }
        if (confirmPassword.value == null) {
            confirmPassword.value = ""
        }
        return (passwordError.value == null) && (userNameError.value == null)
                && (confirmPasswordError.value == null)
    }

}