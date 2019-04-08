package com.ayat.candyapp.bases

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ayat khraisat  on 12/27/18
 * akhraisat@blessedtreeit.com
 *
 *
 * Project Name: ActivityCenter
 *
 *
 * Blessed Tree IT
 */
open class BaseViewModel : ViewModel() {

    protected var compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
