package com.ayat.candyapp.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.bases.SingleLiveEvent
import com.ayat.candyapp.login.model.LoginModels
import com.ayat.candyapp.utils.Event
import io.reactivex.internal.functions.ObjectHelper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

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

    val openMainActivityEvent = MutableLiveData<Event<Any>>()
    val showLoading = MutableLiveData<Event<Any>>()
    val hideLoading = MutableLiveData<Event<Any>>()
    val showError = MutableLiveData<Event<String>>()


    val showUserNameError = Transformations.map(
        name
    ) { input -> input == null || input.toString().isEmpty() }

    val showPasswordError = Transformations.map(
        password
    ) { input -> input == null || input.toString().isEmpty() }

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun onLoginClicked() {

        if (isInputValid()) {
            coroutineScope.launch {
                var getLoginDeferred = userManagementRepository.getLoginDeferrede(name.value!!, password.value!!)
                try {
                    showLoading()

                    val listResult : LoginModels.LoginResponseModel = getLoginDeferred.await()
                    val auth:String =listResult.Authorization
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

    fun showLoading() {
        showLoading.value = Event(Object())
    }

    fun hideLoading() {
        hideLoading.value = Event(Object())
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
        return (!showPasswordError.value!!) && (!showUserNameError.value!!)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
