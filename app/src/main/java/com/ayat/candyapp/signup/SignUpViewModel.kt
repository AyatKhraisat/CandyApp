package com.ayat.candyapp.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.login.UserManagementRepository
import com.ayat.candyapp.login.model.LoginModels
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


    val showUserNameError = Transformations.map(
        name
    ) { input -> input == null || input.toString().isEmpty() }

    val showPasswordError = Transformations.map(
        password
    ) { input -> input == null || input.toString().isEmpty() }

    val showConfirmPasswordError = Transformations.map(
        confirmPassword
    ) { input -> input == null || input.toString().isEmpty() }


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun onSignupClicked() {

        if (isInputValid()) {
            coroutineScope.launch {
                var getLoginDeferred = userManagementRepository.getLoginDeferrede(name.value!!, password.value!!)
                try {
                    showLoading()

                    val listResult: LoginModels.LoginResponseModel = getLoginDeferred.await()
                    val auth: String = listResult.Authorization
                    hideLoading()
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

        if (showUserNameError.value == null) {
            name.value = ""
            return false
        }
        if (showPasswordError.value == null) {
            password.value = ""
            return false
        }
        if (showConfirmPasswordError.value == null) {
            confirmPassword.value = ""
            return false
        }
        return (!showPasswordError.value!!) && (!showUserNameError.value!!)
                && (!showConfirmPasswordError.value!!)
    }

}