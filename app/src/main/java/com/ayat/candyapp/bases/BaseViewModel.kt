package com.ayat.candyapp.bases

import androidx.lifecycle.LiveData
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

    val showDialogSingleLiveEvent: LiveData<Event<String>>
        get() = _showDialogSingleLiveEvent
    private val _showDialogSingleLiveEvent = MutableLiveData<Event<String>>()

    val showLoadingDialogSingleLiveEvent: LiveData<Event<Any>>
        get() = _showLoadingDialogSingleLiveEvent
    private val _showLoadingDialogSingleLiveEvent = MutableLiveData<Event<Any>>()

    val hideDialogEventMutableLiveData: LiveData<Event<Any>>
        get() = _hideDialogEventMutableLiveData
    private val _hideDialogEventMutableLiveData = MutableLiveData<Event<Any>>()

    val showErrorEventMutableLiveData: LiveData<Event<String>>
        get() = _showErrorEventMutableLiveData
    private val _showErrorEventMutableLiveData = MutableLiveData<Event<String>>()


    protected var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }



    fun showError(text: String) {
        _showErrorEventMutableLiveData.value = Event(text)
    }

    fun hideLoading() {
        _hideDialogEventMutableLiveData.value = Event(Any())
    }

    fun showLoading() {
        _showLoadingDialogSingleLiveEvent.value = Event(Any())
    }
}
