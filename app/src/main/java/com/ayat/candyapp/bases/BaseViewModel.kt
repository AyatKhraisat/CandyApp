package com.ayat.candyapp.bases

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Ayat Khriasat on 27,April,2019 at 8:32 PM
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
open class BaseViewModel : ViewModel() {

 var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
