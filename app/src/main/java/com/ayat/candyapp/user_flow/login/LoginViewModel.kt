package com.ayat.candyapp.user_flow.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.user_flow.login.model.LoginModels
import com.ayat.candyapp.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 *Created by Ayat Khriasat on 26,April,2019 at 10:57 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
class LoginViewModel @Inject
constructor(private val userManagementRepository: UserManagementRepository) : BaseViewModel() {

    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _openMainActivityEvent = MutableLiveData<Event<String>>()
    val openMainActivityEvent: LiveData<Event<String>>
        get() = _openMainActivityEvent

    private val _openSignUpActivity = MutableLiveData<Event<Any>>()
    val openSignUpActivity: LiveData<Event<Any>>
        get() = _openSignUpActivity


    val userNameError = Transformations.map(name, { input ->
        if (input == null || input.toString().isEmpty())
            return@map R.string.required_field
        else return@map null
    })

    val passwordError = Transformations.map(
        password
    ) { input ->
        if (input == null || input.toString().isEmpty())
            return@map R.string.required_field
        else return@map null
    }

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun onNewUserClicked() {
        _openSignUpActivity.value = Event(Any())
    }

    fun onLoginClicked() {

        if (isInputValid()) {
            coroutineScope.launch {
                val getLoginDeferred = userManagementRepository.getLoginDeferred(name.value!!, password.value!!)
                try {
                    showLoading()

                    val listResult: LoginModels.LoginResponseModel = getLoginDeferred.await()
                    val auth: String = listResult.Authorization
                    hideLoading()
                    _openMainActivityEvent.value = Event(auth)
                } catch (e: Exception) {
                    hideLoading()
                    if (e is HttpException)
                        if (e.code() == 401)
                            showError("Login Failed")
                        else
                            showError(e.message())
                    else if (e is SocketTimeoutException)
                        showError("Could not connect to the server")
                    else
                        if (e.message == null)
                            showError("Something went wrong")
                        else
                            showError(e.message!!)

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
        return (passwordError.value == null) && (userNameError.value == null)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
