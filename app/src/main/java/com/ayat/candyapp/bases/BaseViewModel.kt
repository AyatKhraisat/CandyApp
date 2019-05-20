package com.ayat.candyapp.bases

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayat.candyapp.utils.Event
import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
open class BaseViewModel : ViewModel() {

    val showLoading = MutableLiveData<Event<Any>>()
    val hideLoading = MutableLiveData<Event<Any>>()
    val showError = MutableLiveData<Event<String>>()


    fun showLoading() {
        showLoading.value = Event(Object())
    }

    fun hideLoading() {
        hideLoading.value = Event(Object())
    }

    override fun onCleared() {
        super.onCleared()
    }
}
