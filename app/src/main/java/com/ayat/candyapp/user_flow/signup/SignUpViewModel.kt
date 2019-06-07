package com.ayat.candyapp.user_flow.signup

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseResponse
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.user_flow.login.UserManagementRepository
import com.ayat.candyapp.user_flow.login.model.LoginModels
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
    val signUpSuccessEvent =MutableLiveData<Event<String>>()


    val userNameError = Transformations.map(
        name
    ) { input -> input == null || input.toString().isEmpty() }

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

                    if(response.isSuccess) {
                        signUpSuccessEvent.value = Event(response.message!!)
                    }
                    else
                        showError.value = Event(response.message!!)

                } catch (e: Exception) {
                    hideLoading()
                    if ((e as HttpException).code() == 401)
                        showError.value = Event("Login Failed")
                    else
                        showError.value = Event(e.message())


                }
            }

        }
    }

    private fun isInputValid(): Boolean {

        if (userNameError.value == null) {
            name.value = ""
            return false
        }
        if (passwordError.value == null) {
            password.value = ""
            return false
        }
        if (confirmPasswordError.value == null) {
            confirmPassword.value = ""
            return false
        }
        return (passwordError.value!=null) && (userNameError.value!=null)
                && (confirmPasswordError.value!=null)
    }

}