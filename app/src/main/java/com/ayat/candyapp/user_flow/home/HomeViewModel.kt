package com.ayat.candyapp.user_flow.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ayat.candyapp.R
import com.ayat.candyapp.bases.BaseViewModel
import com.ayat.candyapp.user_flow.home.CandyRepository
import com.ayat.candyapp.user_flow.home.models.CandyModel
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
class HomeViewModel @Inject
constructor(private val candyRepository: CandyRepository) : BaseViewModel() {

    //TODO create generic adapter and use it
    private val _mutableLiveDataList :MutableLiveData<List<CandyModel>> = MutableLiveData()
    private val mutableLiveDataList :LiveData<List<CandyModel>>
    get() =_mutableLiveDataList

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)




    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
