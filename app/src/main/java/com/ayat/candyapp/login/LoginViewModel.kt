package com.ayat.candyapp.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.bases.SingleLiveEvent

import com.ayat.candyapp.login.model.LoginModels
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

import javax.inject.Inject

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
class LoginViewModel @Inject
constructor(private val userManagementRepository: UserManagementRepository) : BaseViewModel() {

    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    internal val openMainActivityEvent = SingleLiveEvent<Void>()
    val showErrorMessage = MutableLiveData<Boolean>()

    private val loginButtonVisibility = MutableLiveData(View.VISIBLE)
    private val loadingVisibility = MutableLiveData(View.GONE)
    private val successVisibility = MutableLiveData(View.GONE)

    val showUserNameError = Transformations.map(
        name
    ) { input -> input == null || input.toString().isEmpty() }

    val showPasswordError = Transformations.map(
        password
    ) { input -> input == null || input.toString().isEmpty() }


//    private val isInputValid: Boolean
//        get() = (!showPasswordError.value)!! && (!showUserNameError.value)!!
//
//    private//            if (successObservableItem.isSuccess()) {
//    //                showLoginSuccess();
//    //            } else {
//    //                showLoginFail();
//    //            }
//    val onSuccessConsumer: Consumer<LoginModels.LoginResponseModel>
//        get() = { successObservableItem -> }
//

    fun onLoginClicked() {
//        if (isInputValid) {
//
//            val loginObservable = userManagementRepository.getLoginObservable(name.value, password.value)
//
//            val loginDisposable = loginObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { disposable -> showLoading() }
//                .doOnEvent { (token), throwable -> showLoginButton() }
//                .doOnError { this.showError(it) }
//                .subscribe(Consumer {  })
//
//            //  compositeDisposable.add(loginDisposable);
//        }
    }

    private fun showLoading() {
        loadingVisibility.value = View.VISIBLE
        loginButtonVisibility.value = View.GONE
    }

    private fun showLoginButton() {
        loadingVisibility.value = View.GONE
        loginButtonVisibility.value = View.VISIBLE
    }

    private fun showError(throwable: Throwable) {
        showErrorMessage.value = true
        successVisibility.value = View.GONE
        Timber.d(throwable)
    }

    private fun showLoginFail() {
        successVisibility.value = View.GONE
        showErrorMessage.value = true
    }


    private fun showLoginSuccess() {
        successVisibility.value = View.VISIBLE
        loginButtonVisibility.value = View.VISIBLE
        openMainActivityEvent.call()
    }

    fun getLoadingVisibility(): LiveData<Int> {
        return loadingVisibility
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getShowErrorMessage(): LiveData<Boolean> {
        return showErrorMessage
    }

    fun getSuccessVisibility(): LiveData<Int> {
        return successVisibility
    }

    fun getLoginButtonVisibility(): LiveData<Int> {
        return loginButtonVisibility
    }

}
